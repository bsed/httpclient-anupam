package com.anupam.http.basicClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HttpContext;

public class GetRequestThreads extends Thread {
	
	  private final HttpClient httpClient;  
      private final HttpContext context;  
      private final HttpGet httpget;  
      private final int id;  
      
      public GetRequestThreads(HttpClient httpClient,HttpContext context, HttpGet httpget, int id) {  
          this.httpClient = httpClient;  
          this.context = context;  
          this.httpget = httpget;  
          this.id = id;  
      }  

      /** 
       * Executes the GetMethod . 
       */  
      @Override  
      public void run() {  

          System.out.println(id + " - about to get something from " + httpget.getURI());  

          try {  

              // execute the method  
        	  ResponseHandler<byte []> responseArray = new ClientResponseHandler();
        	  byte[] response = httpClient.execute(httpget, responseArray, context);  
              
        	  BasicClient.saveToFileSystem(new ByteArrayInputStream(response), (Calendar.getInstance()).getTime().getTime());
        	  this.interrupt();
        	  
          }catch (ClientProtocolException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
      }  

}  


