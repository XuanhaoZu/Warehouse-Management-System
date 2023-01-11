package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class DataManagementController {

    @FXML
    private Button btnSupplierList;
    @FXML
    private Button btnCreateDeleteOrder;
    @FXML
    private Button btnOrderSummary;
    @FXML
    private Pane pnSupplierList;
    @FXML
    private Pane pnCreateDeleteOrder;
    @FXML
    private Pane pnOrderSummary;
    @FXML
    private Hyperlink linkAbout;
    @FXML
    private Hyperlink linkSettings;
    @FXML
    private Hyperlink linkLogout;
    @FXML
    private TableView<Supplier> tableSupplierList;
    @FXML
    private TableColumn<Supplier, String> supplierIDCol;
    @FXML
    private TableColumn<Supplier, String> supplierNameCol;
    @FXML
    private TableColumn<Supplier, String> phoneNumberCol;
    @FXML
    private TableColumn<Supplier, String> addressCol;
    @FXML
    private Button btnAddRow;
    @FXML
    private TextField txtSupplierName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtAddress;
    @FXML
    private Button btnDeleteRow;
    @FXML
    private TableView<Order> tableOrderList;
    @FXML
    private TableColumn<Order, String> orderIDCol;
    @FXML
    private TableColumn<Order, String> productIDCol;
    @FXML
    private TableColumn<Order, String> quantityCol;
    @FXML
    private TableColumn<Order, String> supplierIDColCreateOrder;
    @FXML
    private TableColumn<Order, String> timestampCol;
    @FXML
    private TableColumn<Order, String> statusCol;
    @FXML
    private TextField txtProductName;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TextField txtTimestamp;
    @FXML
    private TextField txtSupplierName1;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnID;
    @FXML
    private Button btnAddRow1;
    @FXML
    private Button btnDeleteRow1;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<Order> tableAddOrder;
    @FXML
    private Button btnAddToOrderList;
    @FXML
    private TableColumn<Order, String> orderIDCol2;
    @FXML
    private TableColumn<Order, String> productNameCol2;
    @FXML
    private TableColumn<Order, String> quantityCol2;
    @FXML
    private TableColumn<Order, String> supplierCol2;
    @FXML
    private TableColumn<Order, String> timestampCol2;
    @FXML
    private TableColumn<Order, String> statusCol2;
    @FXML
    private TableView<OrderSummary> tableOrderSummary;
    @FXML
    private TableColumn<OrderSummary, String> orderIDCol1;
    @FXML
    private TableColumn<OrderSummary, String> statusCol1;
    @FXML
    private TableColumn<OrderSummary, String> productNameCol1;
    @FXML
    private TableColumn<OrderSummary, String> quantityCol1;
    @FXML
    private TableView<OrderStatusUpdate> tableOrderStatusUpdate;
    @FXML
    private TableColumn<OrderStatusUpdate, String> employeeID;
    @FXML
    private TableColumn<OrderStatusUpdate, String> supplierID;
    @FXML
    private TableColumn<OrderStatusUpdate, String> orderID;
    @FXML
    private TableColumn<OrderStatusUpdate, String> status;
    @FXML
    private TableColumn<OrderStatusUpdate, String> timestamp;
    @FXML
    private Pane pnUpdateOrder;
    @FXML
    private Button btnUpdateOrder;
    @FXML
    private Label lblIncorrectDetails;
    @FXML
    private TextField supplierIDTextField;
    @FXML
    private Label incorrectCredentialsLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField productIDTextField;
    @FXML
    private TextField supplierIDTextFieldCreateDeleteOrder;
    @FXML
    private ListView legend;
    @FXML
    private ListView supplierLegend;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        ObservableList<OrderStatusUpdate> orderStatusUpdate = FXCollections.observableArrayList();
        ObservableList<OrderSummary> orderSummary = FXCollections.observableArrayList();

        Database databaseObj = new Database();
        try {
            Database.openConnection();
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            conn = databaseObj.getConnection();
            supplierList = databaseObj.getSuppliers();
            supplierIDCol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            supplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            tableSupplierList.setItems(supplierList);

            tableSupplierList.setEditable(true);
            supplierNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
            phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
            addressCol.setCellFactory(TextFieldTableCell.forTableColumn());

            orderList = databaseObj.getOrder();
            orderIDCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
            quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            supplierIDColCreateOrder.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
            tableOrderList.setItems(orderList);
            tableOrderList.setEditable(true);
            supplierIDColCreateOrder.setCellFactory(TextFieldTableCell.forTableColumn());
            productIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
            quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
            timestampCol.setCellFactory(TextFieldTableCell.forTableColumn());
            statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
            tableAddOrder.setVisible(false);
            tableOrderList.setVisible(true);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Order Placed", databaseObj.getOrderPlaced()),
                    new PieChart.Data("Order Completed", databaseObj.getOrderCompleted()));

            pieChart.setData(pieChartData);

            pieChart.setTitle("Order Status");

            pieChart.setClockwise(true);

            pieChart.setLabelLineLength(20);

            pieChart.setLabelsVisible(true);

            pieChart.setStartAngle(180);

            orderSummary = databaseObj.getOrderSummary();
            orderIDCol1.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            productNameCol1.setCellValueFactory(new PropertyValueFactory<>("productName"));
            statusCol1.setCellValueFactory(new PropertyValueFactory<>("status"));
            quantityCol1.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            tableOrderSummary.setItems(orderSummary);

            legend.getItems().add("Chicken = 665");
            legend.getItems().add("Pork = 666");
            legend.getItems().add("Veal = 667");
            legend.getItems().add("Banana = 668");

            supplierLegend.getItems().add("22222 = ABC");
            supplierLegend.getItems().add("33333 = DEF");
            supplierLegend.getItems().add("44444 = GHI");
            supplierLegend.getItems().add("55555 = JKL");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        if (event.getSource() == btnSupplierList) {
            pnSupplierList.toFront();
        } else if (event.getSource() == btnCreateDeleteOrder) {
            pnCreateDeleteOrder.toFront();
        } else if (event.getSource() == btnOrderSummary) {
            Database databaseObj = new Database();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Order Placed", databaseObj.getOrderPlaced()),
                    new PieChart.Data("Order Completed", databaseObj.getOrderCompleted()));
            pieChart.setData(pieChartData);
            pieChart.setTitle("Order Status");
            pieChart.setClockwise(true);
            pieChart.setLabelLineLength(20);
            pieChart.setLabelsVisible(true);
            pieChart.setStartAngle(180);
            pnOrderSummary.toFront();

        } else if (event.getSource() == btnUpdateOrder) {
            pnUpdateOrder.toFront();

        }
    }

    public void addButtonClicked() throws SQLException {
        Supplier supplierObj = new Supplier();
        supplierObj.setSupplierName(txtSupplierName.getText());
        supplierObj.setPhoneNumber(txtPhoneNumber.getText());
        supplierObj.setAddress(txtAddress.getText());
        tableSupplierList.getItems().add(supplierObj);
        txtSupplierName.clear();
        txtPhoneNumber.clear();
        txtAddress.clear();
        int supplierID;
        String insert = "SELECT supplier_id FROM Supplier ORDER BY supplier_id DESC LIMIT 1";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            PreparedStatement ps = conn.prepareStatement(insert);
            ResultSet rs = ps.executeQuery();
            supplierID = rs.getInt(1);
            for (int i = 0; i < tableSupplierList.getItems().size(); i++) {
                supplierObj.setSupplierID(supplierID++);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void saveSupplierData(ActionEvent event) throws SQLException {

        Supplier savedData = new Supplier();
        ObservableList<Supplier> allSuppliers;
        allSuppliers = tableSupplierList.getSelectionModel().getSelectedItems();
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");

        for (int i = 0; i < tableSupplierList.getItems().size(); i++) {
            savedData = tableSupplierList.getItems().get(i);
            String idSave = String.valueOf(supplierIDCol.getCellObservableValue(savedData).getValue());
            String nameSave = supplierNameCol.getCellObservableValue(savedData).getValue();
            String phoneNumSave = phoneNumberCol.getCellObservableValue(savedData).getValue();
            String addressSave = addressCol.getCellObservableValue(savedData).getValue();

            String sqlInsert = "INSERT INTO Supplier (supplier_name, phone_number, address) "
                    + "VALUES (?, ?, ?)";

            String sql = "UPDATE Supplier SET supplier_name = ?, phone_number = ?, address = ? WHERE supplier_id = '" + idSave + "'";

            try {
                PreparedStatement st1 = conn.prepareStatement(sqlInsert);
                st1.setString(1, allSuppliers.get(i).getSupplierName());
                st1.setString(2, allSuppliers.get(i).getPhoneNumber());
                st1.setString(3, allSuppliers.get(i).getAddress());
                st1.execute();
                st1.close();

                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, nameSave);
                st.setString(2, phoneNumSave);
                st.setString(3, addressSave);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public void deleteButtonClicked() throws SQLException {
        ObservableList<Supplier> allSuppliers, supplierChosen;
        supplierChosen = tableSupplierList.getSelectionModel().getSelectedItems();
        allSuppliers = tableSupplierList.getItems();
        Supplier supplierSelected = tableSupplierList.getSelectionModel().getSelectedItem();
        String id = String.valueOf(supplierSelected.getSupplierID());
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            String sql = "DELETE FROM Supplier WHERE supplier_id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            for (Supplier s : supplierChosen) {
                allSuppliers.remove(s);
                st.setString(1, id);
                st.execute();
            }
            conn.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void changeSupplierNameCellEvent(CellEditEvent editedCell) throws SQLException {
        Supplier supplierSelected = tableSupplierList.getSelectionModel().getSelectedItem();
        supplierSelected.setSupplierName(editedCell.getNewValue().toString());
    }

    public void changePhoneNumberCellEvent(CellEditEvent editedCell) {
        Supplier supplierSelected = tableSupplierList.getSelectionModel().getSelectedItem();
        supplierSelected.setPhoneNumber(editedCell.getNewValue().toString());
    }

    public void changeAddressCellEvent(CellEditEvent editedCell) {
        Supplier supplierSelected = tableSupplierList.getSelectionModel().getSelectedItem();
        supplierSelected.setAddress(editedCell.getNewValue().toString());
    }

    public void addButtonClickedCreateDeleteOrder() throws SQLException {
        Order orderObj = new Order();
        orderObj.setProductID(productIDTextField.getText());
        orderObj.setQuantity(txtQuantity.getText());
        orderObj.setSupplierID(supplierIDTextFieldCreateDeleteOrder.getText());
        orderObj.setTimestamp(txtTimestamp.getText());
        orderObj.setStatus("Order Placed");
        txtQuantity.clear();
        supplierIDTextFieldCreateDeleteOrder.clear();
        txtTimestamp.clear();
        productIDTextField.clear();
        tableOrderList.getItems().add(orderObj);
        int orderID;
        String insert = "SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1";
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
            PreparedStatement ps = conn.prepareStatement(insert);
            ResultSet rs = ps.executeQuery();
            orderID = rs.getInt(1);
            while (rs.next()) {
                for (int i = 0; i < tableOrderList.getItems().size(); i++) {
                    orderObj.setOrderID(orderID++);
                }
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        tableOrderList.setVisible(false);
//        tableAddOrder.setVisible(true);
//        btnAddToOrderList.setVisible(true);

    }

    public void addButtonClickedAF2() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
        Order orderObj = new Order();
        Database databaseObj = new Database();
        ObservableList<Order> orderList = FXCollections.observableArrayList();

        for (int i = 0; i < tableAddOrder.getItems().size(); i++) {
            orderObj = tableAddOrder.getItems().get(i);
            orderList.add(orderObj);

            tableOrderList.getItems().add(orderObj);
            databaseObj.InsertOrder(orderObj);

        }

        tableAddOrder.getItems().clear();
        tableAddOrder.setVisible(false);
        tableOrderList.setVisible(true);
        btnAddToOrderList.setVisible(false);

    }

    public void deleteButtonClickedOrderDetails() {
        ObservableList<Order> orderSelected, allOrder;
        allOrder = tableOrderList.getItems();
        orderSelected = tableOrderList.getSelectionModel().getSelectedItems();

        for (Order s : orderSelected) {
            allOrder.remove(s);
        }
    }

    public void changeSupplierIDCellEventOrderDetails(CellEditEvent editedCell) throws SQLException {
        Order orderSelected = tableOrderList.getSelectionModel().getSelectedItem();
        orderSelected.setSupplierID(editedCell.getNewValue().toString());
    }

    public void changeProductIDCellEventOrderDetails(CellEditEvent editedCell) throws SQLException {
        Order orderSelected = tableOrderList.getSelectionModel().getSelectedItem();
        orderSelected.setProductID(editedCell.getNewValue().toString());

    }

    public void changeQuantityCellEventOrderDetails(CellEditEvent editedCell) throws SQLException {
        Order orderSelected = tableOrderList.getSelectionModel().getSelectedItem();
        orderSelected.setQuantity(editedCell.getNewValue().toString());
    }

    public void changeTimestampCellEventOrderDetails(CellEditEvent editedCell) throws SQLException {
        Order orderSelected = tableOrderList.getSelectionModel().getSelectedItem();
        orderSelected.setTimestamp(editedCell.getNewValue().toString());
    }

    public void changeStatusCellEventOrderDetails(CellEditEvent editedCell) throws SQLException {
        Order orderSelected = tableOrderList.getSelectionModel().getSelectedItem();
        orderSelected.setStatus(editedCell.getNewValue().toString());
    }

    public void saveCreateDeleteOrderData(ActionEvent event) throws SQLException {

        Order savedData = new Order();
        ObservableList<Order> allOrders;
        allOrders = tableOrderList.getSelectionModel().getSelectedItems();
        Database.openConnection();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");

        for (int i = 0; i < tableOrderList.getItems().size(); i++) {
            savedData = tableOrderList.getItems().get(i);
            String idSave = String.valueOf(orderIDCol.getCellObservableValue(savedData).getValue());
            String productIDSave = String.valueOf(productIDCol.getCellObservableValue(savedData).getValue());
            String quantitySave = quantityCol.getCellObservableValue(savedData).getValue();
            String supplierIDSave = String.valueOf(supplierIDColCreateOrder.getCellObservableValue(savedData).getValue());
            String timestampSave = timestampCol.getCellObservableValue(savedData).getValue();
            String statusSave = statusCol.getCellObservableValue(savedData).getValue();

            String sqlInsert = "INSERT INTO Orders (product_id, quantity, supplier_id, status, order_timestamp)"
                    + "VALUES (?, ?, ?, ?, ?)";

            String sql = "UPDATE Orders SET product_id = ?, quantity = ?, supplier_id = ?, status = ?, order_timestamp = ? WHERE order_id = '" + idSave + "'";

            try {
                PreparedStatement st1 = conn.prepareStatement(sqlInsert);
                st1.setString(1, allOrders.get(i).getProductID());
                st1.setString(2, allOrders.get(i).getQuantity());
                st1.setString(3, allOrders.get(i).getSupplierID());
                st1.setString(4, allOrders.get(i).getStatus());
                st1.setString(5, allOrders.get(i).getTimestamp());
                st1.execute();
                st1.close();

                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, productIDSave);
                st.setString(2, quantitySave);
                st.setString(3, supplierIDSave);
                st.setString(4, statusSave);
                st.setString(5, timestampSave);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    public void refreshButtonClick() throws SQLException {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        Database databaseObj = new Database();
        orderList = databaseObj.getOrder();
        orderIDCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderID"));
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Order, String>("quantity"));
        supplierIDColCreateOrder.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        timestampCol.setCellValueFactory(new PropertyValueFactory<Order, String>("timestamp"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Order, String>("status"));

        tableOrderList.setItems(orderList);

        tableOrderList.setEditable(true);
        supplierIDColCreateOrder.setCellFactory(TextFieldTableCell.forTableColumn());
        productIDCol.setCellFactory(TextFieldTableCell.forTableColumn());
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timestampCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tableAddOrder.setVisible(false);
        tableOrderList.setVisible(true);
    }

    @FXML
    private void handleVerifyButtonAction(ActionEvent event) throws IOException, SQLException {
        int supplierIDVerify = Integer.parseInt(supplierIDTextField.getText());
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        Database databaseObj = new Database();
        boolean success = databaseObj.verify(supplierIDVerify, username, password);
        ObservableList<OrderStatusUpdate> orderStatusUpdate = FXCollections.observableArrayList();

        if (success) {
            orderStatusUpdate = databaseObj.getOrderStatusUpdate(supplierIDVerify, username, password);
            employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
            supplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            timestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
            tableOrderStatusUpdate.setItems(orderStatusUpdate);

            tableOrderStatusUpdate.setEditable(true);
            status.setCellFactory(TextFieldTableCell.forTableColumn());
            tableOrderStatusUpdate.setVisible(true);
            incorrectCredentialsLabel.setVisible(false);
        } else {
            incorrectCredentialsLabel.setText("At least one of the details entered is incorrect");
            incorrectCredentialsLabel.setVisible(true);
        }
    }

    public void changeOrderStatusCellEvent(CellEditEvent editedCell) throws SQLException {
        OrderStatusUpdate orderStatusSelected = tableOrderStatusUpdate.getSelectionModel().getSelectedItem();
        orderStatusSelected.setStatus(editedCell.getNewValue().toString());
    }

    public void saveOrderStatusUpdate(ActionEvent event) throws SQLException {

        OrderStatusUpdate savedData = new OrderStatusUpdate();

        for (int i = 0; i < tableOrderStatusUpdate.getItems().size(); i++) {
            savedData = tableOrderStatusUpdate.getItems().get(i);
            String employeeIDSave = String.valueOf(employeeID.getCellObservableValue(savedData).getValue());
            String idSave = String.valueOf(supplierID.getCellObservableValue(savedData).getValue());
            String orderIDSave = String.valueOf(orderID.getCellObservableValue(savedData).getValue());
            String timestampSave = timestamp.getCellObservableValue(savedData).getValue();
            String statusSave = status.getCellObservableValue(savedData).getValue();
            String sql = "UPDATE Orders SET status = ? WHERE supplier_id = '" + idSave + "'";
            try {
                Database.openConnection();
                Connection conn = DriverManager.getConnection("jdbc:sqlite:groupassignmentdatabase.db");
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, statusSave);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void searchButtonClick(ActionEvent event) throws SQLException {
        ObservableList<OrderSummary> orderSummary = FXCollections.observableArrayList();

        Database databaseObj = new Database();
        String s = txtSearch.getText().trim();

        orderSummary = databaseObj.searchOrder(s);

        orderIDCol1.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        statusCol1.setCellValueFactory(new PropertyValueFactory<>("status"));
        productNameCol1.setCellValueFactory(new PropertyValueFactory<>("productName"));

        tableOrderSummary.setItems(orderSummary);
    }

    @FXML
    private void linkSettingsClick(ActionEvent event) throws IOException {
        System.out.println("linkSettingsClick");
        App.setRoot("settings");
    }

    @FXML
    private void linkAboutClick(ActionEvent event) throws IOException {
        System.out.println("aboutlinkclick");
        App.setRoot("about");
    }

    @FXML
    private void linkLogout(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
