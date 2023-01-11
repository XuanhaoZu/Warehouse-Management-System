/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

/**
 *
 * @author 91296
 */
public class OrderStatusUpdate {
    private int employeeID;
    private int supplierID;
    private int orderID;
    private String status;
    private String timestamp;

    public OrderStatusUpdate (int employeeID, int supplierID, int orderID, String status, String timestamp) {
        this.employeeID = employeeID;
        this.supplierID = supplierID;
        this.orderID = orderID;
        this.status = status;
        this.timestamp = timestamp;
               
    }
    
    public OrderStatusUpdate (){
       
    }
    
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    public int getSupplierID() {
        return supplierID;
    }
    
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
    
    public int getOrderID() {
        return orderID;
    }
    
    public void setOrderID(int orderID){
        this.orderID=orderID;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
}
