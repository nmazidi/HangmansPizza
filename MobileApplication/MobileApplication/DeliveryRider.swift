//
//  DeliveryRider.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 28/04/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class DeliveryRider : Person {
    var riderID : Int
    var password : String
    var vehicleType : String
    
    override init() {
        self.riderID = 0
        self.password = "UNKNOWN"
        self.vehicleType = "UNKNOWN"
        super.init()
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
        self.riderID = riderID
        self.password = password
        self.vehicleType = vehicleType
        super.init(title: title, forename: forename, surname: surname, phoneNumber: phoneNumber, emailAddress: emailAddress, DOB: DOB)
    }
    func getRiderID() -> Int{
        return self.riderID
    }
    func setRiderID(newRiderID: Int) {
        self.riderID = newRiderID
    }
    func getPassword() -> String{
        return self.password
    }
    func setPassword(newPassword: String) {
        self.password = newPassword
    }
    func getVehicleType() -> String{
        return self.vehicleType
    }
    func setVehicleType(newVehicleType: String) {
        self.vehicleType = newVehicleType
    }

}
