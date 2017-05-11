/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.gui;

import desktopapp.datamodel.APIConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Nathan
 */
public class KitchenPage extends javax.swing.JFrame {

    private APIConnection mainOrder;
    private APIConnection orderItems;
    private APIConnection itemName;
    private APIConnection changeOrderStatus;
    
    private String [][] orderArray;
    private int arraySize;
    /**
     * Creates new form KitchenPage
     */
    public KitchenPage() {
        initComponents();  
        
       Timer fillTimer = new Timer(0, new ActionListener(){
           
           @Override
           public void actionPerformed(ActionEvent e) {
                fillOrders();
           }
       });
       
       fillTimer.setDelay(10000);
       fillTimer.start();
           
       
               
    }

    
    
    private void fillOrders() {
        String response;
        String urlOrder;
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
        ArrayList<String> waitingList = new ArrayList<String>();        
        ArrayList<String> readyList = new ArrayList<String>();
        ArrayList<String> items = new ArrayList<String>();
        
         
        
        //Set URL values for 
        urlOrder = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders";
        
        try {
            //Create instance of APIConnection
            mainOrder = new APIConnection();
            //Set get json response to a string variable
            response = mainOrder.getRequest(urlOrder);
            
            String [] allOrders = response.split("\\},\\{");           
            orderArray = new String[allOrders.length][7];  
            
      //      for (int i = 0; i < 14; i++) {
      //          waitingList.add("-1");                
      //      }
            
            arraySize = allOrders.length;
            
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
                
                if (i == allOrders.length - 1)
                {
                    orderIDValue = tempOrder.substring(orderIDFieldLocation + 10, commas[0]);
                    orderIDIntValue = Integer.parseInt(orderIDValue);
                    customerIDValue = tempOrder.substring(customerIDFieldLocation + 13, commas[1]);
                    customerIDIntValue = Integer.parseInt(customerIDValue);
                    datePlacedValue = tempOrder.substring(datePlacedFieldLocation + 14, commas[2] - 1);
                    datePlacedDateValue = format.parse(datePlacedValue);
                    totalCostValue = tempOrder.substring(totalCostFieldLocation + 12, commas[3]);
                    totalCostDoubleValue = Double.parseDouble(totalCostValue);
                    orderTypeValue = tempOrder.substring(orderTypeFieldLocation + 12, commas[4] - 1);
                    notesValue = tempOrder.substring(notesFieldLocation + 7, commas[5] - 1);
                    orderStatusValue = tempOrder.substring(orderStatusFieldLocation + 15, tempOrder.length() - 3);                                       
                } else {
                    orderIDValue = tempOrder.substring(orderIDFieldLocation + 10, commas[0]);
                    orderIDIntValue = Integer.parseInt(orderIDValue);
                    customerIDValue = tempOrder.substring(customerIDFieldLocation + 13, commas[1]);
                    customerIDIntValue = Integer.parseInt(customerIDValue);
                    datePlacedValue = tempOrder.substring(datePlacedFieldLocation + 14, commas[2] - 1);
                    datePlacedDateValue = format.parse(datePlacedValue);
                    totalCostValue = tempOrder.substring(totalCostFieldLocation + 12, commas[3]);
                    totalCostDoubleValue = Double.parseDouble(totalCostValue);
                    orderTypeValue = tempOrder.substring(orderTypeFieldLocation + 12, commas[4] - 1);
                    notesValue = tempOrder.substring(notesFieldLocation + 7, commas[5] - 1);
                    orderStatusValue = tempOrder.substring(orderStatusFieldLocation + 15, tempOrder.length() - 1);
                }
                
                orderArray[i][0] = orderIDValue;
                orderArray[i][1] = customerIDValue;
                orderArray[i][2] = datePlacedValue;
                orderArray[i][3] = totalCostValue;
                orderArray[i][4] = orderTypeValue;
                orderArray[i][5] = notesValue;
                orderArray[i][6] = orderStatusValue;
                
                if (orderStatusValue.equals("PLACED") || orderStatusValue.equals("READY") || orderStatusValue.equals("PREP"))
                {                    
                    if (lblOrderNumber1.getText() == "0") {
                        items = findOrderItems(orderIDIntValue);
                        
                        lblOrderNumber1.setText(orderIDValue);
                        lblOrderTime1.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus1.setText(orderStatusValue);                        
                        
                        for (String ite : items) {
                            txtOrderItems1.append(ite + "\n");
                        }      
                    } else if (lblOrderNumber2.getText() == "0") {
                        lblOrderNumber2.setText(orderIDValue);
                        lblOrderTime2.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus2.setText(orderStatusValue);
                    } else if (lblOrderNumber3.getText() == "0") {
                        lblOrderNumber3.setText(orderIDValue);
                        lblOrderTime3.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus3.setText(orderStatusValue);
                    } else if (lblOrderNumber4.getText() == "0") {
                        lblOrderNumber4.setText(orderIDValue);
                        lblOrderTime4.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus4.setText(orderStatusValue);
                    } else if (lblOrderNumber5.getText() == "0") {
                        lblOrderNumber5.setText(orderIDValue);
                        lblOrderTime5.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus5.setText(orderStatusValue);
                    } else if (lblOrderNumber6.getText() == "0") {
                        lblOrderNumber6.setText(orderIDValue);
                        lblOrderTime6.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus6.setText(orderStatusValue);
                    } else if (lblOrderNumber7.getText() == "0") {
                        lblOrderNumber7.setText(orderIDValue);
                        lblOrderTime7.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus7.setText(orderStatusValue);
                    } else if (lblOrderNumber8.getText() == "0") {
                        lblOrderNumber8.setText(orderIDValue);
                        lblOrderTime8.setText(datePlacedValue.substring(11, 19));
                        btnOrderStatus8.setText(orderStatusValue);
                    } 
                    
               //     waitingList.add(orderIDValue);
               //     jTextArea1.append("Order ID: " + orderIDValue);
                      
                }                   
            }
            
            
        } catch (Exception e3) {
            System.out.println("IOException");
        }
    }
    
    private ArrayList<String> findOrderItems(int orderID) 
    {       
        String response;
        String urlOrderItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/order_item";
        String itemIDField = "ITEM_ID";
        String orderIDField = "ORDER_ID";
        String quantityField = "QUANTITY";
        String sizeField = "ITEM_SIZE";
        String baseField = "ITEM_CRUSTTYPE";
        String itemIDValue;
        int itemIDIntValue;
        String orderIDValue;
        int orderIDIntValue;
        String quantityValue;
        String sizeValue;
        String baseValue;
        int itemIDFieldLocation;
        int orderIDFieldLocation;
        int quantityFieldLocation;
        int sizeFieldLocation;
        int baseFieldLocation;
        List<List<String>> prepList = new ArrayList<List<String>>();
        ArrayList<String> itemList = new ArrayList<String>();
        String [][] orderItemsArray;
        
        try {
            //Create instance of APIConnection
            orderItems = new APIConnection();
            //Set get json response to a string variable
            response = orderItems.getRequest(urlOrderItem);
            
            String [] allOrderItems = response.split("\\},\\{");           
            orderItemsArray = new String[allOrderItems.length][5];  
            
            
            
            
            for (int i = 0; i < allOrderItems.length; i++)
            {
                String tempOrderItem = allOrderItems[i];
                int commaIndex = tempOrderItem.indexOf(",");
                int [] commas = new int[6];
                int j = 0;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                
                itemIDFieldLocation = tempOrderItem.lastIndexOf(itemIDField);
                orderIDFieldLocation = tempOrderItem.lastIndexOf(orderIDField);
                quantityFieldLocation = tempOrderItem.lastIndexOf(quantityField);
                sizeFieldLocation = tempOrderItem.lastIndexOf(sizeField);
                baseFieldLocation = tempOrderItem.lastIndexOf(baseField);             
                
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempOrderItem.indexOf(",", commaIndex + 1); 
                    j++;
                }                
                
                if (i == allOrderItems.length - 1)
                {                               
                    itemIDValue = tempOrderItem.substring(itemIDFieldLocation + 9, commas[0]);
                    itemIDIntValue = Integer.parseInt(itemIDValue);
                    orderIDValue = tempOrderItem.substring(orderIDFieldLocation + 10, commas[1]);
                    orderIDIntValue = Integer.parseInt(orderIDValue);
                    quantityValue = tempOrderItem.substring(quantityFieldLocation + 10, commas[2]);
                    sizeValue = tempOrderItem.substring(sizeFieldLocation + 11, commas[4] - 1);
                    baseValue = tempOrderItem.substring(baseFieldLocation + 17, tempOrderItem.length() - 3);
                } else
                {
                    itemIDValue = tempOrderItem.substring(itemIDFieldLocation + 9, commas[0]);
                    itemIDIntValue = Integer.parseInt(itemIDValue);
                    orderIDValue = tempOrderItem.substring(orderIDFieldLocation + 10, commas[1]);
                    orderIDIntValue = Integer.parseInt(orderIDValue);
                    quantityValue = tempOrderItem.substring(quantityFieldLocation + 10, commas[2]);
                    sizeValue = tempOrderItem.substring(sizeFieldLocation + 11, commas[4] - 1);
                    baseValue = tempOrderItem.substring(baseFieldLocation + 17, tempOrderItem.length() - 1);
                }
                               
                orderItemsArray[i][0] = itemIDValue;
                orderItemsArray[i][1] = orderIDValue;
                orderItemsArray[i][2] = quantityValue;
                orderItemsArray[i][3] = sizeValue;
                orderItemsArray[i][4] = baseValue;
                
                String itemName = findItemName(itemIDValue);
                
                itemList.add(itemName);
                itemList.add(orderIDValue);
                itemList.add(quantityValue);
                itemList.add(sizeValue);
                itemList.add(baseValue);         
            }                                
        } catch (Exception e3) {
            System.out.println("IOException");
        }
        
        return itemList;
    }
    
    private String findItemName(String itemID) 
    {
        String response;
        String urlItemName = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/items?id=" + itemID;
        String itemNameField = "ITEM_NAME";
        String itemNameValue = "";
        int itemNameFieldLocation;
        
        try {
            //Create instance of APIConnection
            itemName = new APIConnection();
            //Set get json response to a string variable
            response = itemName.getRequest(urlItemName);
            
            String [] allItemNames = response.split("\\},\\{");                                       
            
            for (int i = 0; i < allItemNames.length; i++)
            {
                String tempItemName = allItemNames[i];
                int commaIndex = tempItemName.indexOf(",");
                int [] commas = new int[3];
                int j = 0;
                
                itemNameFieldLocation = tempItemName.lastIndexOf(itemNameField);          
                
                while (commaIndex >= 0) {
                    commas[j] = commaIndex;
                    commaIndex = tempItemName.indexOf(",", commaIndex + 1); 
                    j++;
                }                
                
                itemNameValue = tempItemName.substring(itemNameFieldLocation + 12, commas[1] - 1);                           
            }                                                                                                                         
        } catch (Exception e3) {
            System.out.println("IOException");
        }
             
        return itemNameValue;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOrderItems1 = new javax.swing.JTextArea();
        btnOrderStatus1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblOrderNumber2 = new javax.swing.JLabel();
        lblOrderTime2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOrderItems2 = new javax.swing.JTextArea();
        btnOrderStatus2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblOrderNumber3 = new javax.swing.JLabel();
        lblOrderTime3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtOrderItems3 = new javax.swing.JTextArea();
        btnOrderStatus3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblOrderNumber4 = new javax.swing.JLabel();
        lblOrderTime4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtOrderItems4 = new javax.swing.JTextArea();
        btnOrderStatus4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblOrderNumber5 = new javax.swing.JLabel();
        lblOrderTime5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtOrderItems5 = new javax.swing.JTextArea();
        btnOrderStatus5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblOrderNumber6 = new javax.swing.JLabel();
        lblOrderTime6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtOrderItems6 = new javax.swing.JTextArea();
        btnOrderStatus6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblOrderNumber7 = new javax.swing.JLabel();
        lblOrderTime7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtOrderItems7 = new javax.swing.JTextArea();
        btnOrderStatus7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblOrderNumber8 = new javax.swing.JLabel();
        lblOrderTime8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtOrderItems8 = new javax.swing.JTextArea();
        btnOrderStatus8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCurrentTime.setText("00:00:00");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber1.setText("0");

        lblOrderTime1.setText("01:00");

        txtOrderItems1.setEditable(false);
        txtOrderItems1.setColumns(20);
        txtOrderItems1.setRows(5);
        jScrollPane1.setViewportView(txtOrderItems1);

        btnOrderStatus1.setText("PLACED");
        btnOrderStatus1.setToolTipText("");
        btnOrderStatus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber1)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
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
                    .addComponent(btnOrderStatus1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setMaximumSize(new java.awt.Dimension(185, 240));

        lblOrderNumber2.setText("0");

        lblOrderTime2.setText("02:00");

        txtOrderItems2.setColumns(20);
        txtOrderItems2.setRows(5);
        jScrollPane2.setViewportView(txtOrderItems2);

        btnOrderStatus2.setText("PLACED");
        btnOrderStatus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber2)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderTime2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber2)
                    .addComponent(lblOrderTime2)
                    .addComponent(btnOrderStatus2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel3.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber3.setText("0");

        lblOrderTime3.setText("03:00");

        txtOrderItems3.setColumns(20);
        txtOrderItems3.setRows(5);
        jScrollPane3.setViewportView(txtOrderItems3);

        btnOrderStatus3.setText("PLACED");
        btnOrderStatus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber3)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderTime3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber3)
                    .addComponent(lblOrderTime3)
                    .addComponent(btnOrderStatus3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel4.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber4.setText("0");

        lblOrderTime4.setText("04:00");

        txtOrderItems4.setColumns(20);
        txtOrderItems4.setRows(5);
        jScrollPane4.setViewportView(txtOrderItems4);

        btnOrderStatus4.setText("PLACED");
        btnOrderStatus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber4)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderTime4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber4)
                    .addComponent(lblOrderTime4)
                    .addComponent(btnOrderStatus4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel5.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber5.setText("0");

        lblOrderTime5.setText("05:00");

        txtOrderItems5.setColumns(20);
        txtOrderItems5.setRows(5);
        jScrollPane5.setViewportView(txtOrderItems5);

        btnOrderStatus5.setText("PLACED");
        btnOrderStatus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber5)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
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
                    .addComponent(btnOrderStatus5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel6.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber6.setText("0");

        lblOrderTime6.setText("06:00");

        txtOrderItems6.setColumns(20);
        txtOrderItems6.setRows(5);
        jScrollPane6.setViewportView(txtOrderItems6);

        btnOrderStatus6.setText("PLACED");
        btnOrderStatus6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber6)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
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
                    .addComponent(btnOrderStatus6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel7.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber7.setText("0");

        lblOrderTime7.setText("07:00");

        txtOrderItems7.setColumns(20);
        txtOrderItems7.setRows(5);
        jScrollPane7.setViewportView(txtOrderItems7);

        btnOrderStatus7.setText("PLACED");
        btnOrderStatus7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber7)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderTime7))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber7)
                    .addComponent(lblOrderTime7)
                    .addComponent(btnOrderStatus7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setMaximumSize(new java.awt.Dimension(185, 240));
        jPanel8.setPreferredSize(new java.awt.Dimension(185, 240));

        lblOrderNumber8.setText("0");

        lblOrderTime8.setText("08:00");

        txtOrderItems8.setColumns(20);
        txtOrderItems8.setRows(5);
        jScrollPane8.setViewportView(txtOrderItems8);

        btnOrderStatus8.setText("PLACED");
        btnOrderStatus8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderStatus8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblOrderNumber8)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderStatus8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrderTime8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderNumber8)
                    .addComponent(lblOrderTime8)
                    .addComponent(btnOrderStatus8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                        .addGap(0, 13, Short.MAX_VALUE)))
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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        new HomeScreen().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnOrderStatus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus1ActionPerformed
        // TODO add your handling code here:
        
        
        if (btnOrderStatus1.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber1.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus1.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus1.getText();    
                        
                        
                    }
                }     
            }      
        } else if (btnOrderStatus1.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber1.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus1.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus1.getText();   
                        
                        int ilk = 4;
                        
                        try {                        
                            changeOrderStatus = new APIConnection();
                            changeOrderStatus.putRequest(urlItem, params);
                        } catch (Exception e) {
                            System.out.println("Exception");
                        }
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus1ActionPerformed

    private void btnOrderStatus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus2ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus2.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber2.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus2.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus2.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus2.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber2.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus2.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus2.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus2ActionPerformed

    private void btnOrderStatus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus3ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus3.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber3.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus3.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus3.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus3.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber3.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus3.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus3.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus3ActionPerformed

    private void btnOrderStatus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus4ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus4.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber4.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus4.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus4.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus4.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber4.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus4.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus4.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus4ActionPerformed

    private void btnOrderStatus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus5ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus5.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber5.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus5.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus5.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus5.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber5.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus5.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus5.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus5ActionPerformed

    private void btnOrderStatus6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus6ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus6.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber6.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus6.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus6.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus6.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber6.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus6.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus6.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus6ActionPerformed

    private void btnOrderStatus7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus7ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus7.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber7.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus7.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus7.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus7.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber7.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus7.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus7.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus7ActionPerformed

    private void btnOrderStatus8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderStatus8ActionPerformed
        // TODO add your handling code here:
        if (btnOrderStatus8.getText().equals("PLACED"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber8.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus8.setText("PREP");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus8.getText();                        
                    }
                }     
            }      
        } else if (btnOrderStatus8.getText().equals("PREP"))
        {
            int verify = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to begin cooking this order?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (verify == JOptionPane.YES_OPTION)
            {
                String orderID = lblOrderNumber8.getText();
                String urlItem = "http://Xserve.uopnet.plymouth.ac.uk/modules/INTPROJ/PRCS251Q/API/orders/" + orderID;                
                String params;               
                
                for (int i = 0; i < arraySize; i++)
                {
                    if (orderArray[i][0] == orderID)
                    {
                        btnOrderStatus8.setText("READY");
                        params = "ORDER_ID=" + orderID + "&CUSTOMER_ID=" + orderArray[i][1] + "&"
                                + "DATE_PLACED=" + orderArray[i][2] + "&TOTAL_COST=" + orderArray [i][3] + "&"
                                + "ORDER_TYPE=" + orderArray[i][4] + "&NOTES="  + orderArray[i][5] + "&ORDER_STATUS=" + btnOrderStatus8.getText();                        
                    }
                }     
            }      
        }
    }//GEN-LAST:event_btnOrderStatus8ActionPerformed

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
    private javax.swing.JButton btnOrderStatus1;
    private javax.swing.JButton btnOrderStatus2;
    private javax.swing.JButton btnOrderStatus3;
    private javax.swing.JButton btnOrderStatus4;
    private javax.swing.JButton btnOrderStatus5;
    private javax.swing.JButton btnOrderStatus6;
    private javax.swing.JButton btnOrderStatus7;
    private javax.swing.JButton btnOrderStatus8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCurrentTime;
    private javax.swing.JLabel lblOrderNumber1;
    private javax.swing.JLabel lblOrderNumber2;
    private javax.swing.JLabel lblOrderNumber3;
    private javax.swing.JLabel lblOrderNumber4;
    private javax.swing.JLabel lblOrderNumber5;
    private javax.swing.JLabel lblOrderNumber6;
    private javax.swing.JLabel lblOrderNumber7;
    private javax.swing.JLabel lblOrderNumber8;
    private javax.swing.JLabel lblOrderTime1;
    private javax.swing.JLabel lblOrderTime2;
    private javax.swing.JLabel lblOrderTime3;
    private javax.swing.JLabel lblOrderTime4;
    private javax.swing.JLabel lblOrderTime5;
    private javax.swing.JLabel lblOrderTime6;
    private javax.swing.JLabel lblOrderTime7;
    private javax.swing.JLabel lblOrderTime8;
    private javax.swing.JTextArea txtOrderItems1;
    private javax.swing.JTextArea txtOrderItems2;
    private javax.swing.JTextArea txtOrderItems3;
    private javax.swing.JTextArea txtOrderItems4;
    private javax.swing.JTextArea txtOrderItems5;
    private javax.swing.JTextArea txtOrderItems6;
    private javax.swing.JTextArea txtOrderItems7;
    private javax.swing.JTextArea txtOrderItems8;
    // End of variables declaration//GEN-END:variables
}
