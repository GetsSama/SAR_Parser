package edu.zhuravlev.datahandler;

import java.util.Map;

public class FunctionalDependency<K, V> implements ToTableData{
    private final Map<Integer, Double> data;
    private final String[] headers;

    public FunctionalDependency(Map<Integer, Double> data, String... headers) {
        this.data = data;
        this.headers = headers;
    }

    public Map<Integer, Double> getData() {
        return data;
    }

    public String[] getHeaders() {
        return headers;
    }
}