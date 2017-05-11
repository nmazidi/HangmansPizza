/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author amalzard
 */
public class Cart {

    HashMap<String, List<Integer>> cartItems;

    public Cart() {
        cartItems = new HashMap<>();

    }

    public HashMap getCartItems() {
        return cartItems;
    }

    public void addToCart(String itemId, int qty) {
        //cartItems.put(itemId, new qty);
    }

}
