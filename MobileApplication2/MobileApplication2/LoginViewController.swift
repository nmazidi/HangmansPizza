//
//  ViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {
    @IBOutlet weak var txtEmailAddress: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    var dataLoaded = [[String: AnyObject]]()
    var riderLoggedIn = DeliveryRider()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(true, animated: animated)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func btnLoginClicked(_ sender: Any) {
        //creating alert to to make use wait for api call
        let alert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating()
        
        alert.view.addSubview(loadingIndicator)
        DispatchQueue.main.async {
            self.present(alert, animated: true, completion: nil)
        }
        
        //when GetReq is done
        GETRequest() { success in
            print("Successful? \(success.0)\n")
            self.dataLoaded = success.1
            //remove alert from screen when api call completed
            DispatchQueue.main.async {
                alert.dismiss(animated: false, completion: nil)
                loadingIndicator.stopAnimating()
            }
            if !success.0 {
                let errorAlert = UIAlertController(title: "Error", message: "Could not connect. Please try again later.", preferredStyle: .alert)
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                errorAlert.addAction(okAction)
                DispatchQueue.main.async {
                    self.present(errorAlert, animated: true, completion: nil)
                }
                
            } else {
                if self.isValidCredentials(jsonArray: success.1) {
                    self.createRiderInstanceFromData(jsonData: success.1)
                    DispatchQueue.main.async {
                        self.performSegue(withIdentifier: "LoggedInSegue", sender: self)
                    }
                } else {
                    let invalidAlert = UIAlertController(title: "Error", message: "Invalid login credentials. Email address or password is incorrect.", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    invalidAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        self.present(invalidAlert, animated: true, completion: nil)
                    }
                }
            }
        }
    }
    func isValidCredentials(jsonArray : [[String: AnyObject]]) -> Bool {
        for item in jsonArray {
            for (field, data) in item {
                print("\(field) : \(data)")
            }
            if item["RIDER_EMAIL"] as? String == txtEmailAddress.text && item["RIDER_PASSWORD"] as? String == txtPassword.text {
                return true
            }
        }
        return false
    }
    func createRiderInstanceFromData(jsonData: [[String: AnyObject]]) {
        for item in jsonData {
            if item["RIDER_EMAIL"] as? String == txtEmailAddress.text {
                riderLoggedIn.setRiderID(newRiderID: item["RIDER_ID"] as! Int)
                riderLoggedIn.setTitle(newTitle: "UNKNOWN")  //TODO: add title field to database and rebuild API
                riderLoggedIn.setForename(newForename: item["RIDER_FORENAME"] as! String)
                riderLoggedIn.setSurname(newSurname: item["RIDER_SURNAME"] as! String)
                riderLoggedIn.setEmailAddress(newEmailAddress: item["RIDER_EMAIL"] as! String)
                riderLoggedIn.setPhoneNumber(newPhoneNumber: item["RIDER_PHONE"] as! String)
                riderLoggedIn.setPassword(newPassword: item["RIDER_PASSWORD"] as! String)
                riderLoggedIn.setVehicleType(newVehicleType: item["VEHICLE_TYPE"] as! String)
                riderLoggedIn.setDOB(newDOB: formatDate(dateStr: item["RIDER_DOB"] as! String))
                print("Rider instance successfully created")
            }
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "LoggedInSegue" {
            if let destination = segue.destination as? WelcomeViewController {
                //Pass rider object to WelcomeViewController
                destination.riderLoggedIn = self.riderLoggedIn
            }
        }
    }
}

