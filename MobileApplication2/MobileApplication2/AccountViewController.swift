//
//  AccountViewController.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 03/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class AccountViewController: UIViewController {
    var riderLoggedIn = DeliveryRider()
    
    @IBOutlet weak var lblTitleName: UILabel!
    @IBOutlet weak var lblEmail: UILabel!
    @IBOutlet weak var txtForename: UITextField!
    @IBOutlet weak var txtSurname: UITextField!
    @IBOutlet weak var txtPhone: UITextField!
    @IBOutlet weak var txtVehicleType: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    @IBOutlet weak var txtRetype: UITextField!
    @IBOutlet weak var txtCurrentPassword: UITextField!


    override func viewDidLoad() {
        super.viewDidLoad()
        txtForename.setBottomBorder()
        txtSurname.setBottomBorder()
        txtPhone.setBottomBorder()
        txtVehicleType.setBottomBorder()
        txtPassword.setBottomBorder()
        txtRetype.setBottomBorder()
        txtCurrentPassword.setBottomBorder()
        
        //populate rider details
        lblTitleName.text = riderLoggedIn.getFullName()
        lblEmail.text = riderLoggedIn.getEmailAddress()
        txtForename.text = riderLoggedIn.getForename()
        txtSurname.text = riderLoggedIn.getSurname()
        txtPhone.text = riderLoggedIn.getPhoneNumber()
        txtVehicleType.text = riderLoggedIn.getVehicleType()
        
        // Do any additional setup after loading the view.
    }
    @IBAction func btnSave(_ sender: Any) {
        if txtCurrentPassword.text == riderLoggedIn.getPassword() {
            
            //check is password is correct
            if riderLoggedIn.getForename() != txtForename.text {
                riderLoggedIn.setForename(newForename: txtForename.text!)
            }
            if riderLoggedIn.getSurname() != txtSurname.text {
                riderLoggedIn.setSurname(newSurname: txtSurname.text!)
            }
            if riderLoggedIn.getPhoneNumber() != txtPhone.text {
                riderLoggedIn.setPhoneNumber(newPhoneNumber: txtPhone.text!)
            }
            if riderLoggedIn.getVehicleType() != txtVehicleType.text! {
                riderLoggedIn.setVehicleType(newVehicleType: txtVehicleType.text!)
            }
            //set new password
            if txtPassword.text != "" {
                if txtPassword.text == txtRetype.text {
                    //if passwords match
                    if (txtPassword.text?.characters.count)! <= 20 && (txtPassword.text?.characters.count)! >= 8 {
                        riderLoggedIn.setPassword(newPassword: txtPassword.text!)
                    }
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
                    alert.dismiss(animated: false, completion: nil)
                    loadingIndicator.stopAnimating()
                }
                if success.0 {
                    DispatchQueue.main.async {
                        self.performSegue(withIdentifier: "AccountSaveSegue", sender: self)
                    }
                } else {
                    let errorAlert = UIAlertController(title: "Error", message: "Could not complete PUT request. Code: \(success.1)", preferredStyle: .alert)
                    let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default)
                    errorAlert.addAction(okAction)
                    DispatchQueue.main.async {
                        self.present(errorAlert, animated: true, completion: nil)
                    }

                }
            }
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

