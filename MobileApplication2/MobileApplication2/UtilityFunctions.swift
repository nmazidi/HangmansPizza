//
//  UtilityFunctions.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

/// Formats given string to a date 
///
/// - Parameter dateStr: date string that needs to be formatted
/// - Returns: formatted date
func formatDate(){
    func forStorage(dateStr: String) -> Date {
        let dateFormatter: DateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        let formattedDate: Date? = dateFormatter.date(from: dateStr)
        return formattedDate! 
    }
    func forRequest(dateObj: Date) -> String {
        let dateFormatter: DateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd-MMM-yy"
        let formattedDate: String = dateFormatter.string(from: dateObj)
        return formattedDate
    }
}

/// Encode parameters for a PUT/POST request
///
/// - Parameter params: dictionary of string values to be encoded
/// - Returns: encoded string 
func encodeParameters(params: [String:String]) -> Data {
    let paramsArray = params.map {
        (key,value) -> String in
        return "\(key)=\(value)"
    }
    return paramsArray.joined(separator: "&").data(using: String.Encoding.utf8)!
}

/// Turns object, such as a delivery rider, into a dictionary of strings
///
/// - Parameter obj: object to convert
/// - Returns: dictionary of strings
func getStringDictionaryFromObject(obj: AnyObject) -> [String: String] {
    
}
