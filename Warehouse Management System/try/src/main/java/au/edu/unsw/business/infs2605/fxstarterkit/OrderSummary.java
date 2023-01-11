package au.edu.unsw.business.infs2605.fxstarterkit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author berse
 */
public class OrderSummary {
    private int orderID;
    private String productName;
    private String status;
    private String quantity;
    
    public OrderSummary (int orderID, String productName, String status, String quantity) {
        this.orderID = orderID;
        this.productName = productName;
        this.status = status;    
    }
    
     public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
}
