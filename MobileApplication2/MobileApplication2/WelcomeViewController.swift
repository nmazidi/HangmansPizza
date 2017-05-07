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
    @IBOutlet weak var lblWelcome: UILabel!
    
    @IBAction func btnGoLive(_ sender: Any) {
        let newShift = Shift(shiftID: 1, riderID: riderLoggedIn.getRiderID(), shiftStart: Date(), riderLocation: "", deliveriesMade: 0, status: "Available", totalEarned: 0)
        
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
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SignedOutSegue" {
            if let destination = segue.destination as? LoginViewController {
                //Pass rider object to WelcomeViewController
                destination.riderLoggedIn.resetRider()
            }
        } else if segue.identifier == "AccountSegue" {
            if let destination = segue.destination as? AccountViewController {
                //Pass rider object to WelcomeViewController
                destination.riderLoggedIn = self.riderLoggedIn
            }
        }
    }

}
