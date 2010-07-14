package com.anupam.http.basicClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.BufferedHttpEntity;

public class ClientResponseHandler implements ResponseHandler<byte []> {

	/**
	 * Response handler generates a byte array of response ,
	 * containing recieved data
	 */
	public byte [] handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {

		// Debugging code
		//printResponseRelatedMetaInfo(response);
		
		//Fetch the response content here
		HttpEntity entity = response.getEntity();
		entity =  new BufferedHttpEntity(entity);
		InputStream stream = entity.getContent();
		
		// Will never have content length larger than max int value.
		
		byte [] contents = new byte [(int)entity.getContentLength()];
		
		if(stream instanceof ByteArrayInputStream){
			stream.read(contents);
		}else {
			entity.consumeContent();
			
			//stream.read(contents);
		}
		return contents;	
	}
	
	/**
	 * prints the response headers
	 * @param response
	 */
	private void printResponseRelatedMetaInfo(HttpResponse response){
		System.out.println(response.getClass().getName());		
		System.out.println(response.getEntity().getClass().getName());
		Header []  array = response.getAllHeaders();
		for(int i =0;i<array.length;i++)
			System.out.println(array[i].getName() + " : "+array[i].getValue());
		
	}
}

