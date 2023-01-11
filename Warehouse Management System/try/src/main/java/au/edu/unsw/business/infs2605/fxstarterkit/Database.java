/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Database {

    private static Connection sharedConnection;
    private static final String TABLE_NAME_FOR_USER = "User";
    private static final String TABLE_NAME_FOR_SUPPLIER = "Supplier";
    private static final String TABLE_NAME_FOR_ORDER = "Orders";
    private static final String TABLE_NAME_FOR_PRODUCT = "Product";

    public void initialise() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String createUsersQuery = "CREATE TABLE IF NOT EXISTS " + Database.TABLE_NAME_FOR_USER + " ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL UNIQUE, "
                + "password TEXT NOT NULL, "
                + "full_name TEXT, "
                + "employee_id INTEGER)";

        st.execute(createUsersQuery);
        String sqlString = "INSERT OR IGNORE INTO User"
                + " (username, password, full_name, employee_id)"
                + " VALUES (?, ?, ?, ?)";

        PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
        String[] usernameArray = {"michael1234", "will2345", "david4321", "kurtley3123", "tony4444"};
        String[] passwordArray = {"carrot", "banana", "orange", "pineapple", "sohardhomework"};
        String[] fullNameArray = {"Michael Hooper", "Will Genia", "David Pocock", "Kurtley Beale", "Tony A"};
        String[] employeeIDArray = {"11111", "22222", "33333", "44444", "55555"};

        for (int i = 0; i < usernameArray.length; i++) {
            psmt.setString(1, usernameArray[i]);
            psmt.setString(2, passwordArray[i]);
            psmt.setString(3, fullNameArray[i]);
            psmt.setString(4, employeeIDArray[i]);
            psmt.execute();
        }

        String createSupplierTable = "CREATE TABLE IF NOT EXISTS " + Database.TABLE_NAME_FOR_SUPPLIER + " ("
                + "supplier_id INTEGER PRIMARY KEY,"
                + "supplier_name TEXT NOT NULL, "
                + "phone_number TEXT NOT NULL, "
                + "address TEXT NOT NULL, "
                + "CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id)"
                + "REFERENCES User(employee_id))";

        st.execute(createSupplierTable);

        String[] insertStatements = {
            "INSERT OR IGNORE INTO Supplier (supplier_id, supplier_name, phone_number, address) "
            + "VALUES ('22222', 'ABC', '0261676241', '1 Bell Street, Burwood VIC 3125');",
            "INSERT OR IGNORE INTO Supplier (supplier_id,supplier_name, phone_number, address) "
            + "VALUES ('33333', 'DEF', '0261620685', '23 East Avenue, Mosman SYD 2062');",
            "INSERT OR IGNORE INTO Supplier (supplier_id,supplier_name, phone_number, address) "
            + "VALUES ('44444', 'GHI', '0240112987', '2 Richard Street, Roseville SYD 2069');",
            "INSERT OR IGNORE INTO Supplier (supplier_id,supplier_name, phone_number, address) "
            + "VALUES ('55555', 'JKL', '0261537498', '5 Leopard Avenue, Bankstown SYD 2200');"
        };
        for (String thisStatement : insertStatements) {
            st.addBatch(thisStatement);
        }
        st.executeBatch();

        String createTableSql = "CREATE TABLE IF NOT EXISTS Orders ("
                + "order_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "product_id INTEGER NOT NULL, "
                + "supplier_id INTEGER NOT NULL, "
                + "quantity TEXT NOT NULL, "
                + "status TEXT NOT NULL, "
                + "order_timestamp TEXT NOT NULL, "
                + "CONSTRAINT fk_product_id FOREIGN KEY (product_id) "
                + "REFERENCES Product (product_id) "
                + "CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) "
                + "REFERENCES Supplier (supplier_id))";
        st.execute(createTableSql);

        String[] insertOrders = {
            "INSERT OR IGNORE INTO Orders (product_id, supplier_id, quantity, status, order_timestamp) "
            + "VALUES ('665', '22222', '2000', 'Order Placed', '2020-01-01');",
            "INSERT OR IGNORE INTO Orders (product_id, supplier_id, quantity, status, order_timestamp) "
            + "VALUES ('666', '33333', '5000', 'Order Placed', '2020-02-02');",
            "INSERT OR IGNORE INTO Orders (product_id, supplier_id, quantity, status, order_timestamp) "
            + "VALUES ('667', '44444', '9000', 'Order Completed','2020-04-02');",
            "INSERT OR IGNORE INTO Orders (product_id, supplier_id, quantity, status, order_timestamp) "
            + "VALUES ('668', '55555', '1200', 'Order Completed','2020-05-02');"
        };
        //Inserting data by looping through arrays
        for (String thisStatement : insertOrders) {
            st.addBatch(thisStatement);
        }
        st.executeBatch();

        String createProductTableSql = "CREATE TABLE IF NOT EXISTS " + Database.TABLE_NAME_FOR_PRODUCT + " ("
                + "product_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "product_name TEXT NOT NULL, "
                + "price INTEGER NOT NULL, "
                + "product_type TEXT NOT NULL)";
        st.execute(createProductTableSql);

        String[] insertProductStatements = {
            "INSERT OR IGNORE INTO Product (product_name, price, product_type) "
            + "VALUES ('Chicken', '20', 'Poultry');",
            "INSERT OR IGNORE INTO Product (product_name, price, product_type) "
            + "VALUES ('Pork', '15', 'White Meat');",
            "INSERT OR IGNORE INTO Product (product_name, price, product_type) "
            + "VALUES ('Veal', '15', 'White Meat');",
            "INSERT OR IGNORE INTO Product (product_name, price, product_type) "
            + "VALUES ('Banana', '20', 'Fruit');"
        };
        for (String thisStatement : insertProductStatements) {
            st.addBatch(thisStatement);
        }
        st.executeBatch();

    }

    public static boolean openConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            Database.sharedConnection = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");

    }

    private static boolean closeConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            sharedConnection.close();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    @FXML
    public ObservableList<Supplier> getSuppliers() throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM " + Database.TABLE_NAME_FOR_SUPPLIER;
        ResultSet rs = st.executeQuery(query);
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        while (rs.next()) {
            supplierList.add(new Supplier(rs.getInt("supplier_id"), rs.getString("supplier_name"), rs.getString("phone_number"),
                    rs.getString("address")));
        }
        st.close();
        conn.close();
        return supplierList;
    }

    @FXML
    public ObservableList<Order> getOrder() throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT Orders.order_id, Orders.product_id, "
                + "Orders.supplier_id, Orders.quantity, Orders.order_timestamp, Orders.status "
                + "FROM Orders";
        ResultSet rs = st.executeQuery(query);
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        while (rs.next()) {
            orderList.add(new Order(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        st.close();
        conn.close();
        return orderList;
    }

    public int getOrderPlaced() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();

        String sql = "SELECT COUNT(status) FROM Orders WHERE Status = 'Order Placed' ";
        ResultSet rs = st.executeQuery(sql);
        int orderPlaced = rs.getInt(1);
        st.close();
        conn.close();
        return orderPlaced;
    }

    public int getOrderCompleted() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();

        String sql = "SELECT COUNT(status) FROM Orders WHERE Status = 'Order Completed' ";
        ResultSet rs = st.executeQuery(sql);
        int orderCompleted = rs.getInt(1);
        st.close();
        conn.close();
        return orderCompleted;
    }

    public boolean setAccount1(String cp) throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        System.out.println("method start");
        String sql = "SELECT password FROM User WHERE password = '" + cp + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            Database.closeConnection();
            return true;
        } else {
            Database.closeConnection();
        }
        return false;

    }

    public boolean setAccount2(String cp, String np) throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");

        String sql = "UPDATE User SET password ='" + np + "' WHERE password = '" + cp + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            Database.closeConnection();
            return true;
        } else {
            Database.closeConnection();
        }
        return false;
    }

    public void InsertOrder(Order orderObj) throws SQLException {
        Database.openConnection();
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            Statement st1 = conn.createStatement();
            String sqlProductID = "SELECT product_id, supplier_id FROM Product, Supplier"
                    + " WHERE product_id = " + orderObj.getProductID()
                    + " WHERE supplier_id = " + orderObj.getSupplierID();
            ResultSet rs1 = st1.executeQuery(sqlProductID);
            int productID = rs1.getInt(1);
            int supplierID = rs1.getInt(2);

            Statement st = conn.createStatement();
            String sqlInsert = "INSERT INTO Orders (product_id, supplier_id, quantity, status, order_timestamp) "
                    + "VALUES ("+ productID + ", "
                    + supplierID + ", "
                    + "'" + orderObj.getQuantity() + "',"
                    + "'" + orderObj.getStatus() + "',"
                    + "'" + orderObj.getTimestamp() + "');";
            st.executeQuery(sqlInsert);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public ObservableList<OrderStatusUpdate> getOrderStatusUpdate(int supplierID, String username, String password) throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT User.employee_id, Orders.supplier_id, "
                + "Orders.order_id, Orders.status, "
                + "Orders.order_timestamp FROM Orders "
                + "INNER JOIN User ON Orders.supplier_id = User.employee_id "
                + "WHERE supplier_id = " + supplierID
                + " AND username = '" + username
                + "' AND password = '" + password + "'";
        ResultSet rs = st.executeQuery(query);
        ObservableList<OrderStatusUpdate> orderStatusUpdate = FXCollections.observableArrayList();
        while (rs.next()) {
            orderStatusUpdate.add(new OrderStatusUpdate(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }

        st.close();
        conn.close();
        return orderStatusUpdate;
    }

    public boolean verify(int supplierID, String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT User.username, User.password, User.employee_id, "
                + "Orders.supplier_id FROM Orders "
                + "INNER JOIN User ON Orders.supplier_id = User.employee_id "
                + "WHERE supplier_id = " + supplierID
                + " AND username = '" + username
                + "' AND password = '" + password + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            st.close();
            conn.close();
            return true;
        } else {
            st.close();
            conn.close();
            return false;
        }
    }

    public boolean login(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            st.close();
            conn.close();
            return true;
        } else {
            st.close();
            conn.close();
            return false;
        }
    }

    public ObservableList<OrderSummary> searchOrder(String s) throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query;
        if (s.equals("")) {
            query = "SELECT Orders.order_id AS order_id, Product.product_name, "
                    + "Orders.status, Orders.quantity FROM Orders, Supplier, Product "
                    + "WHERE Orders.product_id = Product.product_id "
                    + "AND Orders.supplier_id = Supplier.supplier_id";
        } else {
            query = "SELECT Orders.order_id AS order_id, Product.product_name, "
                    + "Orders.status, Orders.quantity FROM Orders, Supplier, Product "
                    + "WHERE Orders.product_id = Product.product_id "
                    + "AND Orders.supplier_id = Supplier.supplier_id "
                    + "AND (Product.product_name =  '" + s + "' OR Orders.status = '" + s
                    + "')";
        }
        ResultSet rs = st.executeQuery(query);
        ObservableList<OrderSummary> orderSummary = FXCollections.observableArrayList();
        while (rs.next()) {
            orderSummary.add(new OrderSummary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        st.close();
        conn.close();
        return orderSummary;
    }

    @FXML
    public ObservableList<OrderSummary> getOrderSummary() throws SQLException {
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT Orders.order_id, Product.product_name, Orders.status, Orders.quantity "
                + "FROM Orders INNER JOIN Product ON Orders.product_id = Product.product_id";

        ResultSet rs = st.executeQuery(query);
        ObservableList<OrderSummary> orderSummary = FXCollections.observableArrayList();
        while (rs.next()) {
            orderSummary.add(new OrderSummary(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }

        st.close();
        conn.close();
        return orderSummary;
    }

    public boolean check(int supplierID, String supplierName) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Statement st = conn.createStatement();
        String query = "SELECT supplier_id FROM OrderProducts GROUP BY " + supplierID
                + " HAVING MAX(supplier_name) = '" + supplierName + "'"
                + "AND MAX(supplier_name) = '" + supplierName + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            st.close();
            conn.close();
            return true;
        } else {
            st.close();
            conn.close();
            return false;
        }
    }
}
