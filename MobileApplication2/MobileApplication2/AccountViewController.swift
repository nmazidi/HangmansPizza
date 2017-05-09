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
        var passwordVerified: Bool = false
        let firstAlert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating()
        
        firstAlert.view.addSubview(loadingIndicator)
        DispatchQueue.main.async {
            self.present(firstAlert, animated: true, completion: nil)
        }
        riderLoggedIn.verifyPassword(pass: (txtCurrentPassword?.text)!) { success in
            passwordVerified = success
            if passwordVerified == true {
                //check is password is correct
                if self.riderLoggedIn.getForename() != self.txtForename?.text {
                    self.riderLoggedIn.setForename(newForename: (self.txtForename?.text!)!)
                }
                if self.riderLoggedIn.getSurname() != self.txtSurname?.text {
                    self.riderLoggedIn.setSurname(newSurname: (self.txtSurname?.text!)!)
                }
                if self.riderLoggedIn.getPhoneNumber() != self.txtPhone?.text {
                    self.riderLoggedIn.setPhoneNumber(newPhoneNumber: (self.txtPhone?.text!)!)
                }
                if self.riderLoggedIn.getVehicleType() != self.txtVehicleType?.text! {
                    self.riderLoggedIn.setVehicleType(newVehicleType: (self.txtVehicleType?.text!)!)
                }
                //set new password
                if self.txtPassword?.text != "" {
                    if self.txtPassword?.text == self.txtRetype?.text {
                        //if passwords match
                        if (self.txtPassword?.text?.characters.count)! <= 20 && (self.txtPassword?.text?.characters.count)! >= 8 {
                            self.riderLoggedIn.setPassword(newPassword: (self.txtPassword?.text!)!)
                        } else {
                            let errorAlert = UIAlertController(title: "Error", message: "Password must be between 8 and 20 characters long. Please choose another password.", preferredStyle: .alert)
                            let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                            errorAlert.addAction(okAction)
                            DispatchQueue.main.async {
                                firstAlert.dismiss(animated: true) {
                                    self.present(errorAlert, animated: true, completion: nil)
                                }
                            }
                            self.txtCurrentPassword?.text = ""
                            self.txtPassword?.text = ""
                            self.txtRetype?.text = ""
                        }
                    } else {
                        let errorAlert = UIAlertController(title: "Error", message: "Passwords typed do not match. Please try again.", preferredStyle: .alert)
                        let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                        errorAlert.addAction(okAction)
                        DispatchQueue.main.async {
                            firstAlert.dismiss(animated: true) {
                                self.present(errorAlert, animated: true, completion: nil)
                            }
                        }
                        self.txtCurrentPassword?.text = ""
                        self.txtPassword?.text = ""
                        self.txtRetype?.text = ""
                    }
                }
                let alert = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
                let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
                
                loadingIndicator.hidesWhenStopped = true
                loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
                loadingIndicator.startAnimating()
                
                alert.view.addSubview(loadingIndicator)
                DispatchQueue.main.async {
                    firstAlert.dismiss(animated: true) {
                        self.present(alert, animated: true, completion: nil)
                    }
                }
                //Update user details on database
                APICommunication.PUTRequest(path: "delivery_rider", id: self.riderLoggedIn.getRiderID(), params: UtilityFunctions.getStringDictionaryFromObject(obj: self.riderLoggedIn)) { success in
                    print("PUT Successful? \(success)\n")
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
                    firstAlert.dismiss(animated: true) {
                        self.present(errorAlert, animated: true)
                    }
                }
                self.txtCurrentPassword?.text = ""
                self.txtPassword?.text = ""
                self.txtRetype?.text = ""
            }
        }
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "AccountSaveSegue" {
            if let destination = segue.destination as? WelcomeViewController {
                //Pass rider object to WelcomeViewController
                riderLoggedIn.setPassword(newPassword: "HASHED")
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

