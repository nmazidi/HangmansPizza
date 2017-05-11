/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.gui;

import desktopapp.datamodel.APIConnection;
import desktopapp.datamodel.Admin;
import desktopapp.datamodel.Customer;
import desktopapp.datamodel.Item;
import desktopapp.datamodel.Rider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathan
 */
public class AdminPage extends javax.swing.JFrame {

    private APIConnection adminCon;
    private APIConnection customerCon;
    private APIConnection itemCon;
    private APIConnection riderCon;
    private DefaultTableModel adminTableModel;
    private DefaultTableModel customerTableModel;
    private DefaultTableModel itemTableModel;
    private DefaultTableModel riderTableModel;
    /**
     * Creates new form AdminPage
     */
    public AdminPage() {
        initComponents();
        loadTimer();
        
        String [] adminColumnNames = {"Admin ID", "Branch ID", "Forename",
            "Surname", "Username"};
        
        String [] customerColumnNames = {"Customer ID", "Title", "Forename",
            "Surname", "Email Address", "Phone Number", "Date of Birth"};
        
        String [] itemColumnNames = {"Item ID", "Item Name", "Item Type", "Selling Price"};
        
        String [] riderColumnNames = {"Rider ID", "Title", "Forename", "Surname",
            "Email", "Phone Number", "Vehicle Type", "Date of Birth"};
        
        adminTableModel = new DefaultTableModel(adminColumnNames, 0);
        customerTableModel = new DefaultTableModel(customerColumnNames, 0);
        itemTableModel = new DefaultTableModel(itemColumnNames, 0);
        riderTableModel = new DefaultTableModel(riderColumnNames, 0);
        
        fillAdminTable();
        fillTables();
        
        
    }

