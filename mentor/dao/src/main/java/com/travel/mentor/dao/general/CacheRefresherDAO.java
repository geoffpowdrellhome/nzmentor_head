package com.travel.mentor.dao.general;

import net.sf.ehcache.Cache;

import java.util.List;

public interface CacheRefresherDAO {

    List<String> findCacheNames();

    void refreshCache(String cacheName);

    void refreshAllCaches();

    Cache findCache(String cacheName);

    List<Cache> findAllCaches();

}
