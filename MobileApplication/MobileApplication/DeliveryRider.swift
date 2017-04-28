//
//  DeliveryRider.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 28/04/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class DeliveryRider : Person {
    var riderID : Int {
        get { return self.riderID }
        set (newRiderID) {
            self.riderID = newRiderID
        }
    }
    var password : String {
        get { return self.password }
        set (newPassword) {
            self.password = newPassword
        }
    }
    var vehicleType : String {
        get { return self.vehicleType }
        set (newVehicleType) {
            self.vehicleType = newVehicleType
        }
    }
    
    override init() {
        super.init()
        self.riderID = 0
        self.password = "UNKNOWN"
        self.vehicleType = "UNKNOWN"
    }
    /// Constructor for a delivery rider
    ///
    /// - Parameters:
    ///   - title: rider's title
    ///   - forename: rider's forename
    ///   - surname: rider's surname
    ///   - phoneNumber: rider's phone number
    ///   - emailAddress: rider's email address
    ///   - DOB: rider's date of birth
    ///   - riderID: rider's ID
    ///   - password: rider's password
    ///   - vehicleType: type of vehicle that the rider owns
    init(title: String, forename: String, surname: String, phoneNumber: String, emailAddress: String, DOB: Date, riderID:Int, password:String, vehicleType:String) {
        super.init(title: title, forename: forename, surname: surname, phoneNumber: phoneNumber, emailAddress: emailAddress, DOB: DOB)
        self.riderID = riderID
        self.password = password
        self.vehicleType = vehicleType
        
    }
}
