//
//  Shift.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 06/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class Shift {
    var shiftID : Int
    var riderID : Int
    var shiftStart : Date
    var shiftEnd : Date
    var shiftLength : Int
    var latitude : Float
    var longitude : Float
    var deliveriesMade : Int
    var status : String
    var totalEarned : Double
    
    /// default constructor of a shift
    init() {
        self.shiftID = 0
        self.riderID = 0
        self.shiftStart = Date()
        self.shiftEnd = Date()
        self.shiftLength = 0
        self.latitude = 0
        self.longitude = 0
        self.deliveriesMade = 0
        self.status = "UNKNOWN"
        self.totalEarned = 0
    }
    /// Constructor of a shift
    ///
    /// - Parameters:
    ///   - shiftID: Shift ID
    ///   - riderID: Rider's ID
    ///   - shiftStart: Shift's start date time
    ///   - latitude = Rider's latitude coordinate
    ///   - longitude = Rider's longitude coordinate
    ///   - deliveriesMade: number of deliveries made
    ///   - status: status of rider
    ///   - totalEarned: total amount of money earned in shift
    init(shiftID : Int, riderID : Int, shiftStart : Date, latitude : Float, longitude: Float, deliveriesMade : Int, status : String, totalEarned: Double) {
        self.shiftID = shiftID
        self.riderID = riderID
        self.shiftStart = shiftStart
        self.shiftEnd = Date()
        self.shiftLength = 0
        self.latitude = latitude
        self.longitude = longitude
        self.deliveriesMade = deliveriesMade
        self.status = status
        self.totalEarned = totalEarned
    }
    func getShiftID() -> Int{
        return self.shiftID
    }
    func getRiderID() -> Int{
        return self.riderID
    }
    func setRiderID(newRiderID: Int) {
        self.riderID = newRiderID
    }
    func getShiftStart() -> Date{
        return self.shiftStart
    }
    func getShiftEnd() -> Date{
        return self.shiftEnd
    }
    func endShift() {
        self.shiftEnd = Date()
    }
    func getShiftLength() -> Int {
        return self.shiftLength
    }
    func getLatitude() -> Float{
        return self.latitude
    }
    func getLongitude() -> Float{
        return self.longitude
    }
    func setLatitude(newLatitude: Float) {
        self.latitude = newLatitude
    }
    func setLongitude(newLongitude: Float) {
        self.longitude = newLongitude
    }
    func getDeliveriesMade() -> Int{
        return self.shiftID
    }
    func addDeliveryMade() {
        self.deliveriesMade += 1
    }
    func getStatus() -> String{
        return self.status
    }
    func setStatus(newStatus: String) {
        self.status = newStatus
    }
    func getTotalEarned() -> Double{
        return self.totalEarned
    }
    func addEarning(newEarning: Double) {
        self.totalEarned += newEarning
    }
    func setAvailable() {
        self.status = ShiftStatus.AVAILABLE.rawValue
    }
    func setUnavailable() {
        self.status = ShiftStatus.UNAVAILABLE.rawValue
    }
    func setOffline() {
        self.status = ShiftStatus.OFFLINE.rawValue
    }
}
