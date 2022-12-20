package edu.zhuravlev.sarparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LstFilePattern {
    private static final String fileNameExpression = "(\\w+)_descript_(\\d{1,2})_pept_len_(\\d{1,2}).MSAR";
    private static final String splitExpression = "(\\w+_descript_|_pept_len_|.MSAR)";
    private static final Pattern fileNamePattern = Pattern.compile(fileNameExpression);
    private static final Pattern splitPattern = Pattern.compile(splitExpression);

    private LstFilePattern(){}

    public static boolean isLSTFileNameCorrect(String fileName) {
        Matcher fileNameMatcher = fileNamePattern.matcher(fileName);
        return fileNameMatcher.matches();
    }

    public static int getLengthPeptideByFileName(String fileName) {
        return Integer.parseInt(splitPattern.split(fileName)[2]);
    }
}
