//
//  LiveViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 07/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit
import MapKit
import CoreLocation

class LiveViewController: UIViewController, CLLocationManagerDelegate  {
    var riderLoggedIn = DeliveryRider()
    var currentShift = Shift()
    var currentDelivery = Delivery()
    var orderBeingDelivered = Order()
    var timer = Timer()
    
    @IBOutlet var map: MKMapView!
    let manager = CLLocationManager()

    override func viewDidLoad() {
        super.viewDidLoad()
        print(self.currentShift.getShiftID())
        manager.delegate = self
        manager.desiredAccuracy = kCLLocationAccuracyBest
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
        scheduledTimerWithTimeIntervalForLocation()
        scheduledTimerWithTimeIntervalForNewJob()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        let location = locations[0]
        let span: MKCoordinateSpan = MKCoordinateSpanMake(0.01, 0.01)
        let riderLocation: CLLocationCoordinate2D = CLLocationCoordinate2DMake(location.coordinate.latitude, location.coordinate.longitude)
        let region: MKCoordinateRegion = MKCoordinateRegionMake(riderLocation, span)
        map.setRegion(region, animated: true)
        
        let restaurantLocation = CLLocationCoordinate2DMake(50.375360, -4.140813)
        let annotation = MKPointAnnotation()
        annotation.coordinate = restaurantLocation
        annotation.title = "Hangman's Pizza"
        map.addAnnotation(annotation)
        
        self.map.showsUserLocation = true
        
    }
    func scheduledTimerWithTimeIntervalForLocation(){
        timer = Timer.scheduledTimer(timeInterval: 40, target: self, selector: #selector(self.storeRiderLocation), userInfo: nil, repeats: true)
    }
    func scheduledTimerWithTimeIntervalForNewJob(){
        timer = Timer.scheduledTimer(timeInterval: 20, target: self, selector: #selector(self.getNewJob), userInfo: nil, repeats: true)
    }
    
    func storeRiderLocation() {
        DispatchQueue.global(qos: .background).async {
            self.manager.stopUpdatingLocation()
            let lat = Float((self.manager.location?.coordinate.latitude)!)
            let long = Float((self.manager.location?.coordinate.longitude)!)
            
            print("Location: \(lat), \(long)")
            self.currentShift.setLatitude(newLatitude: lat)
            self.currentShift.setLongitude(newLongitude: long)
            APICommunication.PUTRequest(path: "rider_activity", id: self.currentShift.getShiftID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentShift)) { success in
                self.manager.startUpdatingLocation()
            }
        }
    }
    func getNewJob(){
        DispatchQueue.global(qos: .background).async {
            APICommunication.GETRequest(path: "orders") { success in
                if success.0 {
                    print("GET request successful? \(success.0)")
                    for item in success.1 {
                        if item["ORDER_STATUS"] as! String == OrderStatus.READY.rawValue {
                            let newJobAlert = UIAlertController(title: "New Job Available", message: "Do you want to take this delivery?", preferredStyle: .alert)
                            let yesAction = UIAlertAction(title: "Yes", style: UIAlertActionStyle.default, handler: { action in
                                let alert = UIAlertController(title: nil, message: "Accepting job. Please wait...", preferredStyle: .alert)
                                let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
                                
                                loadingIndicator.hidesWhenStopped = true
                                loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
                                loadingIndicator.startAnimating()
                                
                                alert.view.addSubview(loadingIndicator)
                                DispatchQueue.main.async {
                                    self.present(alert, animated: true, completion: nil)
                                }
                                self.currentDelivery = Delivery(deliveryID: 1, orderID: item["ORDER_ID"] as! Int, dateDelivered: Date(), deliveryNotes: "none", riderID: self.riderLoggedIn.getRiderID())
                                APICommunication.POSTRequest(path: "deliveries", params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentDelivery)) { success in
                                    if success.0 {
                                        print("created new delivery. Code: \(success.1)")
                                        for delivery in success.2 {
                                            self.currentDelivery.setDeliveryID(newDeliveryID: delivery["DELIVERY_ID"] as! Int)
                                        }
                                        var addressID: Int
                                        // TODO: ADD ADDRESS CLASS
                                        APICommunication.GETRequestByID(path: "customers", id: item["CUSTOMER_ID"] as! Int) { success in
                                            if success.0 {
                                                print("read addresses successfully")
                                                for cust in success.1 {
                                                    addressID = cust["ADDRESS_ID"] as! Int
                                                }
                                                APICommunication.GETRequestByID(path: "addresses", id: addressID) { success in
                                                    
                                                }
                                            } else {
                                                print("Error getting customer")
                                            }
                                        }
                                        orderBeingDelivered = Order(orderID: item["ORDER_ID"] as! Int, customerID: item["CUSTOMER_ID"] as! Int, datePlaced: item["DATE_PLACED"] as! Date, totalCost: item["TOTAL_COST"] as! Double, orderType: item["ORDER_TYPE"] as! String, notes: item["NOTES"] as! String, orderStatus: item["ORDER_STATUS"] as! String, addressLine1: customer[], addressLine2: <#T##String#>, postcode: <#T##String#>)
                                        alert.dismiss(animated: true)
                                    } else {
                                        print("Error creating delivery. Code: \(success.1)")
                                    }
                                }
                            })
                            let noAction = UIAlertAction(title: "No", style: UIAlertActionStyle.default)
                            newJobAlert.addAction(yesAction)
                            newJobAlert.addAction(noAction)
                            DispatchQueue.main.async {
                                self.present(newJobAlert, animated: true, completion: nil)
                            }
                        }
                    }
                } else {
                    print("there was an error getting orders")
                }
            }
        }
        
    }

}
