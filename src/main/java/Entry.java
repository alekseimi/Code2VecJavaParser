
import Visitors.IfStmtVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;


import java.io.File;
import java.io.IOException;
import java.util.List;


/*
TODO:

 */

public class Entry {

    public static void main(String[] args){
        String filePath = "C:\\Users\\Aleksej\\Documents\\ita-springboot-project\\src\\main\\java\\ita\\springboot\\application\\model\\nnet\\MNISTReader.java";
        File projectFile = new File(filePath);

        try{
            CompilationUnit compilationUnit = JavaParser.parse(projectFile);
            List<TypeDeclaration> typeDeclarationList = compilationUnit.getTypes();
            for(TypeDeclaration typeDeclaration: typeDeclarationList){
                if(typeDeclaration instanceof ClassOrInterfaceDeclaration){
                    if(!((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()){
                        List <Node> classNodeList = typeDeclaration.getChildrenNodes();
                        parseMethods(classNodeList, typeDeclaration.getName());
                    }
                }
            }
        } catch(ParseException | IOException e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void parseMethods(List<Node> nodeList, String className){
        for(Node node: nodeList){
            if(node.getClass() == MethodDeclaration.class || !((MethodDeclaration)node).getType().equals(className)){
                System.out.println(className);
                VoidVisitor visitor = new IfStmtVisitor();
                visitor.visit((MethodDeclaration)node, null);
            }
        }
    }

    public static void parseIf(List<Node> nodeList){

    }
}
