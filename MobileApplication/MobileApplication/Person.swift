//
//  Person.swift
//  MobileApplication
//
//  Created by Najim Mazidi on 28/04/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

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
    init(title:String, forename:String, surname:String, phoneNumber:String, emailAddress:String, DOB:Date) {
        self.title = title
        self.forename = forename
        self.surname = surname
        self.phoneNumber = phoneNumber
        self.emailAddress = emailAddress
        self.DOB = DOB
    }
    
    
    }
