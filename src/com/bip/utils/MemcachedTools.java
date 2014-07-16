package com.bip.utils;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

@Service
public class MemcachedTools {
	
	protected static MemCachedClient mcc = new MemCachedClient();
	
	protected static MemcachedTools memCached = new MemcachedTools();
	 
	static{
		//server list and its weight
		String[] servers={"127.0.0.1:11211"};
		Integer[] weights={3};
		
		//get the socket  connection pool instance
		SockIOPool pool =SockIOPool.getInstance();
		
		//set the server parameters
		pool.setServers(servers);
		pool.setWeights(weights);
		
		//set init number of connections and the min and the max number of connections and the max handle time 
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000*60*60*6);
		
		//set the main sleep time
		pool.setMaintSleep(30);
		
		//set TCP parameter,connection timeout
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);
		
		//init connection pool
		pool.initialize();
		
		//Compression setting, more than specified size, the data will be compressed
		//mcc.setCompressEnable(true);
		//mcc.setCompressThreshold(64*1024);
	}
	
	//protected construct ,not allow be instanced
	protected MemcachedTools(){
		
	}
	
	public static MemcachedTools getInstance(){
		return memCached;
	}
	
	/* add a object to memcached
	 * @param key
	 * @param value
	 * @return
	 * */
	public boolean add(String key,Object value){
		return mcc.add(key, value);
	}
	
	public boolean add(String key,Object value,Date expiry){
		return mcc.add(key, value, expiry);
	}
	
	public boolean replace(String key,Object value){
		return mcc.replace(key,value);
	}
	
	public boolean replace(String key,Object value,Date expiry){
		return mcc.replace(key, value, expiry);
	}
	
	public Object get(String key){
		return mcc.get(key);
	}

	public Object delete(String key ){
		return mcc.delete(key);
	}
}

