/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Nathan
 */
public class APIConnection {
    
    public static final String USER_AGENT = "Mozilla/5.0";          
    public static String url = 
             "http://xserve.uopnet.plymouth.ac.uk/modules/intproj/prcs251q/API/customers/41";
    
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
                	
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	//add reuqest header
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "CUSTOMER_ID=3&CUSTOMER_TITLE=Mr&CUSTOMER_FORENAME=Nathan&CUSTOMER_SURNAME=Scarfi&CUSTOMER_EMAIL=natsca@gmail.com&CUSTOMER_PHONE=07555666777&CUSTOMER_PASSWORD=password&CUSTOMER_DOB=30-MAR-97";

	// Send post request
	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();

	int responseCode = con.getResponseCode();
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + urlParameters);
	System.out.println("Response Code : " + responseCode);

	BufferedReader in = new BufferedReader(
		 new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	//print result
	System.out.println(response.toString());               
    }
    
    
    public void putRequest() throws IOException, MalformedURLException, ProtocolException {        
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
            con.setDoOutput(true);
        
            String payload = "CUSTOMER_ID=41&CUSTOMER_TITLE=Mrs&CUSTOMER_FORENAME=Nathan&CUSTOMER_SURNAME=Scarfi&CUSTOMER_EMAIL=natsca@gmail.com&CUSTOMER_PHONE=07555666777&CUSTOMER_PASSWORD=password&CUSTOMER_DOB=30-MAR-97";


            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(payload);
            osw.close();
            con.getInputStream();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
    
    public void deleteRequest() throws IOException, MalformedURLException, ProtocolException { 
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
        con.setDoOutput(true);
        con.connect();
    }
    
}
