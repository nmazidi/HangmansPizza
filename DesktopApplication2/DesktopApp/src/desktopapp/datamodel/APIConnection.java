/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp.datamodel;

import com.google.gson.Gson;
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
    public static String url;
    
    //HTTP GET request
    public String getRequest(String uri) throws IOException {
        String output = "";
        url = uri;
                         
            try {             
                                
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            
                //set request method and request header
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Type", "application/json" );
                con.setRequestProperty("Accept", "application/json" );
                
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
            
                output = response.toString();
            
                System.out.println(output);                                                  
                
                
                
            } catch (IOException e1) {
                System.out.println("IOException");
                
            }     
            
            return output;
    }
    public String getRequestByID(String uri) throws IOException {
        String output = "";
        url = uri;
                         
            try {             
                                
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            
                //set request method and request header
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Content-Type", "application/json" );
                con.setRequestProperty("Accept", "application/json" );
                
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
            
                output = response.toString();
            
                System.out.println(output);                                                  
                
                
                
            } catch (IOException e1) {
                System.out.println("IOException");
                
            }     
            
            return output;
    }
    //HTTP POST request
    public void postRequest(String uri) throws IOException, MalformedURLException, ProtocolException {
                	
	url = uri;
        
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	//add reuqest header
	con.setRequestMethod("POST");
	con.setRequestProperty("User-Agent", USER_AGENT);
	con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
        con.setRequestProperty("Accept", "application/json" );

        String urlParameters = "INGREDIENT_TYPE_ID=1&INGREDIENT_TYPE1=Topping&DESCRIPTION=Goes on top of the pizzas";

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
    
    
    public void putRequest(String uri, String payload) throws IOException, MalformedURLException, ProtocolException {        
        
        url = uri;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );            
        
            

            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());
            osw.write(payload);
            osw.close();
            con.getInputStream();            
            
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
    
    public void deleteRequest(String uri) throws IOException, MalformedURLException, ProtocolException { 
        url = uri;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
        con.setDoOutput(true);
        con.connect();
        
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);
    }
    
}
