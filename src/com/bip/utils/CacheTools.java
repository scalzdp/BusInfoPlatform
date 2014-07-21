package com.bip.utils;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.CacheKey;
import com.bip.dao.IBaseDAO;
import com.bip.vo.CacheKeyVO;


/**memcached manage ,manage the cache
 * */
@Service
public class CacheTools {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	
	private MemcachedTools memcached = MemcachedTools.getInstance();
	
	/**Store the CacheKey message 
	 * */
	public  void StoreCacheKeyToCached(CacheKeyVO vo){
		CacheKey key = saveCacheToDB(vo);
		Object obj = memcached.get(getCachedKeyKeys());
		List<CacheKey> cacheKeys = new ArrayList<CacheKey>();
		if(obj==null){
			cacheKeys = getAllCacheKeyJson();
		}else{
			cacheKeys = JsonHandler.convertJsonToCacheKeyObjects((String)obj);
		}
		memcached.replace(getCachedKeyKeys(),  JsonHandler.convertObjectToJson(cacheKeys.add(key)));
	}
	
	private List<CacheKey>  getAllCacheKeyJson(){
		return baseDAO.getAllSelf(new CacheKey(), "t_cachedkey");
	}
	
	private String getCachedKeyKeys(){
		return "realactivity_cachedkeys";
	}
	
	private CacheKey saveCacheToDB(CacheKeyVO vo){
		CacheKey key = new CacheKey();
		key.setDataMark(vo.getDataMark());
		key.setProperty1(vo.getProperty1());
		key.setTypeID(vo.getF1());
		baseDAO.save(key);
		return key;
	}
}
