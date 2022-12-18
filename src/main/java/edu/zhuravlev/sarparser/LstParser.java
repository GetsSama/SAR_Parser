package edu.zhuravlev.sarparser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public List<String> getRows() {
        if (rows==null) {
            try {
                readRows();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }

    private void isFileLST() {
        String fileName = inFile.getName();
        String appendix = fileName.substring(fileName.length()-3, fileName.length());
        if (!appendix.equals("lst"))
            throw new RuntimeException("This is not .lst file!");
    }

    private String getNumericParameterByIndex(int index) {
        String parameter = rows.get(index).strip().split("\t")[0];
        return parameter;
    }

    private String getNotPredictTypeByIndex(int index) {
        String type = rows.get(index).strip().split("\t")[1];
        return type;
    }

    private ValidationTypes getValidationTypesByString(String str) {
        var parameters = Arrays.stream(str.split("\t")).map(String::strip).collect(Collectors.toList());
        int numbers = Integer.parseInt(parameters.get(1));
        Double IAP = Double.parseDouble(parameters.get(2).replace(',', '.'));
        String type = parameters.get(3);
        var valType = new ValidationTypes(numbers, IAP, type);

        return valType;
    }

    public SARBaseInformation parseLSTFile() throws IOException{
        isFileLST();
        readRows();
        SARBaseInformationBuilder builder = new SARBaseInformationBuilder();

        builder.addMSARName(rows.get(2).strip());
        builder.addCreateDate(rows.get(3).strip());

        builder.addLevelMNADescriptors(Integer.parseInt(getNumericParameterByIndex(5)));
        builder.addSubstancesNumber(Integer.parseInt(getNumericParameterByIndex(6)));
        builder.addDescriptorsNumber(Integer.parseInt(getNumericParameterByIndex(7)));
        builder.addActivityType(Integer.parseInt(getNumericParameterByIndex(8)));
        builder.addSelectedActivityTypes(Integer.parseInt(getNumericParameterByIndex(9)));
        builder.addAverageIAP(Double.parseDouble(getNumericParameterByIndex(10).replace(',', '.')));

        int counter = 15;
        List<ValidationTypes> validationTypes = new ArrayList<>();
        do {
            validationTypes.add(getValidationTypesByString(rows.get(counter)));
            counter++;
        } while (!rows.get(counter).equals(""));
        builder.addAllValidationTypes(validationTypes);
        counter++;

        if (rows.get(counter).equals("Not Predictable Activity Types")) {
            counter += 3;
            List<String> noTypes = new ArrayList<>();
            for (int i=counter; i<rows.size(); i++)
                noTypes.add(getNotPredictTypeByIndex(i));
            builder.addNotPredictableTypes(noTypes);
        }
        return builder.buildSARInfo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var str : rows)
            sb.append(str.strip() + "\n");
        return sb.toString();
    }
}
