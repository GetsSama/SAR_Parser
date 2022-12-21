package edu.zhuravlev.datahandler;

import edu.zhuravlev.sarparser.SARBaseInformation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataGrouper {
    public static ToTableData getIAPFromPeptLength(List<SARBaseInformation> trainingResults) {
        Map<Integer, Double> iapAndLength = new HashMap<>();
        List<Integer> lengthList = trainingResults.stream()
                .map(SARBaseInformation::getLengthOfPeptide)
                .distinct()
                .collect(Collectors.toList());

        for (Integer len : lengthList) {
            SARBaseInformation trainWithMaxIAP = trainingResults.stream()
                    .filter(e -> e.getLengthOfPeptide()==len)
                    .max(Comparator.comparingDouble(SARBaseInformation::getAverageIAP))
                    .get();
            iapAndLength.put(len, trainWithMaxIAP.getAverageIAP());
        }

        return new FunctionalDependency(iapAndLength, "PEPTIDE_LENGTH", "MAX_AVERAGE_IAP");
    }

    public static ToTableData getIAPFromDescriptorLevel(List<SARBaseInformation> trainingResults) {
        Map<Integer, Double> iapAndLevel = new HashMap<>();
        List<Integer> levelsList = trainingResults.stream()
                .map(SARBaseInformation::getLevelOfMNADescriptors)
                .distinct()
                .collect(Collectors.toList());

        for (var level : levelsList) {
            SARBaseInformation trainWithMaxIAP = trainingResults.stream()
                    .filter(e -> e.getLevelOfMNADescriptors()==level)
                    .max(Comparator.comparingDouble(SARBaseInformation::getAverageIAP))
                    .get();
            iapAndLevel.put(level, trainWithMaxIAP.getAverageIAP());
        }

        return new FunctionalDependency(iapAndLevel, "MNA_LEVEL", "MAX_AVERAGE_IAP");
    }
}
