import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Entity.MethodContent;
import Entity.MethodData;
import FeaturesEntities.ProgramFeatures;
import Visitors.FunctionVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;


@SuppressWarnings("StringEquality")
public class FeatureExtractor {

    public ArrayList<MethodData> extractFeatures(String code, String projectName) throws IOException {
        CompilationUnit compilationUnit = parseFileWithRetries(code);
        FunctionVisitor functionVisitor = new FunctionVisitor(projectName);

        functionVisitor.visit(compilationUnit, null);

        ArrayList<MethodData> methods = functionVisitor.getMethodDataList();
        return methods;
    }

    private CompilationUnit parseFileWithRetries(String code) throws IOException {
        final String classPrefix = "public class Test {";
        final String classSuffix = "}";
        final String methodPrefix = "SomeUnknownReturnType f() {";
        final String methodSuffix = "return noSuchReturnValue; }";

        String originalContent = code;
        String content = originalContent;
        CompilationUnit parsed;
        try {
            parsed = JavaParser.parse(content);
        } catch (ParseProblemException e1) {
            try {
                content = classPrefix + methodPrefix + originalContent + methodSuffix + classSuffix;
                parsed = JavaParser.parse(content);
            } catch (ParseProblemException e2) {
                content = classPrefix + originalContent + classSuffix;
                parsed = JavaParser.parse(content);
            }
        }

        return parsed;
    }

}
