import java.io.IOException;

import ast.node.Program;
import compileError.CompileError;
import main.grammar.LogicPLLexer;
import main.grammar.LogicPLParser;
import visitor.nameAnalyzer.NameAnalyzer;
import visitor.astPrinter.ASTPrinter;
import org.antlr.v4.runtime.*;
import visitor.typeAnalyzer.TypeAnalyzer;

public class Main {
    public static void main(String[] args) throws java.io.IOException {
        String name = "C:\\Users\\ASUS\\Desktop\\debug_comp\\Type-checker-Compiler\\src\\sample.txt";
        CharStream reader = CharStreams.fromFileName(name);
        LogicPLLexer lexer = new LogicPLLexer(reader);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogicPLParser parser = new LogicPLParser(tokens);
        Program program = parser.program().p;

        NameAnalyzer nameAnalyzer = new NameAnalyzer();
        nameAnalyzer.visit(program);

        TypeAnalyzer typeAnalyzer = new TypeAnalyzer();
        typeAnalyzer.visit(program);
        if (typeAnalyzer.typeErrors.size() > 0) {
            for (CompileError compileError : typeAnalyzer.typeErrors)
                System.out.println(compileError.getMessage());
            return;
        }

        System.out.println("Compilation was Successful!!");
    }

}