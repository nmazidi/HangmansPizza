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
    private Integer orderID;
    private Integer customerID;
    private Date datePlaced = new Date();
    private Date dateRequested = new Date();
    private Double totalCost;
    private String orderType;
    private String notes;
    private String orderStatus;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getCustomerID() {
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

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
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
    
    public Orders(Integer ordID, Integer custID, Date datePlace, Date dateRequest, Double cost,
            String ordType, String note, String ordStatus){
        this.orderID = ordID;
        this.customerID = custID;
        this.datePlaced = datePlace;
        this.dateRequested = dateRequest;
        this.totalCost = cost;
        this.orderType = ordType;
        this.notes = note;
        this.orderStatus = ordStatus;
    }
    
    @Override
    public String toString()
    {
        return "Orders [orderID=" + orderID + ", customerID=" + customerID + ", "
                + "datePlaced=" + datePlaced + ", dateRequested=" + dateRequested + ", "
                + "totalCost=" + totalCost + ", orderType=" + orderType + ", "
                + "notes=" + notes + ", orderStatus=" + orderStatus + "]";                
    }
    
}
