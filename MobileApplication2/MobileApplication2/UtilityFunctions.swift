//
//  UtilityFunctions.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class UtilityFunctions {
    /// Formats given string to a date
    ///
    /// - Parameter dateStr: date string that needs to be formatted
    /// - Returns: formatted date
    static func formatDateForStorage(dateStr: String) -> Date {
        let dateFormatter: DateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        let formattedDate: Date? = dateFormatter.date(from: dateStr)
        return formattedDate!
    }
    /// Formats given date to a string ready for HTTP request
    ///
    /// - Parameter dateObj: date that needs to be formatted
    /// - Returns: formatted date string
    func formatDateForRequest(dateObj: Date) -> String {
        let dateFormatter: DateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd-MMM-yy"
        let formattedDate: String = dateFormatter.string(from: dateObj)
        return formattedDate
    }
    /// Encode parameters for a PUT/POST request
    ///
    /// - Parameter params: dictionary of string values to be encoded
    /// - Returns: encoded string
    static func encodeParameters(params: [String:String]) -> Data {
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
    static func getStringDictionaryFromRiderObject(obj: AnyObject) -> [String: String] {
        let mirroredObject = Mirror(reflecting: obj)
        var dict : [String: String]
        for (index, attr) in mirroredObject.children.enumerated() {
            if let propertyName = attr.label as String! {
                dict[]
            }
        }
    }

}
