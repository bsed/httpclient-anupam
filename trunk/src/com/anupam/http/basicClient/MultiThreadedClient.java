package com.anupam.http.basicClient;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

import com.anupam.http.constantFiles.ClientConnectionConstants;

public class MultiThreadedClient {

	
 public static HttpClient createMultiThreadedClient(){
	HttpClient client = null; 
    BasicHttpParams httpParams = new BasicHttpParams();
    
    httpParams.setParameter("Authorization", "Basic YWRtaW46MTIzNDU2");
    httpParams.setParameter("Connection", "Keep-Alive");
    httpParams.setParameter("Cache-Control", "no-cache");
    
    ConnManagerParams.setMaxTotalConnections(httpParams, ClientConnectionConstants.MAX_CONNECTIONS);
    ConnManagerParams.setTimeout(httpParams, 10000l);
    
    HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setUserAgent(httpParams, "UFP-907w-UK");
    
    HttpConnectionParams.setConnectionTimeout(httpParams,10000);
    HttpConnectionParams.setSocketBufferSize(httpParams, 1024*20);
    //Disable Nagle's algorithm here
    HttpConnectionParams.setSoTimeout(httpParams, 10000);
    HttpConnectionParams.setTcpNoDelay(httpParams, true);
    
    SchemeRegistry schemeRegistry = new SchemeRegistry();

    schemeRegistry.register(new Scheme("http",
            PlainSocketFactory.getSocketFactory(), 80));

    ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
            httpParams, schemeRegistry);
    client  = new DefaultHttpClient(cm, httpParams);
    return client;
 }
}
