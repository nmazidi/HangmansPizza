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
    var timer = Timer()
    
    @IBOutlet var map: MKMapView!
    let manager = CLLocationManager()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        manager.delegate = self
        manager.desiredAccuracy = kCLLocationAccuracyBest
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
        
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
        scheduledTimerWithTimeIntervalForLocation(location: location)
        
    }
    func scheduledTimerWithTimeIntervalForLocation(location: CLLocation){
        timer = Timer.scheduledTimer(timeInterval: 60, target: self, selector: #selector(self.storeRiderLocation), userInfo: location, repeats: true)
    }
    func scheduledTimerWithTimeIntervalForNewJob(location: CLLocation){
        timer = Timer.scheduledTimer(timeInterval: 60, target: self, selector: #selector(self.getNewJob), userInfo: nil, repeats: true)
    }
    
    func storeRiderLocation(timer: Timer) {
        let location = timer.userInfo as! CLLocation
        let lat: Float = Float(location.coordinate.latitude)
        let long: Float = Float(location.coordinate.longitude)
        print("Location: \(lat), \(long)")
        
    }
    func getNewJob() {
        
    }

}
