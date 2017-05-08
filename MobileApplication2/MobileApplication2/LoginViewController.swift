//
//  ViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet var txtEmailAddress: UITextField?
    @IBOutlet var txtPassword: UITextField?
    var riderLoggedIn = DeliveryRider()

    override func viewDidLoad() {
        super.viewDidLoad()
        txtPassword?.delegate = self
        txtEmailAddress?.delegate = self
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(LoginViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
    }
    func dismissKeyboard() {
        view.endEditing(true)
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
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
        var loginDetails = [String: String]()
        loginDetails["email"] = txtEmailAddress?.text
        loginDetails["password"] = txtPassword?.text
        APICommunication.POSTLoginRequest(params: loginDetails) { success in
            print("POST login Successful? \(success.0)\n")
            let isValidCredentials = success.0
            let responseCode = success.1
            let dataResponse = success.2
            if isValidCredentials {
                self.createRiderInstanceFromData(jsonData: dataResponse)
                DispatchQueue.main.async {
                    alert.dismiss(animated: true) {
                        self.performSegue(withIdentifier: "LoggedInSegue", sender: self)
                    }
                }
            } else {
                if responseCode == 401 || responseCode == 406 {
                    let invalidAlert = UIAlertController(title: "Error", message: "Invalid login credentials. Email address or password is incorrect. Code: \(responseCode)", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    invalidAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        alert.dismiss(animated: true) {
                            self.present(invalidAlert, animated: true)
                        }
                    }
                } else {
                    let invalidAlert = UIAlertController(title: "Error", message: "Theres was an error logging in. Code: \(responseCode)", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    invalidAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        alert.dismiss(animated: true) {
                            self.present(invalidAlert, animated: true)
                        }
                    }
                }
            }
        }
    }
    func createRiderInstanceFromData(jsonData: [[String: AnyObject]]) {
        for item in jsonData {
            if item["RIDER_EMAIL"] as? String == txtEmailAddress?.text {
                riderLoggedIn.setRiderID(newRiderID: item["RIDER_ID"] as! Int)
                riderLoggedIn.setTitle(newTitle: item["RIDER_TITLE"] as! String)
                riderLoggedIn.setForename(newForename: item["RIDER_FORENAME"] as! String)
                riderLoggedIn.setSurname(newSurname: item["RIDER_SURNAME"] as! String)
                riderLoggedIn.setEmailAddress(newEmailAddress: item["RIDER_EMAIL"] as! String)
                riderLoggedIn.setPhoneNumber(newPhoneNumber: item["RIDER_PHONE"] as! String)
                riderLoggedIn.setPassword(newPassword: item["RIDER_PASSWORD"] as! String)
                riderLoggedIn.setVehicleType(newVehicleType: item["VEHICLE_TYPE"] as! String)
                riderLoggedIn.setDOB(newDOB: UtilityFunctions.formatDateForStorage(dateStr: item["RIDER_DOB"] as! String))
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

