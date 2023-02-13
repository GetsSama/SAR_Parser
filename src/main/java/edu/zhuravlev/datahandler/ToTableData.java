package edu.zhuravlev.datahandler;

import java.util.Map;

public interface ToTableData {
    Map<Integer, Double> getData();
    String[] getHeaders();
}
