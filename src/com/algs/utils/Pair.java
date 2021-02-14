package com.algs.utils;

public class Pair{
    Integer key;
    Object value;
    public Pair(int key, Object value){
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{key=%s,value=%s}".formatted(key, value);
    }
}
