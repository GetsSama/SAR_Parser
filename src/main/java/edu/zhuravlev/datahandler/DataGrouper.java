package edu.zhuravlev.datahandler;

import edu.zhuravlev.sarparser.SARBaseInformation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataGrouper {
    public static Map<Integer, Double> getIAPFromPeptLength(List<SARBaseInformation> trainingResults) {
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

        return iapAndLength;
    }
}
