package edu.zhuravlev.sarparser;

import java.util.List;
import java.util.Objects;

class SARBaseInformationBuilder {
    private final SARBaseInformation sarInfo;

    private int counter = 0;

    public SARBaseInformationBuilder() {sarInfo = new SARBaseInformation();}

    private void dataCheck(Object o) {
        Objects.requireNonNull(o);
        if (o instanceof String)
            if (((String) o).isBlank())
                throw new RuntimeException("String field can't be blank!");
    }

    private void SARInfoCorrectChecker() {
        if (counter != 9)
            throw new RuntimeException("Built object isn't correct!");
    }

    public SARBaseInformationBuilder addMSARName(String msarName) {
        dataCheck(msarName);
        sarInfo.setMSARName(msarName);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addCreateDate(String date) {
        dataCheck(date);
        sarInfo.setCreateDate(date);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addLevelMNADescriptors(int level) {
        dataCheck(level);
        sarInfo.setLevelOfMNADescriptors(level);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addSubstancesNumber(int number) {
        dataCheck(number);
        sarInfo.setSubstancesNumber(number);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addDescriptorsNumber(int number) {
        dataCheck(number);
        sarInfo.setDescriptorsNumber(number);
        counter++;
        return this;
    }
    public SARBaseInformationBuilder addActivityType(int type) {
        dataCheck(type);
        sarInfo.setActivityType(type);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addSelectedActivityTypes(int types) {
        dataCheck(types);
        sarInfo.setSelectedActivityTypes(types);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addAverageIAP(Double iap) {
        dataCheck(iap);
        sarInfo.setAverageIAP(iap);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addAllValidationTypes(List<ValidationTypes> types) {
        dataCheck(types);
        sarInfo.setValidationTable(types);
        counter++;
        return this;
    }

    public SARBaseInformationBuilder addNotPredictableTypes(List<String> types) {
        dataCheck(types);
        sarInfo.setNotPredictableActivityTypes(types);
        return this;
    }

    public SARBaseInformation buildSARInfo() {
        SARInfoCorrectChecker();
        return sarInfo;
    }
}
