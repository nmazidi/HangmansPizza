//
//  Address.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 10/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class Address {
    var addressID: Int
    var addressLine1: String
    var addressLine2: String
    var town: String
    var postcode: String
    
    /// Default constructor for address
    init() {
        self.addressID = 0
        self.addressLine1 = "UNKNOWN"
        self.addressLine2 = "UNKNOWN"
        self.town = "UNKNOWN"
        self.postcode = "UNKNOWN"
    }
    /// Constructor for address
    ///
    /// - Parameters:
    ///   - addressID: Address' ID
    ///   - addressLine1: line 1 of the address
    ///   - addressLine2: line 2 of the address
    ///   - town: town the address is in
    ///   - postcode: postcode
    init(addressID: Int, addressLine1: String, addressLine2: String, town: String, postcode: String) {
        self.addressID = addressID
        self.addressLine1 = addressLine1
        self.addressLine2 = addressLine2
        self.town = town
        self.postcode = postcode
    }
    func getAddressString() -> String {
        return "\(addressLine1), \(addressLine2), \(town), \(postcode)"
    }
    func getAddressID() -> Int {
        return self.addressID
    }
    func setAddressID(newAddressID: Int) {
        self.addressID = newAddressID
    }
    func getAddressLine1() -> String {
        return self.addressLine1
    }
    func setAddressLine1(newAddressLine2: String) {
        self.addressLine2 = newAddressLine2
    }
    func getAddressLine2() -> String {
        return self.addressLine2
    }
    func setAddressLine2(newAddressLine2: String) {
        self.addressLine2 = newAddressLine2
    }
    func getTown() -> String {
        return self.town
    }
    func setTown(newTown: String) {
        self.town = newTown
    }
    func getPostcode() -> String {
        return self.postcode
    }
    func setPostcode(newPostcode: String) {
        self.postcode = newPostcode
    }
}
