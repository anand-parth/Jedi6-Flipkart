package com.flipkart.constant;

import java.util.Map;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to operate UPI fee payment services
 */
final public class CustomEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    /**
     * Method to register courses with given choices for given student
     *
     * @param key
     * @param value
     */
    public CustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * getKey funtion
     * @return
     */
    @Override
    public K getKey() {
        return key;
    }

    /**
     * getValue funtion
     * @return
     */
    @Override
    public V getValue() {
        return value;
    }

    /**
     * setValue funtion
     * @return
     */
    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}