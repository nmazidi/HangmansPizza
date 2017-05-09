//
//  OrderType.swift
//  MobileApplication2
//
//  Created by Najim Mazidi on 09/05/2017.
//  Copyright © 2017 PRCS251-Q. All rights reserved.
//

import Foundation

/// types of Orders
///
/// - DELIVERY: Order that needs to be delivered
/// - COLLECTION: order will be collected by customer so doesnt need to be delivered
enum OrderType: String {
    case DELIVERY = "Delivery"
    case COLLECTION = "Collection"
}
