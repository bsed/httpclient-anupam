package com.anupam.http.basicClient;

import java.util.concurrent.atomic.AtomicLong;

public class MainClazz {

	public static AtomicLong longtimeKeeper= new AtomicLong(System.currentTimeMillis());
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BasicClient bClient = new BasicClient();
		while(true)
			bClient.retriveContent();
		
		//System.out.println(" Total time for operation "+(System.currentTimeMillis()-longtimeKeeper.get()));
		

	}

}
