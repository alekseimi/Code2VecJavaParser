package Visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class IfStmtVisitor extends VoidVisitorAdapter<Void> {

    public void visit (IfStmt ifstmt, Void arg){
        super.visit(ifstmt, arg);
        System.out.println(ifstmt);
    }
}
