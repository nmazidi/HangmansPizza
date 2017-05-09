/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.gui;

import desktopapp.datamodel.APIConnection;
import desktopapp.datamodel.Orders;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Nathan
 */
public class KitchenPage extends javax.swing.JFrame {

    private APIConnection kitchen;
    /**
     * Creates new form KitchenPage
     */
    public KitchenPage() {
        initComponents();
        loadTimer();
        fillOrders();
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
    
    private void fillOrders() {
        String response;
        String urlOrder;
        String urlOrderItem;
        String orderIDField = "ORDER_ID";
        String customerIDField = "CUSTOMER_ID";
        String datePlacedField = "DATE_PLACED";
        String totalCostField = "TOTAL_COST";
        String orderTypeField = "ORDER_TYPE";
        String notesField = "NOTES";
        String orderStatusField = "ORDER_STATUS";
        String orderIDValue;
        int orderIDIntValue;
        String customerIDValue;
        int customerIDIntValue;
        String datePlacedValue;
        Date datePlacedDateValue;
        String totalCostValue;
        double totalCostDoubleValue;
        String orderTypeValue;
        String notesValue;
        String orderStatusValue;                
        int orderIDFieldLocation;
        int customerIDFieldLocation;
        int datePlacedFieldLocation;
        int totalCostFieldLocation;
        int orderTypeFieldLocation;
        int notesFieldLocation;
        int orderStatusFieldLocation;
        
        
        //Set URL values for 
        urlOrder = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders";
        urlOrderItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/order_item";
        
        try {
            //Create instance of APIConnection
            kitchen = new APIConnection();
            //Set get json response to a string variable
            response = kitchen.getRequest(urlOrder);
            
            String [] allOrders = response.split("},{");
            
            
            
            for (int i = 0; i < allOrders.length; i++)
            {
                String tempOrder = allOrders[i];
                int commaIndex = tempOrder.indexOf(",");
                int [] commas = new int[6];
                int j = 0;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                
                orderIDFieldLocation = tempOrder.lastIndexOf(orderIDField);
                customerIDFieldLocation = tempOrder.lastIndexOf(customerIDField);
                datePlacedFieldLocation = tempOrder.lastIndexOf(datePlacedField);
                totalCostFieldLocation = tempOrder.lastIndexOf(totalCostField);
                orderTypeFieldLocation = tempOrder.lastIndexOf(orderTypeField);
                notesFieldLocation = tempOrder.lastIndexOf(notesField);
                orderStatusFieldLocation = tempOrder.lastIndexOf(orderStatusField);               
                
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempOrder.indexOf(",", commaIndex + 1); 
                    j++;
                }                
                
                orderIDValue = tempOrder.substring(orderIDFieldLocation + 3, commas[0]);
                orderIDIntValue = Integer.parseInt(orderIDValue);
                customerIDValue = tempOrder.substring(customerIDFieldLocation + 4, commas[1] - 1);
                customerIDIntValue = Integer.parseInt(customerIDValue);
                datePlacedValue = tempOrder.substring(datePlacedFieldLocation + 4, commas[2] - 1);
                datePlacedDateValue = format.parse(datePlacedValue);
                totalCostValue = tempOrder.substring(totalCostFieldLocation + 4, commas[3] - 1);
                totalCostDoubleValue = Double.parseDouble(totalCostValue);
                orderTypeValue = tempOrder.substring(orderTypeFieldLocation + 4, commas[4] - 1);
                notesValue = tempOrder.substring(notesFieldLocation + 4, commas[5] - 1);
                orderStatusValue = tempOrder.substring(orderStatusFieldLocation + 4, tempOrder.length());
                
                Orders orderToAdd = null;
                orderToAdd = new Orders(orderIDIntValue, customerIDIntValue,
                        datePlacedDateValue, totalCostDoubleValue,
                        orderTypeValue, notesValue, orderStatusValue);                                     
                
                lblOrderNumber1.setText("orderIDValue");
                lblOrderNumber1.setText("datePlacedValue");
                
            }
            
            
            
            
            
            
            
            
            
            txtOrderDetails1.setText(response);
            
        } catch (Exception e3) {
            System.out.println("IOException");
        }
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
        btnBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        lblOrderNumber1 = new javax.swing.JLabel();
        lblOrderTime1 = new javax.swing.JLabel();
        cmbOrderStatus1 = new javax.swing.JComboBox<>();
        txtOrderDetails1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblOrderNumber2 = new javax.swing.JLabel();
        lblOrderTime2 = new javax.swing.JLabel();
        cmbOrderStatus2 = new javax.swing.JComboBox<>();
        txtOrderDetails2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblOrderNumber3 = new javax.swing.JLabel();
        lblOrderTime3 = new javax.swing.JLabel();
        cmbOrderStatus3 = new javax.swing.JComboBox<>();
        txtOrderDetails3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblOrderNumber4 = new javax.swing.JLabel();
        lblOrderTime4 = new javax.swing.JLabel();
        cmbOrderStatus4 = new javax.swing.JComboBox<>();
        txtOrderDetails4 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblOrderNumber5 = new javax.swing.JLabel();
        lblOrderTime5 = new javax.swing.JLabel();
        cmbOrderStatus5 = new javax.swing.JComboBox<>();
        txtOrderDetails5 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lblOrderNumber6 = new javax.swing.JLabel();
        lblOrderTime6 = new javax.swing.JLabel();
        cmbOrderStatus6 = new javax.swing.JComboBox<>();
        txtOrderDetails6 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lblOrderNumber7 = new javax.swing.JLabel();
        lblOrderTime7 = new javax.swing.JLabel();
        cmbOrderStatus7 = new javax.swing.JComboBox<>();
        txtOrderDetails7 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblOrderNumber = new javax.swing.JLabel();
        lblOrderTime8 = new javax.swing.JLabel();
        cmbOrderStatus8 = new javax.swing.JComboBox<>();
        txtOrderDetails8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCurrentTime.setText("00:00:00");

        btnBack.setText("X");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber1.setText("68");

        lblOrderTime1.setText("01:00");

        cmbOrderStatus1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Queue", "Preparing", "Cooking", "Quality Control", "Ready for Delivery" }));

        txtOrderDetails1.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails1.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber1)
                    .addComponent(lblOrderTime1)
                    .addComponent(cmbOrderStatus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(185, 240));

