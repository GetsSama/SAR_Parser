package edu.zhuravlev;

import edu.zhuravlev.datahandler.DataGrouper;
import edu.zhuravlev.datahandler.ExcelCreator;
import edu.zhuravlev.datahandler.ToTableData;
import edu.zhuravlev.sarparser.LstParser;
import edu.zhuravlev.sarparser.SARBaseInformation;

import java.io.IOException;
import java.util.*;

public class Main {
    private static String pathToSoursDir = "C:\\Users\\Zh_Nikolay\\Desktop\\ABL1\\ABL1_OLMPASS";
    private static String isoform = "ABL1";
    private static String middle = "_descript_";
    private static String pept = "_pept_len_";
    private static String suffix = ".lst";
    public static void main(String[] args) throws IOException {
        var sarList = getListOfSARByFullPathToDir(pathToSoursDir);

        ToTableData iapFromLength = DataGrouper.getIAPFromPeptLength(sarList);
        DataGrouper.getFileNameWithMaxIAPFromLength(sarList);
        ExcelCreator.createTable("C:\\Users\\Zh_Nikolay\\Desktop\\" + isoform + "_results_IAP-PEP_LEN.xlsx", "iap_length", iapFromLength);

        var iapFromLevelEachLength = DataGrouper.getIAPFromDescriptorLevelFromPeptideLength(sarList);
        ExcelCreator.createTable("C:\\Users\\Zh_Nikolay\\Desktop\\" + isoform + "_results_IAP-MNA_EACH_LEN.xlsx", iapFromLevelEachLength);
    }

    private static List<SARBaseInformation> getListOfSARByFullPathToDir(String pathToSoursDir) {
        List<SARBaseInformation> sarList = new ArrayList<>(16 * 16);
        for (int i = 1; i <= 15; i++)
            for (int j = 1; j <= 15; j++) {
                String path = pathToSoursDir + "\\" + isoform + middle + i + pept + j + suffix;
                System.out.println(path);
                try {
                    sarList.add(new LstParser(path).parseLSTFile());
                } catch (IOException e) {
                    throw new RuntimeException("Resource with path \"" + path + "\" does not exist!", e);
                }
            }
        return sarList;
    }
}