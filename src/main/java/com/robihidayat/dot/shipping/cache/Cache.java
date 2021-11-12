package com.robihidayat.dot.shipping.cache;

public interface Cache {

  <T> T get(String key, Class<T> type);

  <T> void put(String key, T value);

}
