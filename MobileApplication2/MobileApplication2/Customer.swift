//
//  Customer.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 11/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class Customer : Person {
    var customerID : Int
    var addressID : Int
    
    override init() {
        self.customerID = 0
        self.addressID = 0
        super.init()
    }
    
    init(title: String, forename: String, surname: String, phoneNumber: String, emailAddress: String, DOB: Date, customerID:Int, addressID:Int) {
        self.customerID = customerID
        self.addressID = addressID
        super.init(title: title, forename: forename, surname: surname, phoneNumber: phoneNumber, emailAddress: emailAddress, DOB: DOB)
    }
    func getCustomerID() -> Int{
        return self.customerID
    }
    func setCustomerID(newCustomerID: Int) {
        self.customerID = newCustomerID
    }
    func getAddressID() -> Int{
        return self.customerID
    }
    func setAddressID(newAddressID: Int) {
        self.addressID = newAddressID
    }
}
