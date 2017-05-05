//
//  Data.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 26/03/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

func GetRequest(completionHandler: @escaping (Bool, [[String: AnyObject]]) -> ()) {
    
    var getData = [[String: AnyObject]]()
    let url:String = "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider"
    let urlRequest = URL(string: url)
    
    URLSession.shared.dataTask(with: urlRequest!, completionHandler: {
        (data, response, error) in
        
        if (error != nil){
            print(error.debugDescription)
            completionHandler(false, getData)
        } else {
            do{
                getData = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as! [[String: AnyObject]]
                completionHandler(true, getData)
            } catch let error as NSError {
                print(error)
                completionHandler(false, getData)
            }
        }
    }).resume()
    
}
func PutRequest() {
    let url = URL(string: "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider/4")
    var request = URLRequest(url: url! as URL)
    request.httpMethod = "PUT"
    
    request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
    request.setValue("application/json", forHTTPHeaderField: "Accept")
    
    //let session = URLSession(configuration: URLSessionConfiguration.default, delegate: nil, delegateQueue: nil)
    let data = "RIDER_ID=4&RIDER_FORENAME=Naj&RIDER_SURNAME=Maz&RIDER_EMAIL=test@gmail.com&RIDER_PHONE=07474306999&RIDER_PASSWORD=test&VEHICLE_TYPE=Bike&RIDER_DOB=30-MAR-97".data(using: String.Encoding.utf8)
    request.httpBody = data
    let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
        if error != nil {
            print(error.debugDescription)
        } else {
            let httpresponse = response as? HTTPURLResponse
            print(httpresponse)
        }
    }
    task.resume()
    
//    URLSession.shared.dataTask(with: request, completionHandler: {
//        (data:Data?, response:URLResponse?, error:Error?) -> Void in
//        if (error != nil) {
//            print("Error: \(error.debugDescription)")
//            return
//        } else {
//            do {
//                let jsonData = try JSONSerialization.jsonObject(with: data!, options: .mutableContainers) as? NSDictionary
//                print("1")
//                if let parseJSON = jsonData {
//                    let userID = parseJSON["RIDER_ID"] as? String
//                    print("2")
//                    if (userID != nil) {
//                        
//                    } else {
//                        let errorMessage = parseJSON["message"] as? String
//                        print("3")
//                        print(errorMessage)
//                    }
//                }
//            } catch let error as NSError {
//                print("err \(error)")
//            }
//        }
//    }).resume()
}
func PostRequest() {
    let url = URL(string: "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider")
    var request = URLRequest(url: url! as URL)
    request.httpMethod = "POST"
    
    request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
    request.setValue("application/json", forHTTPHeaderField: "Accept")
    
    //let session = URLSession(configuration: URLSessionConfiguration.default, delegate: nil, delegateQueue: nil)
    let data = "RIDER_ID=4&RIDER_FORENAME=Naj&RIDER_SURNAME=Maz&RIDER_EMAIL=test@gmail.com&RIDER_PHONE=07474306999&RIDER_PASSWORD=test&VEHICLE_TYPE=Bike&RIDER_DOB=30-MAR-97".data(using: String.Encoding.utf8)
    request.httpBody = data
    let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
        if error != nil {
            print(error.debugDescription)
        } else {
            let httpresponse = response as? HTTPURLResponse
            print(httpresponse)
        }
    }
    task.resume()}


    
