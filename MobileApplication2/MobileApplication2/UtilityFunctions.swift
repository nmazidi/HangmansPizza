//
//  UtilityFunctions.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 02/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

/// Formats given string to a date 
///
/// - Parameter dateStr: date string that needs to be formatted
/// - Returns: formatted date
func formatDate(dateStr: String) -> Date {
    let dateFormatter: DateFormatter = DateFormatter()
    dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
    let formattedDate: Date? = dateFormatter.date(from: dateStr)
    return formattedDate!
}
