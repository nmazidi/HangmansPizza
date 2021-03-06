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
import AVFoundation

class LiveViewController: UIViewController, CLLocationManagerDelegate  {
    var riderLoggedIn = DeliveryRider()
    var currentShift = Shift()
    var currentDelivery = Delivery()
    var orderBeingDelivered = Order()
    var addressToBeDeliveredTo = Address()
    var customerToDeliverTo = Customer()
    var jobTimer = Timer()
    var locationTimer = Timer()
    var distanceFromRestaurant: CLLocationDistance = 0;
    
    lazy var geocoder = CLGeocoder()
    
    
    let systemSoundID: SystemSoundID = 1022
    
    
    @IBOutlet weak var btnCallCustomer: UIButton!
    @IBOutlet weak var lblNoJobs: UILabel!
    @IBOutlet weak var btnNavigate: UIButton!
    @IBOutlet weak var btnDelivered: UIButton!
    @IBOutlet weak var lblDeliverTo: UILabel!
    @IBOutlet weak var lblName: UILabel!
    @IBOutlet weak var lblLine1: UILabel!
    @IBOutlet weak var lblLine2: UILabel!
    @IBOutlet weak var lblPostCode: UILabel!
    @IBOutlet weak var imgCall: UIImageView!
    @IBOutlet var map: MKMapView!
    
    let manager = CLLocationManager()

