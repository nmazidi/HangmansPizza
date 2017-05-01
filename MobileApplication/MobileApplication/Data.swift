//
//  Data.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 26/03/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation



func GetReq(completionHandler: @escaping (Bool) -> ()) {
    
    var getData = [[String: AnyObject]]()
    let url:String = "http://xserve.uopnet.plymouth.ac.uk/Modules/INTPROJ/PRCS251Q/api/delivery_rider"
    let urlRequest = URL(string: url)
    
    URLSession.shared.dataTask(with: urlRequest!, completionHandler: {
        (data, response, error) in
        
        if (error != nil){
            print(error.debugDescription)
            completionHandler(false)
        } else {
            do{
                getData = try JSONSerialization.jsonObject(with: data!, options: .allowFragments) as! [[String: AnyObject]]
                completionHandler(true)
            } catch let error as NSError {
                print(error)
                completionHandler(false)
            }
        }
    }).resume()
    
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

    
