package com.anupam.http.basicClient;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

/**
 * 
 * @author (Anupam Saini)
 *
 */
public class ClientRequestRetryHandler implements HttpRequestRetryHandler {
	
	  /**
	   * Simple method to check number of retries and throw an exception if 
	   * they exceed the try limit
	   */
	  public boolean retryRequest(
	            IOException exception, 
	            int executionCount,
	            HttpContext context) {
	        if (executionCount >= 5) {
	            // Do not retry if over max retry count
	            return false;
	        }
	        if (exception instanceof NoHttpResponseException) {
	            // Retry if the server dropped connection on us
	            return true;
	        }
	        if (exception instanceof SSLHandshakeException) {
	            // Do not retry on SSL handshake exception
	            return false;
	        }
	        HttpRequest request = (HttpRequest) context.getAttribute(
	                ExecutionContext.HTTP_REQUEST);
	        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest); 
	        if (idempotent) {
	            // Retry if the request is considered idempotent 
	            return true;
	        }
	        return false;
	    }
}
