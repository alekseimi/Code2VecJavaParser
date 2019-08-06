import Visitors.ForStmtVisitor;
import Visitors.IfStmtVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


/*
TODO:

 */

public class Entry {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Aleksej\\Documents\\ita-springboot-project\\src\\main\\java\\ita\\springboot\\application\\web\\MainController.java";
        File projectFile = new File(filePath);

        try {
            CompilationUnit compilationUnit = JavaParser.parse(projectFile);
            List<TypeDeclaration> typeDeclarationList = compilationUnit.getTypes();
            for (TypeDeclaration typeDeclaration : typeDeclarationList) {
                if (typeDeclaration instanceof ClassOrInterfaceDeclaration) {
                    if (!((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) {
                        List<Node> classNodeList = typeDeclaration.getChildrenNodes();
                        parseMethods(classNodeList, typeDeclaration.getName());
                    }
                }
            }
        } catch (ParseException | IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void parseMethods(List<Node> nodeList, String className) {
        for (Node node : nodeList) {
            if (node.getClass() == MethodDeclaration.class) {
                //ifstmt count
                VoidVisitor<List<Integer>> ifStmtVisitor = new IfStmtVisitor();
                List<Integer> ifStmtList = new ArrayList<>();
                ifStmtVisitor.visit((MethodDeclaration) node, ifStmtList);
                //forstmt count
                VoidVisitor<List<Integer>> forStmtVisitor = new ForStmtVisitor();
                List<Integer> forStmtList = new ArrayList<>();
                forStmtVisitor.visit((MethodDeclaration) node, forStmtList);
                //lineCount
                int slocCount = node.getEndLine() - node.getBeginLine();
                System.out.println(slocCount);
                //accessType
                int modifier = ((MethodDeclaration) node).getModifiers();
                String stringModifier = java.lang.reflect.Modifier.toString(modifier);
                System.out.println(stringModifier);

            }
        }
    }
}


/*
private void getParameterNames(MethodDeclaration methodDeclaration, boolean isInterface) {
  final EnumSet<Modifier> modifiers = methodDeclaration.getModifiers();
  if (isInterface || modifiers.contains(Modifier.PUBLIC)) {
    String methodName = methodDeclaration.getName().getIdentifier();
    List<Parameter> parameters = methodDeclaration.getParameters();
    names.className = this.className;
    List<List<ParameterName>> parameterNames =
        names.names.computeIfAbsent(methodName, k -> new ArrayList<>(4));

    final List<ParameterName> temp = new ArrayList<>();
    for (final Parameter parameter : parameters) {
      ParameterName parameterName = new ParameterName();
      String type = parameter.getType().toString();
      String name = parameter.getName().getIdentifier();
      if (name.contains("[]")) {
        type = type + "[]";
        name = name.replace("[]", "");
      }
      parameterName.type = type;
      parameterName.name = name;
      temp.add(parameterName);
    }
    parameterNames.add(temp);
  }
}
 */