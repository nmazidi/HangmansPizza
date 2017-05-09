//
//  OrderStatus.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 09/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

enum OrderStatus : String {
    case PLACED = "PLACED"
    case PREP = "PREP"
    case READY = "READY"
    case OUTFORDELIVERY = "OUTFORDELIVERY"
    case DELIVERED = "DELIVERED"
    case COLLECTED = "COLLECTED"
}
