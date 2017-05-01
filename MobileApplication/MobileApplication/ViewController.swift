//
//  ViewController.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 01/02/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    var dataa = [[String: AnyObject]]()
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
//        UIApplication.shared.isNetworkActivityIndicatorVisible = true
//        
//        let url = URL(string : "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider")!
//        let sesh = URLSession(configuration: URLSessionConfiguration.default)
//        
//        var request = URLRequest(url: url)
//        request.httpMethod = "GET"
//        
//        let dataTask = sesh.dataTask(with: request as URLRequest) {
//            (data, response, error) in
//            if error != nil {
//                print("an error has occured while calling GET:")
//                print(error!)
//                return
//            }
//            guard let dataReceived = data
//                else {
//                    print("Error! No data received")
//                    return
//                }
//            do {
//                guard let jsonData = try JSONSerialization.jsonObject(with: dataReceived, options: []) as? [[String: AnyObject]]
//                    else {
//                        print("Error! Cannot convert data to JSON")
//                        return
//                    }
//                print(jsonData)
//                print(self.isValidCredentials(jsonArray: jsonData))
//            } catch {
//                print("Error! Cannot convert data to JSON!")
//                return
//            }
//
//            
//        }
//        UIApplication.shared.isNetworkActivityIndicatorVisible = false
//        dataTask.resume()
        GetReq() { success in
            print("Successful? \(success)")
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

