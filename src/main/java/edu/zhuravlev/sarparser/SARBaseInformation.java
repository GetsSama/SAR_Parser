package edu.zhuravlev.sarparser;

import java.util.List;

public class SARBaseInformation {
    private String MSARName;
    private String createDate;

    private int levelOfMNADescriptors;
    private int substancesNumber;
    private int descriptorsNumber;
    private int activityType;
    private int selectedActivityTypes;
    private Double averageIAP;

    private List<ValidationTypes> validationTable;

    private List<String> notPredictableActivityTypes;

    private int lengthOfPeptide;

    public int getLengthOfPeptide() {
        return lengthOfPeptide;
    }

    public void setLengthOfPeptide(int lengthOfPeptide) {
        this.lengthOfPeptide = lengthOfPeptide;
    }

    public String getMSARName() {
        return MSARName;
    }

    public void setMSARName(String MSARName) {
        this.MSARName = MSARName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getLevelOfMNADescriptors() {
        return levelOfMNADescriptors;
    }

    public void setLevelOfMNADescriptors(int levelOfMNADescriptors) {
        this.levelOfMNADescriptors = levelOfMNADescriptors;
    }

    public int getSubstancesNumber() {
        return substancesNumber;
    }

    public void setSubstancesNumber(int substancesNumber) {
        this.substancesNumber = substancesNumber;
    }

    public int getDescriptorsNumber() {
        return descriptorsNumber;
    }

    public void setDescriptorsNumber(int descriptorsNumber) {
        this.descriptorsNumber = descriptorsNumber;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getSelectedActivityTypes() {
        return selectedActivityTypes;
    }

    public void setSelectedActivityTypes(int selectedActivityTypes) {
        this.selectedActivityTypes = selectedActivityTypes;
    }

    public Double getAverageIAP() {
        return averageIAP;
    }

    public void setAverageIAP(Double averageIAP) {
        this.averageIAP = averageIAP;
    }

    public List<ValidationTypes> getValidationTable() {
        return validationTable;
    }

    public void setValidationTable(List<ValidationTypes> validationTable) {
        this.validationTable = validationTable;
    }

    public List<String> getNotPredictableActivityTypes() {
        return notPredictableActivityTypes;
    }

    public void setNotPredictableActivityTypes(List<String> notPredictableActivityTypes) {
        this.notPredictableActivityTypes = notPredictableActivityTypes;
    }

    @Override
    public String toString() {
        return "SARBaseInformation{" +
                "MSARName=" + MSARName + '\n' +
                ", createDate=" + createDate + '\n' +
                ", levelOfMNADescriptors=" + levelOfMNADescriptors + '\n' +
                ", substancesNumber=" + substancesNumber + '\n' +
                ", descriptorsNumber=" + descriptorsNumber + '\n' +
                ", activityType=" + activityType + '\n' +
                ", selectedActivityTypes=" + selectedActivityTypes + '\n' +
                ", averageIAP=" + averageIAP + '\n' +
                ", validationTable=" + validationTable + '\n' +
                ", notPredictableActivityTypes=" + notPredictableActivityTypes +
                '}';
    }
}

