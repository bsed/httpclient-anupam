package com.anupam.http.basicClient;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

import com.anupam.http.constantFiles.ClientConnectionConstants;

/**
 * 
 * @author  (Anupam Saini)
 *
 */
public class BasicClient {
	public static boolean flag = false;
	private static GetRequestThreads []  threads =null;
	
	/**
	 * 
	 */
	public void retriveContent() {
		
		//HttpClient client = new DefaultHttpClient();
		
		HttpClient client = MultiThreadedClient.createMultiThreadedClient();
		
		HttpContext context = this.defineBasicContext();
		
		 		
		((DefaultHttpClient)client).setHttpRequestRetryHandler(new ClientRequestRetryHandler());
		try {
			
			long time = System.currentTimeMillis();
			
			/*
			 * TODO: (Anupam) Move this code to separate file.
			 */
			
			if(!flag) {
				threads = new GetRequestThreads[ClientConnectionConstants.MAX_CONNECTIONS];
			  for (int i = 0; i < threads.length; i++) {
				  HttpGet httpGet = new HttpGet("http://netviewdemo.gicp.net:10532/tmpfs/auto.jpg");
		             threads[i] = new GetRequestThreads(client, context,httpGet, i + 1);
		             threads[i].start();
		         }  
			} else {
				for (int i = 0; i < threads.length; i++) {
					  threads[i].run();
			         }
			}
		    flag = true;
			System.out.println("time taken for reading stream"+ (System.currentTimeMillis()-time));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

	/**
	 * 
	 * @param object
	 * @param timestamp
	 * @return
	 */
	public static synchronized String saveToFileSystem(Object object,long timestamp) {
	    try {
	        // Create an image input stream on the image
	        ImageInputStream iis = ImageIO.createImageInputStream(object);

	        // Find all image readers that recognize the image format
	        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
	        if (!iter.hasNext()) {
	        	System.out.println("no readers found ");
	            return null;
	        }
	        
	        // Use the first reader
	        ImageReader reader = (ImageReader)iter.next();
	        reader.setInput(iis,true);
	        ImageReadParam param = reader.getDefaultReadParam();
	        BufferedImage bi = reader.read(0, param);
	        long timer = System.currentTimeMillis();
	        ImageWriter wr = ImageIO.getImageWriter(reader);	        
	        File f = new File("/home/anupam/Documents/auto"+timestamp+".jpg");
	        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
	        wr.setOutput(ios);
	        wr.write(bi);
	        bi.flush();
	        System.out.println(" file write "+(System.currentTimeMillis()-timer));
	        // Close stream
	        iis.close();
	        // Return the format name
	        return reader.getFormatName();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    // The image could not be read
	    return null;
	}
	
	/**
	 * 
	 * @return
	 */
	private HttpContext defineBasicContext(){
		HttpContext localcontext = new BasicHttpContext();
		return new SyncBasicHttpContext(localcontext);
	}
}
