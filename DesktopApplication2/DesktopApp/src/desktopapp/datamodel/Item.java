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
public class Item {
    private int itemID;
    private String itemName;
    private String itemType;
    private String sellingPrice; 
    
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    
    public Item() {
    }
    
    public Item(int iteID, String iteName, String iteType, String price) {
        this.itemID = iteID;
        this.itemName = iteName;
        this.itemType = iteType;
        this.sellingPrice = price;    
    }
    
     @Override
    public String toString()
    {
        return "Item [itemID=" + itemID + ", itemName=" + itemName + ", "
                + "itemType=" + itemType + "sellingPrice=" + sellingPrice + "]";                
    }
}
