package edu.zhuravlev.datahandler;

import java.util.Map;

public interface ToTableData<K, V> {
    Map<K,V> getData();
    String[] getHeaders();
}
