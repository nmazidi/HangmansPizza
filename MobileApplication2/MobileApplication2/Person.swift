//
//  Person.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 28/04/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

/// Super class which gives DeliveryRider its base properties
class Person {
    var title : String
    var forename : String
    var surname : String
    var phoneNumber : String
    var emailAddress : String
    var DOB : Date
    
    /**
     Default constructor of a person
     */
    init() {
        self.title = "UNKNOWN"
        self.forename = "UNKNOWN"
        self.surname = "UNKNOWN"
        self.phoneNumber = "UNKNOWN"
        self.emailAddress = "UNKNOWN"
        self.DOB = Date()
    }
    /// Constructor for a person
    ///
    /// - Parameters:
    ///   - title: person title
    ///   - forename: person forename
    ///   - surname: person surname
    ///   - phoneNumber: person phone number
    ///   - emailAddress: person email address
    ///   - DOB: person date of birth
    init(title:String, forename:String, surname:String, phoneNumber:String, emailAddress:String, DOB:Date) {
        self.title = title
        self.forename = forename
        self.surname = surname
        self.phoneNumber = phoneNumber
        self.emailAddress = emailAddress
        self.DOB = DOB
    }
    
    /// Getters and Setters for all properties
    func getTitle() -> String{
        return self.title
    }
    func setTitle(newTitle: String) {
        self.title = newTitle
    }
    func getForename() -> String{
        return self.forename
    }
    func setForename(newForename: String) {
        self.forename = newForename
    }
    func getSurname() -> String{
        return self.surname
    }
    func setSurname(newSurname: String) {
        self.surname = newSurname
    }
    func getPhoneNumber() -> String{
        return self.phoneNumber
    }
    func setPhoneNumber(newPhoneNumber: String) {
        self.phoneNumber = newPhoneNumber
    }
    func getEmailAddress() -> String{
        return self.emailAddress
    }
    func setEmailAddress(newEmailAddress: String) {
        self.emailAddress = newEmailAddress
    }
    func getDOB() -> Date{
        return self.DOB
    }
    func setDOB(newDOB: Date) {
        self.DOB = newDOB
    }
    func getFullName() -> String {
        return "\(forename) \(surname)"
    }
    
}
