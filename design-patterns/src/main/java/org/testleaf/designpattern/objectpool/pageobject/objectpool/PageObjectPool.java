package org.testleaf.designpattern.objectpool.pageobject.objectpool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageObjectPool {

    private final Map<Class<?>, Object> pool = new ConcurrentHashMap<>();

    private Object createInstance(Class<?> pageObjectClass) {
        try {
            System.out.println("Creating new instance of " + pageObjectClass.getName());
            return pageObjectClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error creating page object: " + pageObjectClass.getName(), e);
        }
    }

    public Object getPage(Class<?> pageObjectClass) {
        return pool.computeIfAbsent(pageObjectClass, this::createInstance);
    }

    public void clearPool() {
        pool.clear();
    }

}
