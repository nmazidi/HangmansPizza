/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author amalzard
 */
public class CartItem {
    protected String itemName;
    protected Integer itemQuantity;
    protected Integer itemCartIndex;
    protected Double cartSubtotal;
    protected Double itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Integer getItemCartIndex() {
        return itemCartIndex;
    }

    public void setItemCartIndex(Integer itemCartIndex) {
        this.itemCartIndex = itemCartIndex;
    }

    public Double getCartSubtotal() {
        return cartSubtotal;
    }

    public void setCartSubtotal(Double cartSubtotal) {
        this.cartSubtotal = cartSubtotal;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
}
