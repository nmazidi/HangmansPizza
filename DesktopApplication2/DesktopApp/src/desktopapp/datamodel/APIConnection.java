/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Nathan
 */
public class APIConnection {
    
    public static final String USER_AGENT = "Mozilla/5.0";          
    public static String url = 
                    "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251q/API/customers";
    
    //HTTP GET request
    public void getRequest() throws IOException {
                        
            try {
                
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            
                //set request method and request header
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
            
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);
            
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
            
                while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);            
                }
                in.close();
            
                String output = response.toString();
            
                System.out.println(output);
                  
            } catch (IOException e1) {
                System.out.println("IOException");
            }           
            
    }
    
    //HTTP POST request
    public void postRequest() throws IOException, MalformedURLException, ProtocolException {
        
        URL url = new URL("http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251q/API/customers");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("CUSTOMER_ID", 3);
        params.put("CUSTOMER_TITLE", "Mr");
        params.put("CUSTOMER_FORENAME", "Nathan");
        params.put("CUSTOMER_SURNAME", "Scarfi");
        params.put("CUSTOMER_EMAIL", "nathanscarfi@gmail.com");
        params.put("CUSTOMER_PHONE", "07565772859");
        params.put("CUSTOMER_PASSWORD", "password");
        params.put("CUSTOMER_DOB", "30-MAR-97");
        
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        
        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c); 
        
        
    }
    
}
