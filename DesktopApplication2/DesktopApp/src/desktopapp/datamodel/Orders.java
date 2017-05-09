/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;
import java.util.Date;
/**
 *
 * @author Nathan
 */
public class Orders {
    private int orderID;
    private int customerID;
    private Date datePlaced = new Date();
    private Double totalCost;
    private String orderType;
    private String notes;
    private String orderStatus;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }    

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
            
    public Orders(){
    }
    
    public Orders(int ordID, int custID, Date datePlace, Double cost,
            String ordType, String note, String ordStatus){
        this.orderID = ordID;
        this.customerID = custID;
        this.datePlaced = datePlace;
        this.totalCost = cost;
        this.orderType = ordType;
        this.notes = note;
        this.orderStatus = ordStatus;
    }
    
    @Override
    public String toString()
    {
        return "Orders [orderID=" + orderID + ", customerID=" + customerID + ", "
                + "datePlaced=" + datePlaced + "totalCost=" + totalCost + ", "
                + "orderType=" + orderType + ", notes=" + notes + ", orderStatus=" + orderStatus + "]";                
    }
    
}
