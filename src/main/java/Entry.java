import Entity.Common;

import Entity.MethodData;
import Util.CSVUtil;
import com.github.javaparser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Entry {

    static List<MethodData> methodDataList = new ArrayList<>();

    public static void main(String[] args) {

        /* --MAIN CONTENT
        String testFilePath = "E:\\Program Files\\commons-lang-master\\src\\main\\java\\org\\apache\\commons\\lang3\\tuple\\Pair.java";
        parseFile(testFilePath, "project");
        System.out.println(methodDataList);


        String csvTestPath = "E:\\Šola\\code2vec\\apache_commons.csv";
        List<String> listOfiles = CSVUtil.readCSV(csvTestPath);
        for (String file : listOfiles) {
            parseFile(file, "apache_commons");
        }

        CSVUtil.dumpToCsv(methodDataList, "test.csv");
        System.out.println(methodDataList);

         */


        String csvPath = "E:\\Šola\\code2vec\\apache_commons.csv";
        List<String> listOfProjectFiles = CSVUtil.readCSV(csvPath);


        String testFilePath = "E:\\Program Files\\commons-lang-master\\src\\main\\java\\org\\apache\\commons\\lang3\\tuple\\Pair.java";
        String projectName = "apache_commons";

        try {
            for (String file : listOfProjectFiles) {
                Path filePath = Paths.get(file);
                String code = extractSingleFile(filePath);
                FeatureExtractor featureExtractor = new FeatureExtractor();
                List<MethodData> classList = featureExtractor.extractFeatures(code, projectName);
                System.out.println("File: " + file + " size:" + classList.size());
                methodDataList.addAll(classList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(methodDataList.size());
        CSVUtil.dumpToCsv(methodDataList, "csv_dump.csv");

    }

    public static String extractSingleFile(Path filePath) {
        String code;
        try {
            code = new String(Files.readAllBytes(filePath));
            return code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }









    /*



    public static void parseFile(String filePath, String projectName) {
        try {
            File projectFile = new File(filePath);
            CompilationUnit compilationUnit = JavaParser.parse(projectFile);
            for (TypeDeclaration typeDeclaration : compilationUnit.getTypes()) {
                if (typeDeclaration instanceof ClassOrInterfaceDeclaration) {
                    if (!((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) {
                        List<Node> classNodeList = typeDeclaration.getChildNodes();
                        parseMethods(classNodeList, typeDeclaration.getName(), projectFile.getName(), projectName);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void parseMethods(List<Node> nodeList, SimpleName className, String fileName, String projectName) {
        for (Node node : nodeList) {
            if (node.getClass() == MethodDeclaration.class) {
                if (!((MethodDeclaration) node).getType().equals(className)) {
                    MethodData methodData = new MethodData();

                    //ifstmt count
                    VoidVisitor<List<Integer>> ifStmtVisitor = new IfStmtVisitor();
                    List<Integer> ifStmtList = new ArrayList<>();
                    ifStmtVisitor.visit((MethodDeclaration) node, ifStmtList);
                    methodData.setIfBlockCount(ifStmtList.size());

                    //forstmt count
                    VoidVisitor<List<Integer>> forStmtVisitor = new ForStmtVisitor();
                    List<Integer> forStmtList = new ArrayList<>();
                    forStmtVisitor.visit((MethodDeclaration) node, forStmtList);
                    methodData.setForBlockCount(forStmtList.size());

                    //lineCount
                    int slocCount = node.getEnd().get().line - node.getBegin().get().line;
                    if (slocCount == 0) {
                        continue;
                    }
                    methodData.setSizeInSloc(slocCount);

                    //accessType
                    EnumSet enumSetList = ((MethodDeclaration) node).getModifiers();
                    String modifier = extractModifier(enumSetList);
                    methodData.setAccessType(modifier);
                    //fileName
                    methodData.setFileName(fileName);

                    //projectName
                    methodData.setProjectName(projectName);

                    //methodName
                    methodData.setMethodName(((MethodDeclaration) node).getName().asString());
                    methodDataList.add(methodData);
                    //}
                } else if (node.getClass() == ClassOrInterfaceDeclaration.class) {
                    if (!((ClassOrInterfaceDeclaration) node).isInterface()) {
                        parseMethods(node.getChildNodes(), className, fileName, projectName);
                    }
                }
            }
        }
    }

    public static String extractModifier(EnumSet enumSet) {
        String returnValue = "null";

        if (enumSet.contains(PRIVATE)) {
            returnValue = "private";
        } else if (enumSet.contains(PUBLIC)) {
            returnValue = "public";
        } else if (enumSet.contains(PROTECTED)) {
            returnValue = "protected";
        }
        return returnValue;
    }

     */
}

