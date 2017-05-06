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
