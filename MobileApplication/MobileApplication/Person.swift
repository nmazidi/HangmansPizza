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
    var title : String {
        get { return self.title }
        set(newTitle) {
            self.title = newTitle
        }
    }
    var forename : String {
        get { return self.forename }
        set(newForename) {
            self.forename = newForename
        }
    }
    var surname : String {
        get { return self.surname }
        set(newSurname) {
            self.surname = newSurname
        }
    }
    var phoneNumber : String {
        get { return self.phoneNumber }
        set(newPhoneNumber) {
            self.phoneNumber = newPhoneNumber
        }
    }
    var emailAddress : String {
        get { return self.emailAddress }
        set(newEmailAddress) {
            self.emailAddress = newEmailAddress
        }
    }
    var DOB : Date {
        get { return self.DOB }
        set(newDOB) {
            self.DOB = newDOB
        }
    }
    
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

    func getFullName() -> String {
        return "\(forename) \(surname)"
    }
    
}
