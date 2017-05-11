//
//  Delivery.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 10/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class Delivery {
    var deliveryID: Int
    var orderID: Int
    var dateDelivered: Date
    var deliveryNotes: String
    var riderID : Int
    var deliveryStatus: String
    
    /// Default constructor of Delivery
    init() {
        self.deliveryID = 0
        self.orderID = 0
        self.dateDelivered = Date()
        self.deliveryNotes = ""
        self.riderID = 0
        self.deliveryStatus = ""
    }
    /// Constructor of a delivery
    ///
    /// - Parameters:
    ///   - deliveryID: Delivery's ID
    ///   - orderID: order's ID
    ///   - dateDelivered: date that the delivery was completed
    ///   - deliveryNotes: any notes that have been added by the customer or chefs
    ///   - riderID: rider that is completing the delivery
    ///   - deliveryStatus: current status of the delivery
    init(deliveryID: Int, orderID: Int, dateDelivered: Date, deliveryNotes: String, riderID : Int, deliveryStatus: String) {
        self.deliveryID = deliveryID
        self.orderID = orderID
        self.dateDelivered = dateDelivered
        self.deliveryNotes = deliveryNotes
        self.riderID = riderID
        self.deliveryStatus = deliveryStatus
    }
    func getDeliveryID() -> Int {
        return self.deliveryID
    }
    func setDeliveryID(newDeliveryID: Int) {
        self.deliveryID = newDeliveryID
    }
    func getOrderID() -> Int {
        return self.orderID
    }
    func setOrderID(newOrderID: Int) {
        self.orderID = newOrderID
    }
    func getDateDelivered() -> Date {
        return self.dateDelivered
    }
    func setDateDelivered(){
        self.dateDelivered = Date()
    }
    func getDeliveryNotes() -> String {
        return self.deliveryNotes
    }
    func setDeliveryNotes(newDeliveryNotes: String) {
        self.deliveryNotes = newDeliveryNotes
    }
    func getRiderID() -> Int {
        return self.riderID
    }
    func setRiderID(newRiderID: Int) {
        self.riderID = newRiderID
    }
    func getDeliveryStatus() -> String {
        return self.deliveryStatus
    }
    func setDeliveryStatus(newDeliveryStatus: String) {
        self.deliveryStatus = newDeliveryStatus
    }
}
