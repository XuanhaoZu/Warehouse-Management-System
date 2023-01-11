/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;
/**
 *
 * @author berse
 */
public class Supplier {
    private int supplierID; 
    private String supplierName;
    private String phoneNumber;
    private String address;
    
    
    public Supplier(int supplierID, String supplierName, String phoneNumber, String address) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public Supplier() {
        
    }
    
    public int getSupplierID() {
        return supplierID;
    }
    
    public String getSupplierName() {
        return supplierName;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
    
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
}
