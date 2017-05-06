//
//  AccountViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 03/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class AccountViewController: UIViewController, UITextFieldDelegate {
    var riderLoggedIn = DeliveryRider()
    
    @IBOutlet weak var lblTitleName: UILabel!
    @IBOutlet weak var lblEmail: UILabel!
    @IBOutlet var txtForename: UITextField?
    @IBOutlet var txtSurname: UITextField?
    @IBOutlet var txtPhone: UITextField?
    @IBOutlet var txtVehicleType: UITextField?
    @IBOutlet var txtPassword: UITextField?
    @IBOutlet var txtRetype: UITextField?
    @IBOutlet var txtCurrentPassword: UITextField?


    override func viewDidLoad() {
        super.viewDidLoad()
        txtForename?.delegate = self
        txtSurname?.delegate = self
        txtPhone?.delegate = self
        txtVehicleType?.delegate = self
        txtPassword?.delegate = self
        txtRetype?.delegate = self
        txtCurrentPassword?.delegate = self
        
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(AccountViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
        
        txtForename?.setBottomBorder()
        txtSurname?.setBottomBorder()
        txtPhone?.setBottomBorder()
        txtVehicleType?.setBottomBorder()
        txtPassword?.setBottomBorder()
        txtRetype?.setBottomBorder()
        txtCurrentPassword?.setBottomBorder()
        
        //populate rider details
        lblTitleName.text = riderLoggedIn.getFullName()
        lblEmail.text = riderLoggedIn.getEmailAddress()
        txtForename?.text = riderLoggedIn.getForename()
        txtSurname?.text = riderLoggedIn.getSurname()
        txtPhone?.text = riderLoggedIn.getPhoneNumber()
        txtVehicleType?.text = riderLoggedIn.getVehicleType()
        
        // Do any additional setup after loading the view.
    }
    func dismissKeyboard() {
        view.endEditing(true)
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }

    @IBAction func btnSave(_ sender: Any) {
        if txtCurrentPassword?.text == riderLoggedIn.getPassword() {
            //check is password is correct
            if riderLoggedIn.getForename() != txtForename?.text {
                riderLoggedIn.setForename(newForename: (txtForename?.text!)!)
            }
            if riderLoggedIn.getSurname() != txtSurname?.text {
                riderLoggedIn.setSurname(newSurname: (txtSurname?.text!)!)
            }
            if riderLoggedIn.getPhoneNumber() != txtPhone?.text {
                riderLoggedIn.setPhoneNumber(newPhoneNumber: (txtPhone?.text!)!)
            }
            if riderLoggedIn.getVehicleType() != txtVehicleType?.text! {
                riderLoggedIn.setVehicleType(newVehicleType: (txtVehicleType?.text!)!)
            }
            //set new password
            if txtPassword?.text != "" {
                if txtPassword?.text == txtRetype?.text {
                    //if passwords match
                    if (txtPassword?.text?.characters.count)! <= 20 && (txtPassword?.text?.characters.count)! >= 8 {
                        riderLoggedIn.setPassword(newPassword: (txtPassword?.text!)!)
                    } else {
                        let errorAlert = UIAlertController(title: "Error", message: "Password must be between 8 and 20 characters long. Please choose another password.", preferredStyle: .alert)
                        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                        errorAlert.addAction(okAction)
                        DispatchQueue.main.async {
                            self.present(errorAlert, animated: true, completion: nil)
                        }
                        txtCurrentPassword?.text = ""
                        txtPassword?.text = ""
                        txtRetype?.text = ""
                    }
                } else {
                    let errorAlert = UIAlertController(title: "Error", message: "Passwords typed do not match. Please try again.", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    errorAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        self.present(errorAlert, animated: true, completion: nil)
                    }
                    txtCurrentPassword?.text = ""
                    txtPassword?.text = ""
                    txtRetype?.text = ""
                }
            }
            let alert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
            let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
            
            loadingIndicator.hidesWhenStopped = true
            loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
            loadingIndicator.startAnimating()
            
            alert.view.addSubview(loadingIndicator)
            DispatchQueue.main.async {
                self.present(alert, animated: true, completion: nil)
            }
            //Update user details on database
            print(riderLoggedIn.getDOB())
            print(UtilityFunctions.getStringDictionaryFromRiderObject(obj: riderLoggedIn))
            APICommunication.PUTRequest(path: "delivery_rider", id: riderLoggedIn.getRiderID(), params: UtilityFunctions.getStringDictionaryFromRiderObject(obj: riderLoggedIn)) { success in
                print("PUT Successful? \(success)\n")
                //remove alert from screen when api call completed
                DispatchQueue.main.async {
                }
                if success.0 {
                    DispatchQueue.main.async {
                        alert.dismiss(animated: true) {
                            self.performSegue(withIdentifier: "AccountSaveSegue", sender: self)
                        }
                    }
                } else {
                    let errorAlert = UIAlertController(title: "Error", message: "Could not complete PUT request. Code: \(success.1)", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    errorAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        alert.dismiss(animated: true) {
                            self.present(errorAlert, animated: true)
                        }
                    }
                }
            }
        } else {
            let errorAlert = UIAlertController(title: "Error", message: "The password that you have entered is wrong. Please try again.", preferredStyle: .alert)
            let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
            errorAlert.addAction(okAction)
            DispatchQueue.main.async {
                self.present(errorAlert, animated: true)
            }
            txtCurrentPassword?.text = ""
            txtPassword?.text = ""
            txtRetype?.text = ""
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AccountSaveSegue" {
            if let destination = segue.destination as? WelcomeViewController {
                //Pass rider object to WelcomeViewController
                destination.riderLoggedIn = riderLoggedIn
            }
        }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}

extension UITextField {
    func setBottomBorder() {
        self.borderStyle = .none
        self.layer.backgroundColor = UIColor.white.cgColor
        
        self.layer.masksToBounds = false
        self.layer.shadowColor = UIColor.gray.cgColor
        self.layer.shadowOffset = CGSize(width: 0.0, height: 1.0)
        self.layer.shadowOpacity = 1.0
        self.layer.shadowRadius = 0.0
    }
}

