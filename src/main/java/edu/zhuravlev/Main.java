package edu.zhuravlev;

import edu.zhuravlev.sarparser.LstParser;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL resource = cl.getResource("ABL2_descript_3_pept_len_3.lst");

        LstParser parser = new LstParser(resource.getPath());
        parser.readRows();
        System.out.println(parser);
    }
}