        lblOrderNumber2.setText("69");

        lblOrderTime2.setText("02:00");

        cmbOrderStatus2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Queue", "Preparing", "Cooking", "Quality Control", "Ready for Delivery" }));

        txtOrderDetails2.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails2.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber2)
                    .addComponent(lblOrderTime2)
                    .addComponent(cmbOrderStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber3.setText("70");

        lblOrderTime3.setText("03:00");

        cmbOrderStatus3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Queue", "Preparing", "Cooking", "Quality Control", "Ready for Delivery" }));

        txtOrderDetails3.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails3.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime3)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber3)
                    .addComponent(lblOrderTime3)
                    .addComponent(cmbOrderStatus3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber4.setText("71");

        lblOrderTime4.setText("04:00");

        cmbOrderStatus4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In Queue", "Preparing", "Cooking", "Quality Control", "Ready for Delivery" }));

        txtOrderDetails4.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails4.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber4)
                    .addComponent(lblOrderTime4)
                    .addComponent(cmbOrderStatus4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel5.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber5.setText("72");

        lblOrderTime5.setText("05:00");

        cmbOrderStatus5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtOrderDetails5.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails5.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime5)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber5)
                    .addComponent(lblOrderTime5)
                    .addComponent(cmbOrderStatus5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel6.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber6.setText("73");

        lblOrderTime6.setText("06:00");

        cmbOrderStatus6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtOrderDetails6.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails6.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime6)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber6)
                    .addComponent(lblOrderTime6)
                    .addComponent(cmbOrderStatus6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel7.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber7.setText("74");

        lblOrderTime7.setText("07:00");

        cmbOrderStatus7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtOrderDetails7.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails7.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime7)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber7)
                    .addComponent(lblOrderTime7)
                    .addComponent(cmbOrderStatus7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel8.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber.setText("75");

        lblOrderTime8.setText("08:00");

        cmbOrderStatus8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtOrderDetails8.setMaximumSize(new java.awt.Dimension(163, 190));
        txtOrderDetails8.setPreferredSize(new java.awt.Dimension(163, 190));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrderDetails8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbOrderStatus8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOrderTime8)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber)
                    .addComponent(lblOrderTime8)
                    .addComponent(cmbOrderStatus8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrderDetails8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCurrentTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 172, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurrentTime)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(KitchenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitchenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitchenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitchenPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitchenPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox<String> cmbOrderStatus1;
    private javax.swing.JComboBox<String> cmbOrderStatus2;
    private javax.swing.JComboBox<String> cmbOrderStatus3;
    private javax.swing.JComboBox<String> cmbOrderStatus4;
    private javax.swing.JComboBox<String> cmbOrderStatus5;
    private javax.swing.JComboBox<String> cmbOrderStatus6;
    private javax.swing.JComboBox<String> cmbOrderStatus7;
    private javax.swing.JComboBox<String> cmbOrderStatus8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCurrentTime;
    private javax.swing.JLabel lblOrderNumber;
    private javax.swing.JLabel lblOrderNumber1;
    private javax.swing.JLabel lblOrderNumber2;
    private javax.swing.JLabel lblOrderNumber3;
    private javax.swing.JLabel lblOrderNumber4;
    private javax.swing.JLabel lblOrderNumber5;
    private javax.swing.JLabel lblOrderNumber6;
    private javax.swing.JLabel lblOrderNumber7;
    private javax.swing.JLabel lblOrderTime1;
    private javax.swing.JLabel lblOrderTime2;
    private javax.swing.JLabel lblOrderTime3;
    private javax.swing.JLabel lblOrderTime4;
    private javax.swing.JLabel lblOrderTime5;
    private javax.swing.JLabel lblOrderTime6;
    private javax.swing.JLabel lblOrderTime7;
    private javax.swing.JLabel lblOrderTime8;
    private javax.swing.JTextField txtOrderDetails1;
    private javax.swing.JTextField txtOrderDetails2;
    private javax.swing.JTextField txtOrderDetails3;
    private javax.swing.JTextField txtOrderDetails4;
    private javax.swing.JTextField txtOrderDetails5;
    private javax.swing.JTextField txtOrderDetails6;
    private javax.swing.JTextField txtOrderDetails7;
    private javax.swing.JTextField txtOrderDetails8;
    // End of variables declaration//GEN-END:variables
}
