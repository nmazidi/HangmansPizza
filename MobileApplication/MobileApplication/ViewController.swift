//
//  ViewController.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 01/02/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    var dataLoaded = [[String: AnyObject]]()
    @IBOutlet weak var txtEmailAddress: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    @IBOutlet weak var StatView: UIView!
    @IBAction func btnGoLive(_ sender: UIButton) {
        
    }

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
        GetReq() { success in
            print("Successful? \(success.0)\n")
            self.dataLoaded = success.1
            //remove alert from screen when api call completed
            alert.dismiss(animated: false, completion: nil)
            loadingIndicator.stopAnimating()
            
            
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
        
}

