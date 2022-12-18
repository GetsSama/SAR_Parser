package edu.zhuravlev;

import edu.zhuravlev.sarparser.LstParser;
import edu.zhuravlev.sarparser.SARBaseInformation;
import edu.zhuravlev.sarparser.ValidationTypes;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String abl = "ABL1";
        String middle = "_descript_";
        String pept = "_pept_len_";
        String suffix = ".lst";

        List<SARBaseInformation> sarList = new ArrayList<>();
        for (int i=3; i<=15; i++)
            for (int j=1; j<=15; j++) {
                String path = abl + middle + i + pept + j + suffix;
                System.out.println(path);
                sarList.add(getSARByPath(path));}
        //System.out.println(sarList);

    }

    private static SARBaseInformation getSARByPath(String path) throws IOException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL resource = cl.getResource(path);
        LstParser parser = new LstParser(resource.getPath());
        SARBaseInformation sar = parser.parseLSTFile();
        return sar;
    }
}