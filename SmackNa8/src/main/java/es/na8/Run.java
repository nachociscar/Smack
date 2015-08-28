package es.na8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Run {

	String publicIP ="";
	
		public static void main(String args[]){
			Run run = new Run();
			run.getIP();
			
			
		}
		
	 
	    public String  getIP() {
	    	try {
	                URL tempURL = new URL("http://api.ipify.org/");
//	                System.setProperty("http.proxyHost", "sqd3v1.si.generali.es");
//	                System.setProperty("http.proxyPort", "3128");
	                HttpURLConnection tempConn = (HttpURLConnection)tempURL.openConnection();
	                InputStream tempInStream = tempConn.getInputStream();
	                InputStreamReader tempIsr = new InputStreamReader(tempInStream);
	                BufferedReader tempBr = new BufferedReader(tempIsr);        
	                
	                 publicIP = tempBr.readLine();
	 
	                tempBr.close();
	                tempInStream.close();
	 
	        } catch (Exception ex) {
	                publicIP = "<No es posible resolver la direccion IP>";   
	          }
	 
	         System.out.println("Mi IP Publica es " +publicIP);
	         return publicIP;
	    }
	
	
}
