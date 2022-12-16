package edu.zhuravlev.sarparser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LstParser {
    private List<String> rows;
    private File inFile;

    public LstParser(String path){
        this.inFile = new File(path);
    }

    public void readRows() throws IOException{
        Scanner scn = new Scanner(inFile);
        rows = new ArrayList<>();

        while (scn.hasNext())
            rows.add(scn.nextLine());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var str : rows)
            sb.append(str + "\n");
        return sb.toString();
    }
}