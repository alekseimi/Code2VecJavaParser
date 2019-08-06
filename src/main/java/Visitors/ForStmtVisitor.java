package Visitors;

import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ForStmtVisitor extends VoidVisitorAdapter<List<Integer>> {

    public void visit (ForStmt forstmt, List<Integer> forCount) {
        super.visit(forstmt, forCount);
        forCount.add(1);
    }
}
