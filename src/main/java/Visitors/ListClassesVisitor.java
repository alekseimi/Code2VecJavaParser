package Visitors;

import com.google.common.base.Strings;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import util.DirExplorer;

import java.io.File;
import java.io.IOException;

public class ListClassesVisitor {

    public static void listClasses(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new VoidVisitorAdapter<Object>() {
                    @Override
                    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                        super.visit(n, arg);
                        System.out.println(" * " + n.getName());
                    }
                }.visit(JavaParser.parse(file), null);
                System.out.println(); // empty line
            } catch (IOException |ParseException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }
}
