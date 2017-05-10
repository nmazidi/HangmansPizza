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
    
    init() {
        self.deliveryID = 0
        self.orderID = 0
        self.dateDelivered = Date()
        self.deliveryNotes = ""
        self.riderID = 0
    }
    init(deliveryID: Int, orderID: Int, dateDelivered: Date, deliveryNotes: String, riderID : Int) {
        self.deliveryID = deliveryID
        self.orderID = orderID
        self.dateDelivered = dateDelivered
        self.deliveryNotes = deliveryNotes
        self.riderID = riderID
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
}
