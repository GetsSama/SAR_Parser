package edu.zhuravlev.datahandler;

import java.util.Map;

public class FunctionalDependency<K, V> implements ToTableData<K, V>{
    private final Map<K, V> data;
    private final String[] headers;

    public FunctionalDependency(Map<K, V> data, String... headers) {
        this.data = data;
        this.headers = headers;
    }

    public Map<K, V> getData() {
        return data;
    }

    public String[] getHeaders() {
        return headers;
    }
}