    private void loadTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
                lblCurrentTime.setText(string);
            }
        }, 0, 1000);
    }
    
    private void fillTables() 
    {
      //  fillAdminTable();
        fillCustomerTable();
        fillItemTable();
        fillRiderTable();        
    }
    
    
    private void fillAdminTable()
    {
        String response;
        String urlAdmin = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/admins";
        String adminIDField = "ADMIN_ID";
        String branchIDField = "BRANCH_ID";
        String forenameField = "FORENAME";
        String surnameField = "SURNAME";
        String usernameField = "USERNAME";
        String adminIDValue;
        int adminIDIntValue;
        String branchIDValue;
        int branchIDIntValue;
        String forenameValue;
        String surnameValue;
        String usernameValue;
        int adminIDFieldLocation;
        int branchIDFieldLocation;
        int forenameFieldLocation;
        int surnameFieldLocation;
        int usernameFieldLocation;     
                
        try {
            //Create instance of API Connection
            adminCon = new APIConnection();
            //Set get json response to a string variable
            response = adminCon.getRequest(urlAdmin);            
            //Refresh admin table
            adminTableModel.setRowCount(0);            
            //Split response into individual database records
            String [] allAdmins = response.split("\\},\\{");
            Object adminData [] = new Object[5];
            
            //Loop through records in database table
            for (int i = 0; i < allAdmins.length; i++) 
            {
                String tempAdmin = allAdmins[i];
                int commaIndex = tempAdmin.indexOf(",");
                int commas [] = new int[5];
                int j = 0;
                
                //Find location of field names JSON string
                adminIDFieldLocation = tempAdmin.lastIndexOf(adminIDField);
                branchIDFieldLocation = tempAdmin.lastIndexOf(branchIDField);
                forenameFieldLocation = tempAdmin.lastIndexOf(forenameField);
                surnameFieldLocation = tempAdmin.lastIndexOf(surnameField);
                usernameFieldLocation = tempAdmin.lastIndexOf(usernameField);
                
                //Find the location of commas splitting attributes of the data
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempAdmin.indexOf(",", commaIndex + 1); 
                    j++;
                }
                
                //Set values of attributes in JSON to corresponding variables
                adminIDValue = tempAdmin.substring(adminIDFieldLocation + 10, commas[0]);
                adminIDIntValue = Integer.parseInt(adminIDValue);
                branchIDValue = tempAdmin.substring(branchIDFieldLocation + 11, commas[1]);
                branchIDIntValue = Integer.parseInt(branchIDValue);
                forenameValue = tempAdmin.substring(forenameFieldLocation + 11, commas[2] - 1);
                surnameValue = tempAdmin.substring(surnameFieldLocation + 10, commas[3] - 1);
                usernameValue = tempAdmin.substring(usernameFieldLocation + 11, commas[4] - 1);
                
                //Add a new data record to the class
                Admin adminToAdd = null;
                adminToAdd = new Admin(adminIDIntValue, branchIDIntValue,
                        forenameValue, surnameValue, usernameValue);
                
                System.out.println(adminIDValue + " " + branchIDValue + " "
                        + forenameValue + " " + surnameValue + " " + usernameValue);               
                
                //Add a new data rown to the table using the data parsed out of the JSON
                adminData[0] = adminIDValue;
                adminData[1] = branchIDValue;
                adminData[2] = forenameValue;
                adminData[3] = surnameValue;
                adminData[4] = usernameValue;
                adminTableModel.addRow(adminData);                
            }
            tblAdmins.setModel(adminTableModel);          
                        
        } catch (Exception e) {
            System.out.println("Exception");
        }
        
    }
    
    private void fillCustomerTable()
    {
        String response;
        String urlCustomer = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/customers";
        String customerIDField = "CUSTOMER_ID";
        String titleField = "CUSTOMER_TITLE";
        String forenameField = "CUSTOMER_FORENAME";
        String surnameField = "CUSTOMER_SURNAME";
        String emailField = "CUSTOMER_EMAIL";
        String phoneField = "CUSTOMER_PHONE";
        String dateOfBirthField = "CUSTOMER_DOB";
        String customerIDValue;
        int customerIDIntValue;
        String titleValue;
        String forenameValue;
        String surnameValue;
        String emailValue;
        String phoneValue;
        String dateOfBirthValue;
        Date dobDateValue;
        int customerIDFieldLocation;
        int titleFieldLocation;
        int forenameFieldLocation;
        int surnameFieldLocation;
        int emailFieldLocation;
        int phoneFieldLocation;
        int dobFieldLocation;
        
        
        try {
        
            //Create instance of API Connection
            customerCon = new APIConnection();
            //Set get json response to a string variable
            response = customerCon.getRequest(urlCustomer);            
            //Refresh admin table
            customerTableModel.setRowCount(0);
            //Split response into individual database records
            String [] allCustomers = response.split("\\},\\{");
            Object customerData [] = new Object[7];
            
            //Loop through records in database table
            for (int i = 0; i < allCustomers.length; i++) 
            {
                String tempCustomer = allCustomers[i];
                int commaIndex = tempCustomer.indexOf(",");
                int commas [] = new int[9];
                int j = 0;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                
                //Find location of field names JSON string
                customerIDFieldLocation = tempCustomer.lastIndexOf(customerIDField);
                titleFieldLocation = tempCustomer.lastIndexOf(titleField);
                forenameFieldLocation = tempCustomer.lastIndexOf(forenameField);
                surnameFieldLocation = tempCustomer.lastIndexOf(surnameField);
                emailFieldLocation = tempCustomer.lastIndexOf(emailField);
                phoneFieldLocation = tempCustomer.lastIndexOf(phoneField);
                dobFieldLocation = tempCustomer.lastIndexOf(dateOfBirthField);
                
                //Find the location of commas splitting attributes of the data
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempCustomer.indexOf(",", commaIndex + 1); 
                    j++;
                }
                
                //Set values of attributes in JSON to corresponding variables
                customerIDValue = tempCustomer.substring(customerIDFieldLocation + 13, commas[0]);
                customerIDIntValue = Integer.parseInt(customerIDValue);
                titleValue = tempCustomer.substring(titleFieldLocation + 17, commas[1] - 1);
                forenameValue = tempCustomer.substring(forenameFieldLocation + 20, commas[2] - 1);
                surnameValue = tempCustomer.substring(surnameFieldLocation + 19, commas[3] - 1);
                emailValue = tempCustomer.substring(emailFieldLocation + 17, commas[4] - 1);
                phoneValue = tempCustomer.substring(phoneFieldLocation + 17, commas[5] - 1);
                dateOfBirthValue = tempCustomer.substring(dobFieldLocation + 15, commas[7] - 1);
                dobDateValue = format.parse(dateOfBirthValue);
                        
                //Add a new data record to the class
                Customer customerToAdd = null;
                customerToAdd = new Customer(customerIDIntValue, titleValue,
                        forenameValue, surnameValue, emailValue, phoneValue, dobDateValue);
                
                System.out.println(customerIDValue + " " + titleValue + " "
                        + forenameValue + " " + surnameValue + " " + emailValue + " "
                        + phoneValue + " " + dateOfBirthValue);
                
                //Add a new data rown to the table using the data parsed out of the JSON
                customerData[0] = customerIDValue;
                customerData[1] = titleValue;
                customerData[2] = forenameValue;
                customerData[3] = surnameValue;
                customerData[4] = emailValue;
                customerData[5] = phoneValue;
                customerData[6] = dateOfBirthValue.substring(0, 10);
                customerTableModel.addRow(customerData);                
            }
            tblCustomers.setModel(customerTableModel);  
            
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    private void fillItemTable()
    {
        String response;
        String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/items";
        String itemIDField = "ITEM_ID";
        String itemNameField = "ITEM_NAME";
        String itemTypeField = "ITEM_TYPE";
        String priceField = "SELLING_PRICE";
        String itemIDValue;
        int itemIDIntValue;
        String itemNameValue;
        String itemTypeValue;
        String priceValue;
        int itemIDFieldLocation;
        int itemNameFieldLocation;
        int itemTypeFieldLocation;
        int priceFieldLocation;
        
        try {
        
            //Create instance of API Connection
            itemCon = new APIConnection();
            //Set get json response to a string variable
            response = itemCon.getRequest(urlItem);            
            //Refresh admin table
            itemTableModel.setRowCount(0);
            //Split response into individual database records
            String [] allItems = response.split("\\},\\{");
            Object itemData [] = new Object[4];
            
            //Loop through records in database table
            for (int i = 0; i < allItems.length; i++) 
            {
                String tempItem = allItems[i];
                int commaIndex = tempItem.indexOf(",");
                int commas [] = new int[3];
                int j = 0;
                
                //Find location of field names JSON string
                itemIDFieldLocation = tempItem.lastIndexOf(itemIDField);
                itemNameFieldLocation = tempItem.lastIndexOf(itemNameField);
                itemTypeFieldLocation = tempItem.lastIndexOf(itemTypeField);
                priceFieldLocation = tempItem.lastIndexOf(priceField);
                
                //Find the location of commas splitting attributes of the data
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempItem.indexOf(",", commaIndex + 1); 
                    j++;
                }
                
                //Set values of attributes in JSON to corresponding variables
                if (i == allItems.length - 1)
                {
                    itemIDValue = tempItem.substring(itemIDFieldLocation + 9, commas[0]);
                    itemIDIntValue = Integer.parseInt(itemIDValue);
                    itemNameValue = tempItem.substring(itemNameFieldLocation + 12, commas[1] - 1);
                    itemTypeValue = tempItem.substring(itemTypeFieldLocation + 12, commas[2] - 1);              
                    priceValue = tempItem.substring(priceFieldLocation + 16, tempItem.length() - 3); 
                } else {
                    itemIDValue = tempItem.substring(itemIDFieldLocation + 9, commas[0]);
                    itemIDIntValue = Integer.parseInt(itemIDValue);
                    itemNameValue = tempItem.substring(itemNameFieldLocation + 12, commas[1] - 1);
                    itemTypeValue = tempItem.substring(itemTypeFieldLocation + 12, commas[2] - 1);              
                    priceValue = tempItem.substring(priceFieldLocation + 16, tempItem.length() - 1);
                }
                               
                //Add a new data record to the class                        
                Item itemToAdd = null;
                itemToAdd = new Item(itemIDIntValue, itemNameValue, itemTypeValue,
                        priceValue);
                
                System.out.println(itemIDValue + " " + itemNameValue + " "
                        + itemTypeValue + " " + priceValue);
                
                //Add a new data rown to the table using the data parsed out of the JSON
                itemData[0] = itemIDIntValue;
                itemData[1] = itemNameValue;
                itemData[2] = itemTypeValue;
                itemData[3] = priceValue;
                itemTableModel.addRow(itemData);                
            }
            tblItems.setModel(itemTableModel);  
            
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    
    private void fillRiderTable()
    {
        String response;
        String urlRider = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/delivery_rider";
        String riderIDField = "RIDER_ID";
        String titleField = "RIDER_TITLE";
        String forenameField = "RIDER_FORENAME";
        String surnameField = "RIDER_SURNAME";
        String emailField = "RIDER_EMAIL";
        String phoneField = "RIDER_PHONE";
        String vehicleTypeField = "VEHICLE_TYPE";
        String dateOfBirthField = "RIDER_DOB";
        String riderIDValue;
        int riderIDIntValue;
        String titleValue;
        String forenameValue;
        String surnameValue;
        String emailValue;
        String phoneValue;
        String vehicleTypeValue;
        String dateOfBirthValue;
        Date dobDateValue;
        int riderIDFieldLocation;
        int titleFieldLocation;
        int forenameFieldLocation;
        int surnameFieldLocation;
        int emailFieldLocation;
        int phoneFieldLocation;
        int vehicleFieldLocation;
        int dobFieldLocation;
        
        
        try {
        
            //Create instance of API Connection
            riderCon = new APIConnection();
            //Set get json response to a string variable
            response = riderCon.getRequest(urlRider);            
            //Refresh admin table
            riderTableModel.setRowCount(0);
            //Split response into individual database records
            String [] allRiders = response.split("\\},\\{");
            Object riderData [] = new Object[8];
            
            //Loop through records in database table
            for (int i = 0; i < allRiders.length; i++) 
            {
                String tempRider = allRiders[i];
                int commaIndex = tempRider.indexOf(",");
                int commas [] = new int[9];
                int j = 0;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                
                //Find location of field names JSON string
                riderIDFieldLocation = tempRider.lastIndexOf(riderIDField);
                titleFieldLocation = tempRider.lastIndexOf(titleField);
                forenameFieldLocation = tempRider.lastIndexOf(forenameField);
                surnameFieldLocation = tempRider.lastIndexOf(surnameField);
                emailFieldLocation = tempRider.lastIndexOf(emailField);
                phoneFieldLocation = tempRider.lastIndexOf(phoneField);
                vehicleFieldLocation = tempRider.lastIndexOf(vehicleTypeField);
                dobFieldLocation = tempRider.lastIndexOf(dateOfBirthField);
                
                //Find the location of commas splitting attributes of the data
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempRider.indexOf(",", commaIndex + 1); 
                    j++;
                }
                
                //Set values of attributes in JSON to corresponding variables
                riderIDValue = tempRider.substring(riderIDFieldLocation + 10, commas[0]);
                riderIDIntValue = Integer.parseInt(riderIDValue);
                titleValue = tempRider.substring(titleFieldLocation + 14, commas[8] - 1);
                forenameValue = tempRider.substring(forenameFieldLocation + 17, commas[1] - 1);
                surnameValue = tempRider.substring(surnameFieldLocation + 16, commas[2] - 1);
                emailValue = tempRider.substring(emailFieldLocation + 14, commas[3] - 1);
                phoneValue = tempRider.substring(phoneFieldLocation + 14, commas[4] - 1);
                vehicleTypeValue = tempRider.substring(vehicleFieldLocation + 15, commas[6] - 1);
                dateOfBirthValue = tempRider.substring(dobFieldLocation + 12, commas[7] - 1);
                dobDateValue = format.parse(dateOfBirthValue);
                        
                //Add a new data record to the class
                Rider riderToAdd = null;
                riderToAdd = new Rider(riderIDIntValue, titleValue,
                        forenameValue, surnameValue, emailValue, phoneValue,
                        vehicleTypeValue, dobDateValue);
                
                System.out.println(riderIDValue + " " + titleValue + " "
                        + forenameValue + " " + surnameValue + " " + emailValue + " "
                        + phoneValue + " " + vehicleTypeValue + " " + dateOfBirthValue);
                
                //Add a new data rown to the table using the data parsed out of the JSON
                riderData[0] = riderIDValue;
                riderData[1] = titleValue;
                riderData[2] = forenameValue;
                riderData[3] = surnameValue;
                riderData[4] = emailValue;
                riderData[5] = phoneValue;
                riderData[6] = vehicleTypeValue;
                riderData[7] = dateOfBirthValue.substring(0, 10);
                riderTableModel.addRow(riderData);                
            }
            tblRiders.setModel(riderTableModel);  
            
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
    
    private void showSelectedAdmin()
    {
        int selectedRowIndex = tblAdmins.getSelectedRow();
        
        txtBranchID.setText(adminTableModel.getValueAt(selectedRowIndex, 1).toString());
        txtAdminForename.setText(adminTableModel.getValueAt(selectedRowIndex, 2).toString());
        txtAdminSurname.setText(adminTableModel.getValueAt(selectedRowIndex, 3).toString());
        txtAdminUsername.setText(adminTableModel.getValueAt(selectedRowIndex, 4).toString());
    }
    
    private void showSelectedCustomer()
    {
        int selectedRowIndex = tblCustomers.getSelectedRow();
        
        txtCustomerTitle.setText(customerTableModel.getValueAt(selectedRowIndex, 1).toString());
        txtCustomerForename.setText(customerTableModel.getValueAt(selectedRowIndex, 2).toString());
        txtCustomerSurname.setText(customerTableModel.getValueAt(selectedRowIndex, 3).toString());
        txtCustomerEmail.setText(customerTableModel.getValueAt(selectedRowIndex, 4).toString());
        txtCustomerPhone.setText(customerTableModel.getValueAt(selectedRowIndex, 5).toString());
        txtCustomerDOB.setText(customerTableModel.getValueAt(selectedRowIndex, 6).toString());
        
        
    }
    
    private void showSelectedItem()
    {
        int selectedRowIndex = tblItems.getSelectedRow();
        
        txtItemName.setText(itemTableModel.getValueAt(selectedRowIndex, 1).toString());
        txtItemType.setText(itemTableModel.getValueAt(selectedRowIndex, 2).toString());
        txtPrice.setText(itemTableModel.getValueAt(selectedRowIndex, 3).toString());
        
    }
    
    private void showSelectedRider()
    {
        int selectedRowIndex = tblRiders.getSelectedRow();
        
        txtRiderTitle.setText(riderTableModel.getValueAt(selectedRowIndex, 1).toString());
        txtRiderForename.setText(riderTableModel.getValueAt(selectedRowIndex, 2).toString());
        txtRiderSurname.setText(riderTableModel.getValueAt(selectedRowIndex, 3).toString());
        txtRiderEmail.setText(riderTableModel.getValueAt(selectedRowIndex, 4).toString());
        txtRiderPhone.setText(riderTableModel.getValueAt(selectedRowIndex, 5).toString());
        txtVehicleType.setText(riderTableModel.getValueAt(selectedRowIndex, 6).toString());
        txtRiderDOB.setText(riderTableModel.getValueAt(selectedRowIndex, 7).toString());
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCurrentTime = new javax.swing.JLabel();
        btnSignOut = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        tabAdminDashboard = new javax.swing.JTabbedPane();
        pnlAdmins = new javax.swing.JPanel();
        lblSearchAdmin = new javax.swing.JLabel();
        txtSearchAdmin = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdmins = new javax.swing.JTable();
        lblBranchID = new javax.swing.JLabel();
        lblForename = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtBranchID = new javax.swing.JTextField();
        txtAdminForename = new javax.swing.JTextField();
        txtAdminSurname = new javax.swing.JTextField();
        txtAdminUsername = new javax.swing.JTextField();
        btnCreateAdmin = new javax.swing.JButton();
        btnUpdateAdmin = new javax.swing.JButton();
        btnDeleteAdmin = new javax.swing.JButton();
        btnClearAdmin = new javax.swing.JButton();
        pnlCustomers = new javax.swing.JPanel();
        lblSearchCustomers = new javax.swing.JLabel();
        txtSearchCustomers = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        lblCustomerTitle = new javax.swing.JLabel();
        txtCustomerTitle = new javax.swing.JTextField();
        txtCustomerForename = new javax.swing.JTextField();
        txtCustomerSurname = new javax.swing.JTextField();
        txtCustomerEmail = new javax.swing.JTextField();
        txtCustomerPhone = new javax.swing.JTextField();
        txtCustomerDOB = new javax.swing.JTextField();
        lblCustomerForename = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCustomerEmail = new javax.swing.JLabel();
        lblcustomerPhone = new javax.swing.JLabel();
        lblCustomerDOB = new javax.swing.JLabel();
        btnCreateCustomer = new javax.swing.JButton();
        btnUpdateCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        btnClearCustomer = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        lblSearchItem = new javax.swing.JLabel();
        txtSearchItem = new javax.swing.JTextField();
        lblItemName = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtItemType = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        lblItemType = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        btnClearItem = new javax.swing.JButton();
        btnCreateItem = new javax.swing.JButton();
        btnUpdateItem = new javax.swing.JButton();
        btnDeleteItem = new javax.swing.JButton();
        pnlRiders = new javax.swing.JPanel();
        lblSearchRiders = new javax.swing.JLabel();
        txtSearchRiders = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRiders = new javax.swing.JTable();
        lblRiderTitle = new javax.swing.JLabel();
        txtRiderTitle = new javax.swing.JTextField();
        txtRiderForename = new javax.swing.JTextField();
        txtRiderSurname = new javax.swing.JTextField();
        txtRiderEmail = new javax.swing.JTextField();
        lblRiderForename = new javax.swing.JLabel();
        lblRiderSurname = new javax.swing.JLabel();
        lblRiderEmail = new javax.swing.JLabel();
        lblRiderPhone = new javax.swing.JLabel();
        txtRiderPhone = new javax.swing.JTextField();
        lblVehicleType = new javax.swing.JLabel();
        lblRiderDOB = new javax.swing.JLabel();
        txtVehicleType = new javax.swing.JTextField();
        txtRiderDOB = new javax.swing.JTextField();
        btnClearRider = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCurrentTime.setText("00:00:00");

        btnSignOut.setText("Sign Out");
        btnSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignOutActionPerformed(evt);
            }
        });

        lblSearchAdmin.setText("Search:");

        tblAdmins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAdmins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAdmins);

        lblBranchID.setText("BranchID");

        lblForename.setText("Forename:");

        lblSurname.setText("Surname:");

        lblUsername.setText("Username:");

        btnCreateAdmin.setText("Create Admin");

        btnUpdateAdmin.setText("Update Admin");

        btnDeleteAdmin.setText("Delete Admin");

        btnClearAdmin.setText("Clear");

        javax.swing.GroupLayout pnlAdminsLayout = new javax.swing.GroupLayout(pnlAdmins);
        pnlAdmins.setLayout(pnlAdminsLayout);
        pnlAdminsLayout.setHorizontalGroup(
            pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminsLayout.createSequentialGroup()
                .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAdminsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                            .addGroup(pnlAdminsLayout.createSequentialGroup()
                                .addComponent(lblSearchAdmin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlAdminsLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(btnCreateAdmin)
                        .addGap(67, 67, 67)
                        .addComponent(btnUpdateAdmin)
                        .addGap(66, 66, 66)
                        .addComponent(btnDeleteAdmin))
                    .addGroup(pnlAdminsLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBranchID)
                            .addComponent(txtBranchID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblForename)
                            .addComponent(txtAdminForename, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSurname)
                            .addComponent(txtAdminSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addGroup(pnlAdminsLayout.createSequentialGroup()
                                .addComponent(txtAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClearAdmin)))))
                .addContainerGap())
        );
        pnlAdminsLayout.setVerticalGroup(
            pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchAdmin)
                    .addComponent(txtSearchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBranchID)
                    .addComponent(lblForename)
                    .addComponent(lblSurname)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBranchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdminUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearAdmin))
                .addGap(37, 37, 37)
                .addGroup(pnlAdminsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        tabAdminDashboard.addTab("Admin", pnlAdmins);

        lblSearchCustomers.setText("Search:");

        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomers);

        lblCustomerTitle.setText("Title:");

        lblCustomerForename.setText("First Name:");

        jLabel3.setText("Surname:");

        lblCustomerEmail.setText("Email Address:");

        lblcustomerPhone.setText("Phone Number:");

        lblCustomerDOB.setText("Date of Birth:");

        btnCreateCustomer.setText("Create");

        btnUpdateCustomer.setText("Update");

        btnDeleteCustomer.setText("Delete");

        btnClearCustomer.setText("Clear");

        javax.swing.GroupLayout pnlCustomersLayout = new javax.swing.GroupLayout(pnlCustomers);
        pnlCustomers.setLayout(pnlCustomersLayout);
        pnlCustomersLayout.setHorizontalGroup(
            pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(pnlCustomersLayout.createSequentialGroup()
                        .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCustomersLayout.createSequentialGroup()
                                .addComponent(lblSearchCustomers)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlCustomersLayout.createSequentialGroup()
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCustomerTitle))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerForename, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCustomerForename))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCustomerEmail))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblcustomerPhone))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCustomerDOB)
                                    .addComponent(txtCustomerDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlCustomersLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(btnCreateCustomer)
                .addGap(57, 57, 57)
                .addComponent(btnUpdateCustomer)
                .addGap(53, 53, 53)
                .addComponent(btnDeleteCustomer)
                .addGap(55, 55, 55)
                .addComponent(btnClearCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCustomersLayout.setVerticalGroup(
            pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCustomersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchCustomers)
                    .addComponent(txtSearchCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomerTitle)
                    .addComponent(lblCustomerForename)
                    .addComponent(jLabel3)
                    .addComponent(lblCustomerEmail)
                    .addComponent(lblcustomerPhone)
                    .addComponent(lblCustomerDOB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(pnlCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateCustomer)
                    .addComponent(btnUpdateCustomer)
                    .addComponent(btnDeleteCustomer)
                    .addComponent(btnClearCustomer))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        tabAdminDashboard.addTab("Customers", pnlCustomers);

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblItems);

        lblSearchItem.setText("Search:");

        lblItemName.setText("Item Name:");

        lblItemType.setText("Item Type:");

        lblPrice.setText("Selling Price:");

        btnClearItem.setText("Clear");

        btnCreateItem.setText("Create");

        btnUpdateItem.setText("Update");

        btnDeleteItem.setText("Delete");

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                            .addGroup(pnlMenuLayout.createSequentialGroup()
                                .addComponent(lblSearchItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItemName))
                        .addGap(39, 39, 39)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblItemType))
                        .addGap(34, 34, 34)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrice)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClearItem)))
                .addContainerGap())
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(btnCreateItem)
                .addGap(75, 75, 75)
                .addComponent(btnUpdateItem)
                .addGap(69, 69, 69)
                .addComponent(btnDeleteItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchItem)
                    .addComponent(txtSearchItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblItemName)
                            .addComponent(lblItemType)
                            .addComponent(lblPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtItemType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlMenuLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnClearItem)))
                .addGap(27, 27, 27)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateItem)
                    .addComponent(btnUpdateItem)
                    .addComponent(btnDeleteItem))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        tabAdminDashboard.addTab("Menu", pnlMenu);

        lblSearchRiders.setText("Search:");

        tblRiders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblRiders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRidersMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblRiders);

        lblRiderTitle.setText("Title:");

        lblRiderForename.setText("First Name:");

        lblRiderSurname.setText("Surname:");

        lblRiderEmail.setText("Email Address:");

        lblRiderPhone.setText("Phone Number:");

        lblVehicleType.setText("Vehicle Type:");

        lblRiderDOB.setText("Date of Birth:");

        btnClearRider.setText("Clear");

        btnCreate.setText("Create");

        btnUpdate.setText("Update");

        btnDelete.setText("Delete");

        javax.swing.GroupLayout pnlRidersLayout = new javax.swing.GroupLayout(pnlRiders);
        pnlRiders.setLayout(pnlRidersLayout);
        pnlRidersLayout.setHorizontalGroup(
            pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRidersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                    .addGroup(pnlRidersLayout.createSequentialGroup()
                        .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRidersLayout.createSequentialGroup()
                                .addComponent(lblSearchRiders)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchRiders, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlRidersLayout.createSequentialGroup()
                                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtRiderPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(txtRiderTitle, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblRiderTitle, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(lblRiderPhone))
                                .addGap(18, 18, 18)
                                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRiderForename)
                                    .addComponent(lblRiderForename)
                                    .addComponent(lblVehicleType)
                                    .addComponent(txtVehicleType, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnClearRider)
                                        .addGroup(pnlRidersLayout.createSequentialGroup()
                                            .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtRiderDOB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                                .addComponent(txtRiderSurname, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblRiderSurname, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblRiderEmail)
                                                .addComponent(txtRiderEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(lblRiderDOB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(65, 65, 65)))
                .addContainerGap())
        );
        pnlRidersLayout.setVerticalGroup(
            pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRidersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchRiders)
                    .addComponent(txtSearchRiders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRiderTitle)
                    .addComponent(lblRiderForename)
                    .addComponent(lblRiderSurname)
                    .addComponent(lblRiderEmail))
                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRidersLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRiderTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiderForename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiderSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiderEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlRidersLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnCreate)))
                .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRidersLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRiderPhone)
                            .addComponent(lblVehicleType)
                            .addComponent(lblRiderDOB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRiderPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRiderDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearRider)))
                    .addGroup(pnlRidersLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        tabAdminDashboard.addTab("Riders", pnlRiders);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabAdminDashboard)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCurrentTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSignOut)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurrentTime)
                    .addComponent(btnSignOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabAdminDashboard)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAdminsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminsMouseClicked
        // TODO add your handling code here:
        showSelectedAdmin();
    }//GEN-LAST:event_tblAdminsMouseClicked

    private void tblCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomersMouseClicked
        // TODO add your handling code here:
        showSelectedCustomer();
    }//GEN-LAST:event_tblCustomersMouseClicked

    private void tblRidersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRidersMouseClicked
        // TODO add your handling code here:
        showSelectedRider();
    }//GEN-LAST:event_tblRidersMouseClicked

    private void tblItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemsMouseClicked
        // TODO add your handling code here:
        showSelectedItem();
    }//GEN-LAST:event_tblItemsMouseClicked

    private void btnSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignOutActionPerformed
        // TODO add your handling code here:
        new HomeScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSignOutActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAdmin;
    private javax.swing.JButton btnClearCustomer;
    private javax.swing.JButton btnClearItem;
    private javax.swing.JButton btnClearRider;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreateAdmin;
    private javax.swing.JButton btnCreateCustomer;
    private javax.swing.JButton btnCreateItem;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteAdmin;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnDeleteItem;
    private javax.swing.JButton btnSignOut;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateAdmin;
    private javax.swing.JButton btnUpdateCustomer;
    private javax.swing.JButton btnUpdateItem;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBranchID;
    private javax.swing.JLabel lblCurrentTime;
    private javax.swing.JLabel lblCustomerDOB;
    private javax.swing.JLabel lblCustomerEmail;
    private javax.swing.JLabel lblCustomerForename;
    private javax.swing.JLabel lblCustomerTitle;
    private javax.swing.JLabel lblForename;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblItemType;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblRiderDOB;
    private javax.swing.JLabel lblRiderEmail;
    private javax.swing.JLabel lblRiderForename;
    private javax.swing.JLabel lblRiderPhone;
    private javax.swing.JLabel lblRiderSurname;
    private javax.swing.JLabel lblRiderTitle;
    private javax.swing.JLabel lblSearchAdmin;
    private javax.swing.JLabel lblSearchCustomers;
    private javax.swing.JLabel lblSearchItem;
    private javax.swing.JLabel lblSearchRiders;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblVehicleType;
    private javax.swing.JLabel lblcustomerPhone;
    private javax.swing.JPanel pnlAdmins;
    private javax.swing.JPanel pnlCustomers;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlRiders;
    private javax.swing.JTabbedPane tabAdminDashboard;
    private javax.swing.JTable tblAdmins;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JTable tblItems;
    private javax.swing.JTable tblRiders;
    private javax.swing.JTextField txtAdminForename;
    private javax.swing.JTextField txtAdminSurname;
    private javax.swing.JTextField txtAdminUsername;
    private javax.swing.JTextField txtBranchID;
    private javax.swing.JTextField txtCustomerDOB;
    private javax.swing.JTextField txtCustomerEmail;
    private javax.swing.JTextField txtCustomerForename;
    private javax.swing.JTextField txtCustomerPhone;
    private javax.swing.JTextField txtCustomerSurname;
    private javax.swing.JTextField txtCustomerTitle;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemType;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtRiderDOB;
    private javax.swing.JTextField txtRiderEmail;
    private javax.swing.JTextField txtRiderForename;
    private javax.swing.JTextField txtRiderPhone;
    private javax.swing.JTextField txtRiderSurname;
    private javax.swing.JTextField txtRiderTitle;
    private javax.swing.JTextField txtSearchAdmin;
    private javax.swing.JTextField txtSearchCustomers;
    private javax.swing.JTextField txtSearchItem;
    private javax.swing.JTextField txtSearchRiders;
    private javax.swing.JTextField txtVehicleType;
    // End of variables declaration//GEN-END:variables
}
