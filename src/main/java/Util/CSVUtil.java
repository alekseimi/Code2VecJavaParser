package Util;

import Entity.MethodData;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {

    private static final char DEFAULT_SEPARATOR = ',';

    public static List<String> readCSV(String pathToCSV) {
        BufferedReader bufferedReader = null;
        String line;
        String splitBy = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(pathToCSV));
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("\"", "");
                String[] bufferedArray = line.split(splitBy);
                List<String> fileList = new ArrayList<>(Arrays.asList(bufferedArray));
                return fileList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void dumpToCsv(List<MethodData> methodDataList, String csvFilePath) {

        try {
            FileWriter writer = new FileWriter(csvFilePath);
            writeLine(writer, Arrays.asList("method_name", "project_name", "access_type", "size_in_sloc", "if_block_count", "for_block_count", "getter_or_setter"));
            for (MethodData methodData : methodDataList) {
                List<String> list = new ArrayList<>();
                list.add(methodData.getMethodName());
                list.add(methodData.getProjectName());
                list.add(methodData.getAccessType());
                list.add(Integer.toString(methodData.getSizeInSloc()));
                list.add(Integer.toString(methodData.getIfBlockCount()));
                list.add(Integer.toString(methodData.getForBlockCount()));
                list.add(methodData.getGetterOrSetter());
                writeLine(writer, list);
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    private static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    private static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    private static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }


}
