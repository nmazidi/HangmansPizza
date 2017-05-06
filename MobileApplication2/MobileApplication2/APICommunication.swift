//
//  Data.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 26/03/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class APICommunication {
    static func GETRequest(path: String, completionHandler: @escaping (Bool, [[String: AnyObject]]) -> ()) {
        
        var getData = [[String: AnyObject]]()
        let url:String = "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/\(path)"
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
    
    static func GETRequestByID(path: String, id: Int, completionHandler: @escaping (Bool, [[String: AnyObject]]) -> ()) {
        
        var getData = [[String: AnyObject]]()
        let url:String = "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/\(path)/\(id)"
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
    
    static func PUTRequest(path: String, id: Int, params: [String: String], completionHandler: @escaping (Bool,Int) -> ()) {
        let url = URL(string: "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/\(path)/\(id)")
        var request = URLRequest(url: url! as URL)
        request.httpMethod = "PUT"
        
        request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        request.httpBody = UtilityFunctions.encodeParameters(params: params)
        print(UtilityFunctions.encodeParameters(params: params))
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error != nil {
                print(error.debugDescription)
            } else {
                let httpresponse = response as? HTTPURLResponse
                print(httpresponse as Any)
                if httpresponse?.statusCode == 204 {
                    completionHandler(true, (httpresponse?.statusCode)!)
                } else {
                    
                    completionHandler(false, (httpresponse?.statusCode)!)
                }
            }
        }
        task.resume()
    }
    
    static func POSTRequest(path: String, params: [String: String]) {
        let url = URL(string: "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/\(path)")
        var request = URLRequest(url: url! as URL)
        request.httpMethod = "POST"
        
        request.addValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        request.httpBody = UtilityFunctions.encodeParameters(params: params)
        let task = URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error != nil {
                print(error.debugDescription)
            } else {
                let httpresponse = response as? HTTPURLResponse
                print(httpresponse as Any)
            }
        }
        task.resume()
    }
}

