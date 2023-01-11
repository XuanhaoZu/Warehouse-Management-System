# Supermarket Warehouse Management System

**Login Screen**  
   For login screen, there are 4 accounts you can login with. There 2 types of account: manager and employee  
                1. type: manager   
                   username: michael1234  
                   password: carrot  
                2. type: employee  
                   username: will2345  
                   password: banana  
                3. type: employee  
                   username: david4321  
                   password: orange   
                4. type: employee  
                   username: kurtley3123  
                   password: pineapple  
                5. type: employee  
                   username: tony4444  
                   password: sohardhomework  
                   
 **Register screen** (AF3)
     For the register screen, the user can create a new account. 
     The user has to enter their full name, username and password into the respective text fields. 

**Top navigation bar** (after login)  
    In the right side:  
    'About' - About Screen  
    'Logout' - logout the account and back to login screen  
    If you change the scrren to About Screen, it will be 'DataManage' for datamanage screen  
    

**Data Management Screen**   
***1. Side navigation bar***  
    'SUPPLIER LIST' - Supplier List Pane  
    'CREATE ORDER' - Create/delete order Pane   
    'ORDER DETAILS' - Order Summary Pane  
    'UPDATE ORDER' - Update order Pnae  
    
***2. Supplier List Pane***    
    Add supplier: type the right ID, Supplier name, Phone number and Address  
    Delete: Click the row you want to delete and click the 'delete' button  
    Edit: Double click the cell you want to change and type the right information  
    
***3.Create Order(Create/Delete Order Pane)***     
*please type the password in 'password' field at first*  
    Add order: type the right product name, quantity, supplier name and timestamp. Click 'Add' button. It will switch to a new table like a shopping cart. You can other product and click 'add' button. Once you finish adding, Click 'ADD ORDER TO TABLE' button then your order will be added to database and table. The status is set to 'order placed' by default - AF2  
    Delete: Click the row you want to delete and click the 'delete' button   
    Edit: Double click the cell you want to change and type the right information (reminder: only the employee and order from same supplier can change AND manager can changer all information ) - AF1  
    Refresh Table: Click the refesh table button. It will give the table after you change   
   
***4. Order Detail(Order Summary Pane)***  
    Search: Type right Status('Order Placed' OR 'Order Completed') OR OrderID  
    Pie Chart is shown  
    
***5. Update Order Pane*** (AF1)
    Update the order status of an order by writing your supplier's ID, your username and password into the respective text fields, and double-clicking on the order status cell to         change it.  

**About Screen**    
***1. Side navigation bar***   
    'Copyright' - CopyRight Pane  
    'FAQ' - FAQ Pane  
    
***2.CopyRight Pane***  
    Show the 'about' content  

***3. FAQ***  
    Frequently asked questions   
    
**Actions to ensure the application runs correctly**
    Delete the SQLite database before starting the application. 
    Run the application twice.
    Run the application by right-clicking on the project folder and selecting 'Run'.
    
   
