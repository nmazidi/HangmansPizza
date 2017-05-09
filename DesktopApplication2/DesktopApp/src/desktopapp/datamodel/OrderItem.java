/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

/**
 *
 * @author Nathan
 */
public class OrderItem {
    private String itemName;
    private int orderID;
    private int quantity;
    
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
        
    public OrderItem() {
    }
    
    public OrderItem (String item, int ordID, int quant) {
        this.itemName = item;
        this.orderID = ordID;
        this.quantity = quant;        
    }   
       
    @Override
    public String toString()
    {
        return "OrderItem [itemName=" + itemName + ", orderID=" + orderID + ", "
                + "quantity=" + quantity + "]";                
    }
    
    
}
