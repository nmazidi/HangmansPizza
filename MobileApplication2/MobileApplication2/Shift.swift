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
    var wageMultiplier : Double
    var riderLocation : String
    var deliveriesMade : Int
    var status : String
    
    /// default constructor of a shift
    init() {
        self.shiftID = 0
        self.riderID = 0
        self.shiftStart = Date()
        self.shiftEnd = Date()
        self.wageMultiplier = 1
        self.riderLocation = "UNKNOWN"
        self.deliveriesMade = 0
        self.status = "UNKNOWN"
    }
    /// Constructor of a shift
    ///
    /// - Parameters:
    ///   - shiftID: Shift ID
    ///   - riderID: Rider's ID
    ///   - shiftStart: Shift's start date time
    ///   - shiftEnd: Shift's end date and time
    ///   - wageMultiplier: wage multiplied by
    ///   - riderLocation: coordinated of the rider
    ///   - deliveriesMade: number of deliveries made
    ///   - status: status of rider
    init(shiftID : Int, riderID : Int, shiftStart : Date, shiftEnd : Date, wageMultiplier : Double, riderLocation : String, deliveriesMade : Int, status : String) {
        self.shiftID = 0
        self.riderID = 0
        self.shiftStart = Date()
        self.shiftEnd = Date()
        self.wageMultiplier = 1
        self.riderLocation = "UNKNOWN"
        self.deliveriesMade = 0
        self.status = "UNKNOWN"
    }
}
