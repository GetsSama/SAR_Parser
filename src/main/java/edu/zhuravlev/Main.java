package edu.zhuravlev;

import edu.zhuravlev.datahandler.DataGrouper;
import edu.zhuravlev.datahandler.ExcelCreator;
import edu.zhuravlev.datahandler.ToTableData;
import edu.zhuravlev.sarparser.LstParser;
import edu.zhuravlev.sarparser.SARBaseInformation;
import edu.zhuravlev.sarparser.ValidationTypes;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String abl = "ABL2";
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

//        ToTableData iapFromLength = DataGrouper.getIAPFromPeptLength(sarList);
//        DataGrouper.getFileNameWithMaxIAPFromLength(sarList);
//        ExcelCreator.createTable("C:\\Users\\Zh_Nikolay\\Desktop\\results1.xls", "iap_length", iapFromLength);

        var iapFromLevelEachLength = DataGrouper.getIAPFromDescriptorLevelFromPeptideLength(sarList);
        ExcelCreator.createTable("C:\\Users\\Zh_Nikolay\\Desktop\\results_ABL2_.xlsx", iapFromLevelEachLength);
    }

    private static SARBaseInformation getSARByPath(String path) throws IOException {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL resource = cl.getResource(path);
        Objects.requireNonNull(resource, "Resource with path \"" + path + "\" does not exist!");
        LstParser parser = new LstParser(resource.getPath());
        return parser.parseLSTFile();
    }
}