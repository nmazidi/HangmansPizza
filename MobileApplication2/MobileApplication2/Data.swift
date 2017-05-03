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


    
