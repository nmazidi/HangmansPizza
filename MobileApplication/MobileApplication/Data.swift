//
//  Data.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 26/03/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation



func GetRequest() {
    var test = [[String: AnyObject]]()
    let url = URL(string : "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider")!
    let sesh = URLSession(configuration: URLSessionConfiguration.default)
    var request = URLRequest(url: url)
    request.httpMethod = "GET"
    
    let dataTask = sesh.dataTask(with: request as URLRequest) {
        (data, response, error) in
        if error != nil {
            print("an error has occured while calling GET:")
            print(error!)
            return
        }
        guard let dataReceived = data
            else {
                print("Error! No data received")
                return
        }
        do {
            guard let jsonData = try JSONSerialization.jsonObject(with: dataReceived, options: []) as? [[String: AnyObject]]
                else {
                    print("Error! Cannot convert data to JSON")
                    return
            }
            //print(jsonData)
            test = jsonData
        } catch {
            print("Error! Cannot convert data to JSON!")
            return
        }
        
    }
    dataTask.resume()
    print(test)
}

func convertToDictionary(str:String) -> [String: Any]? {
    if let data = str.data(using: .utf8) {
        do {
            return try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any]
        } catch {
            print(error.localizedDescription)
        }
    }
    return nil
}

    
