package Visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Entity.MethodData;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import Entity.Common;
import Entity.MethodContent;

import static com.github.javaparser.ast.Modifier.*;


@SuppressWarnings("StringEquality")
public class FunctionVisitor extends VoidVisitorAdapter<Object> {

    private ArrayList<MethodContent> m_Methods = new ArrayList<>();
    private ArrayList<MethodData> methodDataList = new ArrayList<>();

    private String functionProjectName;

    public FunctionVisitor(String functionProjectName) {
        this.functionProjectName = functionProjectName;
    }

    @Override
    public void visit(MethodDeclaration node, Object arg) {
        visitMethod(node, arg);

        super.visit(node, arg);
    }

    private void visitMethod(MethodDeclaration node, Object obj) {
        LeavesCollectorVisitor leavesCollectorVisitor = new LeavesCollectorVisitor();
        leavesCollectorVisitor.visitDepthFirst(node);

        String normalizedMethodName = Common.normalizeName(node.getName(), Common.BlankWord);
        ArrayList<String> splitNameParts = Common.splitToSubtokens(node.getName());
        String splitName = normalizedMethodName;
        if (splitNameParts.size() > 0) {
            splitName = splitNameParts.stream().collect(Collectors.joining(Common.internalSeparator));
        }


        if (node.getBody() != null) {

            String getterOrSetter = (isGetterOrSetter(splitName));

            VoidVisitor<List<Integer>> ifStmtVisitor = new IfStmtVisitor();
            List<Integer> ifStmtList = new ArrayList<>();
            ifStmtVisitor.visit(node, ifStmtList);

            VoidVisitor<List<Integer>> forStmtVisitor = new ForStmtVisitor();
            List<Integer> forStmtList = new ArrayList<>();
            forStmtVisitor.visit(node, forStmtList);


            String modifier = extractModifier(node.getModifiers());

            if (getMethodLength(node.getBody().toString()) != 0) {
                methodDataList.add(new MethodData(splitName, functionProjectName,
                        modifier, getMethodLength(node.getBody().toString()), ifStmtList.size(), forStmtList.size(), getterOrSetter));
            }
        }

    }

    private int getMethodLength(String code) {
        String cleanCode = code.replaceAll("\r\n", "\n").replaceAll("\t", " ");
        if (cleanCode.startsWith("{\n"))
            cleanCode = cleanCode.substring(3).trim();
        if (cleanCode.endsWith("\n}"))
            cleanCode = cleanCode.substring(0, cleanCode.length() - 2).trim();
        if (cleanCode.length() == 0) {
            return 0;
        }
        long codeLength = Arrays.asList(cleanCode.split("\n")).stream()
                .filter(line -> (line.trim() != "{" && line.trim() != "}" && line.trim() != ""))
                .filter(line -> !line.trim().startsWith("/") && !line.trim().startsWith("*")).count();
        return (int) codeLength;
    }


    private static String extractModifier(EnumSet enumSet) {
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

    private static String isGetterOrSetter(String methodName) {
        String regexPattern = ".+?(?=\\|)";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(methodName);
        if (matcher.find()) {
            if (matcher.group(0).equals(("get"))) {
                return "getter";
            } else if (matcher.group(0).equals("set")) {
                return "setter";
            } else {
                return "other";
            }
        }

        return "other";
    }


    public ArrayList<MethodData> getMethodDataList() {
        return methodDataList;
    }
}
