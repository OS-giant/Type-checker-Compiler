package visitor.typeAnalyzer;

import ast.node.Program;
import ast.node.declaration.Declaration;
import ast.node.declaration.FuncDeclaration;
import ast.node.declaration.MainDeclaration;
import ast.node.expression.Expression;
import ast.node.expression.FunctionCall;
import ast.node.expression.Identifier;
import ast.node.expression.operators.BinaryOperator;
import ast.node.statement.AssignStmt;
import ast.node.statement.ForloopStmt;
import ast.node.statement.ImplicationStmt;
import ast.node.statement.VarDecStmt;
import ast.type.NoType;
import ast.type.Type;
import compileError.CompileError;
import compileError.Type.FunctionNotDeclared;
import compileError.Type.LeftSideNotLValue;
import compileError.Type.UnsupportedOperandType;
import compileError.Type.ConditionTypeNotBool;
import symbolTable.SymbolTable;
import symbolTable.itemException.ItemNotFoundException;
import symbolTable.symbolTableItems.ForLoopItem;
import symbolTable.symbolTableItems.FunctionItem;
import symbolTable.symbolTableItems.MainItem;
import visitor.Visitor;

import java.util.ArrayList;

public class TypeAnalyzer extends Visitor<Void> {
    public ArrayList<CompileError> typeErrors = new ArrayList<>();
    ExpressionTypeChecker expressionTypeChecker = new ExpressionTypeChecker(typeErrors);

    @Override
    public Void visit(Program program) {
        for(var functionDec : program.getFuncs()) {
            functionDec.accept(this);
        }

        program.getMain().accept(this);

        return null;
    }

    @Override
    public Void visit(FuncDeclaration funcDeclaration) {
        try {
            FunctionItem functionItem = (FunctionItem)  SymbolTable.root.get(FunctionItem.STARTKEY + funcDeclaration.getName().getName());
            SymbolTable.push((functionItem.getFunctionSymbolTable()));
        } catch (ItemNotFoundException e) {
            //unreachable
        }

        for(var stmt : funcDeclaration.getStatements()) {
            stmt.accept(this);
        }

        SymbolTable.pop();

        return null;
    }

    @Override
    public Void visit(MainDeclaration mainDeclaration) {
        var mainItem = new MainItem(mainDeclaration);
        var mainSymbolTable = new SymbolTable(SymbolTable.top, "main");
        mainItem.setMainItemSymbolTable(mainSymbolTable);

        SymbolTable.push(mainItem.getMainItemSymbolTable());

        for (var stmt : mainDeclaration.getMainStatements()) {
            stmt.accept(this);
        }


        return null;
    }
    @Override
    public Void visit(ForloopStmt forloopStmt) {
        try {
            ForLoopItem forLoopItem = (ForLoopItem)  SymbolTable.root.get(FunctionItem.STARTKEY + forloopStmt.toString());
            SymbolTable.push((forLoopItem.getForLoopSymbolTable()));
        } catch (ItemNotFoundException e) {

        }

        SymbolTable.pop();

        return null;
    }

    @Override
    public Void visit(AssignStmt assignStmt) {
        Type tl = assignStmt.getLValue().accept(expressionTypeChecker);
        Type tr = assignStmt.getRValue().accept(expressionTypeChecker);


        return null;
    }

    @Override
    public Void visit(FunctionCall functionCall) {
        try {
                SymbolTable.root.get(FunctionItem.STARTKEY + functionCall.getUFuncName().getName());
        }
        catch (ItemNotFoundException e) {

        }


        return null;
    }

}
