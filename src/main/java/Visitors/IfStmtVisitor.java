package Visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class IfStmtVisitor extends VoidVisitorAdapter<List<Integer>> {

    public void visit(IfStmt ifstmt, List<Integer> ifCount) {
        super.visit(ifstmt, ifCount);
        ifCount.add(1);
    }
}