    @IBAction func btnCallCustomer(_ sender: Any) {
        guard let numberURL = URL(string: "telprompt://" + customerToDeliverTo.getPhoneNumber())
            else {
                return
        }
        UIApplication.shared.open(numberURL, options: [:], completionHandler: nil)
    }
    @IBAction func btnDelivered(_ sender: Any) {
        DispatchQueue.global(qos: .background).async {
            self.currentShift.setAvailable()
            self.orderBeingDelivered.setStatusAsDelivered()
            
            APICommunication.PUTRequest(path: "rider_activity", id: self.currentShift.getShiftID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentShift)) { success in
                if success.0 {
                    print("Shift PUT successful. Code: \(success.1)")
                    APICommunication.PUTRequest(path: "orders", id: self.orderBeingDelivered.getOrderID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.orderBeingDelivered)) { success in
                        if success.0 {
                            print("Orders PUT successful. Code: \(success.1)")
                            self.currentDelivery = Delivery()
                            self.orderBeingDelivered = Order()
                            self.addressToBeDeliveredTo = Address()
                            self.customerToDeliverTo = Customer()
                            DispatchQueue.main.async {
                                self.lblNoJobs.isHidden = false
                                self.lblDeliverTo.isHidden = true
                                self.lblName.isHidden = true
                                self.lblLine1.isHidden = true
                                self.lblLine2.isHidden = true
                                self.lblPostCode.isHidden = true
                                self.btnNavigate.isHidden = true
                                self.btnCallCustomer.isHidden = true
                                self.imgCall.isHidden = true
                                self.btnDelivered.isHidden = true
                                self.jobTimer.fire()
                                self.scheduledTimerWithTimeIntervalForNewJob()
                                
                            }
                            
                        } else {
                            print("Orders PUT unsuccessful. Code: \(success.1)")
                        }
                    }
                } else {
                    print("Shift PUT unsuccessful. Code: \(success.1)")
                }
            }
        }
    }
    
     func geocode(completionHandler: @escaping (CLLocationCoordinate2D, MKPlacemark) -> ()) {
        let address = "\(addressToBeDeliveredTo.addressLine1), \(addressToBeDeliveredTo.town)"
        var coordinates = CLLocationCoordinate2D()
        print(address)
        let geocoder = CLGeocoder()
        geocoder.geocodeAddressString(address) { (placemarks, error) in
            if let placemarks = placemarks {
                if placemarks.count != 0 {
                    let annotation = MKPlacemark(placemark: placemarks.first!)
                    self.map.addAnnotation(annotation)
                    let span: MKCoordinateSpan = MKCoordinateSpanMake(0.01, 0.01)
                    let region: MKCoordinateRegion = MKCoordinateRegionMake(coordinates, span)
                    self.map.setRegion(region, animated: true)
                }
            }
            coordinates = (placemarks?.first?.location?.coordinate)!
            let placemark = MKPlacemark(coordinate: coordinates)
            completionHandler(coordinates, placemark)
        }
        
    }
    
    @IBAction func btnNavigate(_ sender: Any) {
        var lat = CLLocationDegrees()
        var long = CLLocationDegrees()
        geocode() { success in
            lat = success.0.latitude
            long = success.0.longitude
            let mapItem = MKMapItem(placemark: success.1)
            let launchOptions = [MKLaunchOptionsDirectionsModeKey : MKLaunchOptionsDirectionsModeDriving]
            mapItem.openInMaps(launchOptions: launchOptions)
            self.btnNavigate.isHidden = true
            self.btnDelivered.isHidden = false
        }
    }

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
        super.viewDidAppear(animated)
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
        locationTimer = Timer.scheduledTimer(timeInterval: 40, target: self, selector: #selector(self.storeRiderLocation), userInfo: nil, repeats: true)
    }
    func scheduledTimerWithTimeIntervalForNewJob(){
        jobTimer = Timer.scheduledTimer(timeInterval: 20, target: self, selector: #selector(self.getNewJob), userInfo: nil, repeats: true)
    }
    
    func storeRiderLocation() {
        DispatchQueue.global(qos: .background).async {
            self.manager.stopUpdatingLocation()
            let lat = Float((self.manager.location?.coordinate.latitude)!)
            let long = Float((self.manager.location?.coordinate.longitude)!)
            
            print("Location: \(lat), \(long)")
            let restaurantLocation = CLLocation(latitude: 50.3075360, longitude: -4.140813)
            let riderLocation = CLLocation(latitude: CLLocationDegrees(lat), longitude: CLLocationDegrees(long))
            self.distanceFromRestaurant = riderLocation.distance(from: restaurantLocation)
            print("Distance from restaurant: \(self.distanceFromRestaurant)")
            
            self.currentShift.setLatitude(newLatitude: lat)
            self.currentShift.setLongitude(newLongitude: long)
            APICommunication.PUTRequest(path: "rider_activity", id: self.currentShift.getShiftID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentShift)) { success in
                self.manager.startUpdatingLocation()
            }
        }
    }
    func getNewJob(){
        DispatchQueue.global(qos: .background).async {
            if self.distanceFromRestaurant <= 500 {
                APICommunication.GETRequest(path: "orders") { success in
                if success.0 {
                    print("GET request successful? \(success.0)")
                    for item in success.1 {
                        if item["ORDER_STATUS"] as! String == OrderStatus.READY.rawValue {
                            
                            AudioServicesPlayAlertSound(self.systemSoundID)
                            
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
                                self.currentDelivery = Delivery(deliveryID: 1, orderID: item["ORDER_ID"] as! Int, dateDelivered: Date(), deliveryNotes: "none", riderID: self.riderLoggedIn.getRiderID(), deliveryStatus: DeliveryStatus.OUTFORDELIVERY.rawValue)
                                APICommunication.POSTRequest(path: "deliveries", params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentDelivery)) { success in
                                    if success.0 {
                                        print("created new delivery. Code: \(success.1)")
                                        for delivery in success.2 {
                                            self.currentDelivery.setDeliveryID(newDeliveryID: delivery["DELIVERY_ID"] as! Int)
                                        }
                                        APICommunication.GETRequestByID(path: "customers", id: item["CUSTOMER_ID"] as! Int) { success in
                                            if success.0 {
                                                print("read customers successfully")
                                                for cust in success.1 {
                                                    self.customerToDeliverTo = Customer(title: cust["CUSTOMER_TITLE"] as! String, forename: cust["CUSTOMER_FORENAME"] as! String, surname: cust["CUSTOMER_SURNAME"] as! String, phoneNumber: cust["CUSTOMER_PHONE"] as! String, emailAddress: cust["CUSTOMER_EMAIL"] as! String, DOB: UtilityFunctions.formatDateForStorage(dateStr: cust["CUSTOMER_DOB"] as! String), customerID: cust["CUSTOMER_ID"] as! Int, addressID: cust["ADDRESS_ID"] as! Int)
                                                }
                                                print(self.customerToDeliverTo.getAddressID())
                                                APICommunication.GETRequestByID(path: "addresses", id: self.customerToDeliverTo.getAddressID()) { success in
                                                    if success.0 {
                                                        print("Getting address successful")
                                                        for item in success.1 {
                                                            self.addressToBeDeliveredTo = Address(addressID: item["ADDRESS_ID"] as! Int, addressLine1: item["ADDRESS_LINE_1"] as! String, addressLine2: item["ADDRESS_LINE_2"] as! String, town: item["TOWN"] as! String, postcode: item["POSTCODE"] as! String)
                                                            DispatchQueue.main.async {
                                                                self.jobAccepted()
                                                            }
                                                        }
                                                    } else {
                                                        print(" error getting address.")
                                                    }
                                                }
                                            } else {
                                                print("Error getting customer")
                                            }
                                        }
                                        print(item)
                                        self.orderBeingDelivered = Order(orderID: item["ORDER_ID"] as! Int, customerID: item["CUSTOMER_ID"] as! Int, datePlaced: UtilityFunctions.formatDateForStorage(dateStr: item["DATE_PLACED"] as! String), totalCost: item["TOTAL_COST"] as! Double, orderType: item["ORDER_TYPE"] as! String, notes: item["NOTES"] as! String, orderStatus: item["ORDER_STATUS"] as! String, addressLine1: self.addressToBeDeliveredTo.getAddressLine1(), addressLine2: self.addressToBeDeliveredTo.getAddressLine2(), postcode: self.addressToBeDeliveredTo.getPostcode())
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
    func jobAccepted() {
        self.jobTimer.invalidate()
        
        self.currentShift.setUnavailable()
        self.orderBeingDelivered.setStatusAsOutForDelivery()
        
        DispatchQueue.global(qos: .background).async {
            APICommunication.PUTRequest(path: "rider_activity", id: self.currentShift.getShiftID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.currentShift)) { success in
                if success.0 {
                    print("Shift PUT successful. Code: \(success.1)")
                    APICommunication.PUTRequest(path: "orders", id: self.orderBeingDelivered.getOrderID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.orderBeingDelivered)) { success in
                        if success.0 {
                            print("Orders PUT successful. Code: \(success.1)")
                        } else {
                            print("Orders PUT unsuccessful. Code: \(success.1)")
                        }
                        DispatchQueue.main.async {
                            self.performSegue(withIdentifier: "DeliveredSegue", sender: self)
                        }
                        
                    }
                    
                } else {
                    print("Shift PUT unsuccessful. Code: \(success.1)")
                }
            }
        }
        lblNoJobs.isHidden = true
        lblDeliverTo.isHidden = false
        lblName.isHidden = false
        lblLine1.isHidden = false
        lblLine2.isHidden = false
        lblPostCode.isHidden = false
        btnNavigate.isHidden = false
        btnCallCustomer.isHidden = false
        imgCall.isHidden = false
        
        
        lblName.text = customerToDeliverTo.getForename()
        print(addressToBeDeliveredTo.getAddressLine1())
        lblLine1.text = addressToBeDeliveredTo.getAddressLine1()
        lblLine2.text = addressToBeDeliveredTo.getAddressLine2()
        lblPostCode.text = addressToBeDeliveredTo.getPostcode()
    }
    
}
