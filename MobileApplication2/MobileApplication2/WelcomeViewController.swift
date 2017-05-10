//
//  WelcomeViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class WelcomeViewController: UIViewController {
    var riderLoggedIn = DeliveryRider()
    var newShift = Shift()
    @IBOutlet weak var lblWelcome: UILabel!
    
    @IBOutlet weak var lblDeliveriesToday: UILabel!
    @IBOutlet weak var lblTotalToday: UILabel!
    @IBOutlet weak var lblHoursToday: UILabel!
    @IBOutlet weak var lblHoursWeek: UILabel!
    @IBOutlet weak var lblTotalWeek: UILabel!
    @IBOutlet weak var lblDeliveriesWeek: UILabel!
    
    @IBAction func btnGoLive(_ sender: Any) {
        newShift = Shift(shiftID: 1, riderID: riderLoggedIn.getRiderID(), shiftStart: Date(), latitude: 0, longitude: 0, deliveriesMade: 0, status: ShiftStatus.AVAILABLE.rawValue, totalEarned: 0)
        
        let alert = UIAlertController(title: nil, message: "Please wait, going online...", preferredStyle: .alert)
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating()
        
        alert.view.addSubview(loadingIndicator)
        DispatchQueue.main.async {
            self.present(alert, animated: true, completion: nil)
        }
        APICommunication.POSTRequest(path: "rider_activity", params: UtilityFunctions.getStringDictionaryFromObject(obj: newShift)) { success in
            print("POST successful? \(success.0) with code: \(success.1)")
            if success.0 {
                self.createShiftInstanceFromData(jsonData: success.2)
                DispatchQueue.main.async {
                    alert.dismiss(animated: true) {
                        self.performSegue(withIdentifier: "GoLiveSegue", sender: self)
                    }
                }
            } else {
                let errorAlert = UIAlertController(title: "Error", message: "Could not sign in. Code: \(success.1)", preferredStyle: .alert)
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                errorAlert.addAction(okAction)
                DispatchQueue.main.async {
                    alert.dismiss(animated: true) {
                        self.present(errorAlert, animated: true)
                    }
                }
            }
        }
    }
    func createShiftInstanceFromData(jsonData: [[String: AnyObject]]) {
        print(jsonData)
        for item in jsonData {
            
            newShift = Shift(shiftID: item["SHIFT_ID"] as! Int, riderID: item["RIDER_ID"] as! Int, shiftStart: Date(), latitude: 0.0, longitude: 0.0, deliveriesMade: item["DELIVERIES_MADE"] as! Int, status: item["STATUS"] as! String, totalEarned: 0)
            
            print("Shift instance successfully created")
        }
    }
    @IBAction func btnSignOut(_ sender: Any) {
        DispatchQueue.main.async {
            self.performSegue(withIdentifier: "SignedOutSegue", sender: self)
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        DispatchQueue.main.async {
            self.lblWelcome.text = "Welcome \(self.riderLoggedIn.getForename())!"
        }
        //initStats()
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
    }
    func initStats() {
        var shiftData = [[String:AnyObject]]()
        APICommunication.GETRequest(path: "RIDER_ACTIVITY") { success in
            if success.0 == true {
                print("GET successful")
                shiftData = success.1
                for item in shiftData {
                    if (item["RIDER_ID"]?.isEqual(self.riderLoggedIn.getRiderID()))!  {
                        print(true)
                        if let shiftStart: Date = item["SHIFT_START"] as? Date {
                            print(shiftStart)
                            let calendar = NSCalendar.current
                            let shiftDate = calendar.startOfDay(for: shiftStart)
                            let todayDate = calendar.startOfDay(for: Date())
                            let components = calendar.dateComponents([.day], from: todayDate, to: shiftDate)
                            print("days between: \(components.day)")
                            if components.day == 0 {
                                
                            }
                        } else {
                            
                        }

                    } else {
                        
                    }
                }
            } else {
                print("GET not successful")
            }
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SignedOutSegue" {
            if let destination = segue.destination as? LoginViewController {
                destination.riderLoggedIn.resetRider()
            }
        } else if segue.identifier == "AccountSegue" {
            if let destination = segue.destination as? AccountViewController {
                //Pass rider object to WelcomeViewController
                destination.riderLoggedIn = self.riderLoggedIn
            }
        } else if segue.identifier == "GoLiveSegue" {
            if let destination = segue.destination as? LiveViewController {
                destination.riderLoggedIn = self.riderLoggedIn
                destination.currentShift = self.newShift
            }
        }
    }
    
}
