package com.bytetrend.sandbox.java.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * https://dzone.com/articles/memory-leak-andjava-code
 * Can you spot the memory leak?
 * <p>
 * Here, a memory leak occurs due to the internal map data structure. This class is to display
 * the employee value from the cache. Once those are displayed, there is no need to store
 * those elements in the cache.
 * <p>
 * We forgot to clear the cache, so although objects in cache are not required anymore
 * by the application, it can’t be GCed, as map holds a strong reference to them.
 * <p>
 * So when you're using your own Cache, don’t forget to clear them if items in the
 * cache are no longer required. Alternatively, you can initialize cache by WeakHashMap.
 * The beauty of WeakHashMap is, if keys are not referenced by any other objects, then
 * that entry will be eligible for GC.
 * <p>
 * There is lot to say about WeakHashMap, but I will discuss it in another article. Use
 * it with caution, if you want to reuse the values stored in the cache, it may be that
 * its key is not referenced by any other object, so the entry will be GCed and that
 * value magically disappears.
 *
 *
 */
public class Cache {
    private Map<String, String> map = new HashMap<String, String>();

    public void initCache() {
        map.put("Anil", "Work as Engineer");
        map.put("Shamik", "Work as Java Engineer");
        map.put("Ram", "Work as Doctor");
    }

    public Map<String, String> getCache() {
        return map;
    }

    public void forEachDisplay() {
        for (String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(key + " :: " + val);
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        cache.initCache();
        cache.forEachDisplay();
    }
}