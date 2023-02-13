package edu.zhuravlev.datahandler;

import edu.zhuravlev.sarparser.SARBaseInformation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                    .orElseThrow();
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
                    .orElseThrow();
            iapAndLevel.put(level, trainWithMaxIAP.getAverageIAP());
        }

        return new FunctionalDependency(iapAndLevel, "MNA_LEVEL", "MAX_AVERAGE_IAP");
    }

    public static void getFileNameWithMaxIAPFromLength(List<SARBaseInformation> trainingResults) {
        List<SARBaseInformation> maxIAPToLength = new ArrayList<>();
        List<Integer> lengthList = trainingResults.stream()
                .map(SARBaseInformation::getLengthOfPeptide)
                .distinct()
                .collect(Collectors.toList());

        for (Integer len : lengthList) {
            SARBaseInformation trainWithMaxIAP = trainingResults.stream()
                    .filter(e -> e.getLengthOfPeptide()==len)
                    .max(Comparator.comparingDouble(SARBaseInformation::getAverageIAP))
                    .get();
            maxIAPToLength.add(trainWithMaxIAP);
        }

        System.out.println(maxIAPToLength.stream().max(Comparator.comparingDouble(SARBaseInformation::getAverageIAP)).get());
    }

    public static Map<Integer, ToTableData> getIAPFromDescriptorLevelFromPeptideLength(List<SARBaseInformation> trainingResults) {
        Map<Integer, ToTableData> dataForEachLength = new HashMap<>(25);

         var allLengths =  trainingResults.stream()
                .map(SARBaseInformation::getLengthOfPeptide)
                .distinct()
                .collect(Collectors.toList());

         for(var len : allLengths) {
             var iapAndLevel = new HashMap<Integer, Double>();
             trainingResults.stream()
                     .filter(e -> e.getLengthOfPeptide()==len)
                     .forEach(e -> iapAndLevel.put(e.getLevelOfMNADescriptors(), e.getAverageIAP()));
             ToTableData dataForThisLength = new FunctionalDependency(iapAndLevel, "MNA_LEVEL", "AVERAGE_IAP");
             dataForEachLength.put(len, dataForThisLength);
         }

         return dataForEachLength;
    }
}
