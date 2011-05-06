package net.flitech.faregate.service.impl;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.stereotype.Component;

import net.flitech.faregate.service.CacheRefresher;

/**
 * Wraps the Ehcache {@link CacheManager} for cache management.
 * @see CacheRefresherImplUnitTest
 */
@Component
public class CacheRefresherImpl extends FareGateObject implements CacheRefresher {

    private static final CacheComparator CACHE_COMPARATOR = new CacheComparator();
    @Resource
    private CacheManager cacheManager;

    @Override
    public List<String> findCacheNames() {
        return asList(cacheManager.getCacheNames());
    }

    @Override
    public void refreshCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.removeAll();
        }
    }

    @Override
    public Cache findCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    @Override
    public void refreshAllCaches() {
        List<String> caches = findCacheNames();
        for (String cache : caches) {
            refreshCache(cache);
        }
    }

    @Override
    public List<Cache> findAllCaches() {
        List<Cache> caches = new ArrayList<Cache>();
        for (String cacheName : findCacheNames()) {
            caches.add(findCache(cacheName));
        }
        Collections.sort(caches, CACHE_COMPARATOR);
        return caches;
    }

    public static class CacheComparator implements Comparator<Cache> {

        @Override
        public int compare(Cache cache1, Cache cache2) {
            return cache1.getName().compareTo(cache2.getName());
        }
    }
}

