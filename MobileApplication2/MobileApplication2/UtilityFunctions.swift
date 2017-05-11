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
    static func formatDateForRequest(dateObj: Date) -> String {
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
        let paramsArray = UtilityFunctions.changeKeysForRequest(dict: params).map {
            (key,value) -> String in
            return "\(key)=\(value)"
        }
        print(paramsArray.joined(separator: "&"))
        return paramsArray.joined(separator: "&").data(using: String.Encoding.utf8)!
        
    }
    static func encodeLoginParameters(params: [String:String]) -> Data {
        let paramsArray = params.map {
            (key,value) -> String in
            return "\(key)=\(value)"
        }
        print(paramsArray.joined(separator: "&"))
        return paramsArray.joined(separator: "&").data(using: String.Encoding.utf8)!
        
    }
    
    /// Turns object, such as a delivery rider, into a dictionary of strings
    ///
    /// - Parameter obj: object to convert
    /// - Returns: dictionary of strings
    static func getStringDictionaryFromObject(obj: AnyObject) -> [String: String] {
        let dict = Mirror(reflecting: obj).toDictionary()
        return dict
    }
    static func changeKeysForRequest(dict: [String: String]) -> [String: String] {
        var updatedDict = dict
        updatedDict.updateKey(from: "title", to: "RIDER_TITLE")
        updatedDict.updateKey(from: "forename", to: "RIDER_FORENAME")
        updatedDict.updateKey(from: "surname", to: "RIDER_SURNAME")
        updatedDict.updateKey(from: "phoneNumber", to: "RIDER_PHONE")
        updatedDict.updateKey(from: "emailAddress", to: "RIDER_EMAIL")
        updatedDict.updateKey(from: "DOB", to: "RIDER_DOB")
        updatedDict.updateKey(from: "riderID", to: "RIDER_ID")
        updatedDict.updateKey(from: "password", to: "RIDER_PASSWORD")
        updatedDict.updateKey(from: "vehicleType", to: "VEHICLE_TYPE")
        updatedDict.updateKey(from: "shiftID", to: "SHIFT_ID")
        updatedDict.updateKey(from: "shiftStart", to: "SHIFT_START")
        updatedDict.updateKey(from: "shiftEnd", to: "SHIFT_END")
        updatedDict.updateKey(from: "shiftLength", to: "SHIFT_LENGTH")
        updatedDict.updateKey(from: "latitude", to: "LATITUDE")
        updatedDict.updateKey(from: "longitude", to: "LONGITUDE")
        updatedDict.updateKey(from: "deliveriesMade", to: "DELIVERIES_MADE")
        updatedDict.updateKey(from: "status", to: "STATUS")
        updatedDict.updateKey(from: "totalEarned", to: "TOTAL_EARNED")
        updatedDict.updateKey(from: "deliveryNotes", to: "DELIVERY_NOTES")
        updatedDict.updateKey(from: "deliveryID", to: "DELIVERY_ID")
        updatedDict.updateKey(from: "orderID", to: "ORDER_ID")
        updatedDict.updateKey(from: "dateDelivered", to: "DATE_DELIVERED")
        updatedDict.updateKey(from: "deliveryStatus", to: "DELIVERY_STATUS")
        updatedDict.updateKey(from: "customerID", to: "CUSTOMER_ID")
        updatedDict.updateKey(from: "addressID", to: "ADDRESS_ID")
        return updatedDict
    }
}
extension Mirror {
    func toDictionary() -> [String: String] {
        var dict = [String: String]()
        for attr in self.children {
            if let propertyName = attr.label as String! {
                if attr.value is Date {
                    let propertyVal = UtilityFunctions.formatDateForRequest(dateObj: attr.value as! Date)
                        dict[propertyName] = propertyVal
                } else if attr.value is Int {
                    let propertyVal = "\(attr.value)"
                    dict[propertyName] = propertyVal
                } else if attr.value is Double {
                    let propertyVal = "\(attr.value)"
                    dict[propertyName] = propertyVal
                } else if attr.value is Float {
                    let propertyVal = "\(attr.value)"
                    dict[propertyName] = propertyVal
                } else {
                    if let propertyVal = attr.value as? String {
                        dict[propertyName] = propertyVal
                    }
                }
            }
        }
        if let parent = self.superclassMirror {
            for (propertyName, value) in parent.toDictionary() {
                dict[propertyName] = value
            }
        }
        return dict
    }
}
extension Dictionary {
    mutating func updateKey(from:Key, to:Key) {
        self[to] = self[from]
        self.removeValue(forKey: from)
    }
}
