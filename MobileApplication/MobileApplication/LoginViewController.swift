//
//  ViewController.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 01/02/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var txtEmailAddress: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    var dataLoaded = [[String: AnyObject]]()
    var riderLoggedIn = DeliveryRider()

    override func viewDidLoad() {
        super.viewDidLoad()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func btnLoginClicked(_ sender: Any) {
        
        //creating alert to to make use wait for api call
        let alert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating()
        
        alert.view.addSubview(loadingIndicator)
        present(alert, animated: true, completion: nil)
        
        //when GetReq is done
        GetRequest() { success in
            print("Successful? \(success.0)\n")
            self.dataLoaded = success.1
            //remove alert from screen when api call completed
            alert.dismiss(animated: false, completion: nil)
            loadingIndicator.stopAnimating()
            
            if !success.0 {
                let errorAlert = UIAlertController(title: "Error", message: "Could not connect. Please try again later.", preferredStyle: .alert)
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                errorAlert.addAction(okAction)
                self.present(errorAlert, animated: true, completion: nil)
            } else {
                if self.isValidCredentials(jsonArray: success.1) {
                    self.createRiderInstanceFromData(jsonData: success.1)
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
            riderLoggedIn.riderID = item["RIDER_ID"] as! Int
            riderLoggedIn.title = "UNKNOWN" //TODO: add title field to database and rebuild API
            riderLoggedIn.forename = item["RIDER_FORENAME"] as! String
            riderLoggedIn.surname = item["RIDER_SURNAME"] as! String
            riderLoggedIn.emailAddress = item["RIDER_EMAIL"] as! String
            riderLoggedIn.phoneNumber = item["RIDER_PHONE"] as! String
            riderLoggedIn.password = item["RIDER_PASSWORD"] as! String
            riderLoggedIn.vehicleType = item["VEHICLE_TYPE"] as! String
            riderLoggedIn.DOB = item["RIDER_DOB"] as! Date
            print("Rider instance successfully created")
        }
    }
        
}

