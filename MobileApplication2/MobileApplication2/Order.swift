//
//  Order.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 09/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

class Order {
    var orderID: Int
    var customerID: Int
    var datePlaced: Date
    var totalCost: Double
    var orderType: String
    var notes: String
    var orderStatus: String
    var addressLine1: String
    var addressLine2: String
    var postcode: String
    
    ///default constructor of a shift
    init() {
        self.orderID = 0
        self.customerID = 0
        self.datePlaced = Date()
        self.totalCost = 0
        self.orderType = "UNKNOWN"
        self.notes = "none"
        self.orderStatus = "UNKNOWN"
        self.addressLine1 = "UNKNOWN"
        self.addressLine2 = "UNKNOWN"
        self.postcode = "UNKNOWN"
    }
    /// Constructor of a Order class
    ///
    /// - Parameters:
    ///   - orderID: Order's ID
    ///   - customerID: Customer's ID from customer table
    ///   - datePlaced: date order was placed
    ///   - totalCost: total cost of order
    ///   - orderType: type of order (delivery or collection)
    ///   - notes: notes for order
    ///   - orderStatus: status of the order (e.g. out for delivery)
    init(orderID: Int, customerID: Int, datePlaced: Date, totalCost: Double, orderType: String, notes: String, orderStatus: String, addressLine1: String, addressLine2: String, postcode: String) {
        self.orderID = orderID
        self.customerID = customerID
        self.datePlaced = datePlaced
        self.totalCost = totalCost
        self.orderType = orderType
        self.notes = notes
        self.orderStatus = orderStatus
        self.addressLine1 = addressLine1
        self.addressLine2 = addressLine2
        self.postcode = postcode
    }
    func getOrderID() -> Int {
        return self.orderID
    }
    func getCustomerID() -> Int {
        return self.customerID
    }
    func setCustomerID(newCustomerID: Int) {
        self.customerID = newCustomerID
    }
    func getDatePlaced() -> Date {
        return self.datePlaced
    }
    func setDatePlaced(newDatePlaced: Date) {
        self.datePlaced = newDatePlaced
    }
    func getTotalCost() -> Double {
        return self.totalCost
    }
    func setTotalCost(newTotalCost: Double) {
        self.totalCost = newTotalCost
    }
    func getOrderType() -> String {
        return self.orderType
    }
    func setOrderForDelivery() {
        self.orderType = OrderType.DELIVERY.rawValue
    }
    func setOrderForCollection() {
        self.orderType = OrderType.COLLECTION.rawValue
    }
    func getNotes() -> String {
        return self.notes
    }
    func setNotes(newNotes: String) {
        self.notes = newNotes
    }
    func getOrderStatus() -> String {
        return self.orderStatus
    }
    func setStatusAsPlaced() {
        self.orderStatus = OrderStatus.PLACED.rawValue
    }
    func setStatusAsPrep() {
        self.orderStatus = OrderStatus.PREP.rawValue
    }
    func setStatusAsReady() {
        self.orderStatus = OrderStatus.READY.rawValue
    }
    func setStatusAsOutForDelivery() {
        self.orderStatus = OrderStatus.OUTFORDELIVERY.rawValue
    }
    func setStatusAsDelivered() {
        self.orderStatus = OrderStatus.DELIVERED.rawValue
    }
    func setStatusAsCollected() {
        self.orderStatus = OrderStatus.COLLECTED.rawValue
    }
}
