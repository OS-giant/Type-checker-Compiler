package visitor.typeAnalyzer;

import java.util.*;

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
import ast.type.primitiveType.BooleanType;
import com.sun.jdi.VoidType;
import compileError.CompileError;
import compileError.Type.FunctionNotDeclared;
import compileError.Type.LeftSideNotLValue;
import compileError.Type.UnsupportedOperandType;
import compileError.Type.ConditionTypeNotBool;
import symbolTable.SymbolTable;
import symbolTable.itemException.ItemAlreadyExistsException;
import symbolTable.itemException.ItemNotFoundException;
import symbolTable.symbolTableItems.ForLoopItem;
import symbolTable.symbolTableItems.FunctionItem;
import symbolTable.symbolTableItems.MainItem;
import visitor.Visitor;

import java.util.ArrayList;

public class TypeAnalyzer extends Visitor<Void> {
    public ArrayList<CompileError> typeErrors = new ArrayList<>();
    private FuncDeclaration curFunction;
    private boolean hasReturn = false;
    Set<String> undefined = new HashSet<>();
    private boolean validForReturn = true;
    private boolean validForVarDec = true;
    ExpressionTypeChecker expressionTypeChecker = new ExpressionTypeChecker(typeErrors);

    @Override
    public Void visit(Program program) {
        for (var functionDec : program.getFuncs()) {
            functionDec.accept(this);
        }
        program.getMain().accept(this);

        return null;
    }

    @Override
    public Void visit(FuncDeclaration funcDeclaration) {
        FunctionItem functionItem = new FunctionItem(funcDeclaration);
        hasReturn = false;
        Type type = funcDeclaration.getType();

        try {
            functionItem = (FunctionItem) SymbolTable.root
                    .get(FunctionItem.STARTKEY + funcDeclaration.getName().getName());
            SymbolTable.push((functionItem.getFunctionSymbolTable()));

        } catch (ItemNotFoundException e) {
            // unreachable
        }
        SymbolTable new_symbol = new SymbolTable();
        SymbolTable.push(new_symbol);
        curFunction = funcDeclaration;
        for (var arg : funcDeclaration.getArgs()) {
            arg.accept(this);
        }
        funcDeclaration.getIdentifier().accept(this);
        funcDeclaration.getName().accept(this);
        ;

        for (var stmt : funcDeclaration.getStatements()) {
            stmt.accept(this);
        }

        SymbolTable.pop();

        functionItem.setFunctionSymbolTable(new_symbol);

        return null;
    }

    @Override
    public Void visit(MainDeclaration mainDeclaration) {
        FuncDeclaration functionDeclaration = new FuncDeclaration(
                new Identifier("main"),
                new NoType(),
                new ArrayList<>(),
                mainDeclaration.getMainStatements());
        FunctionItem functionItem = new FunctionItem(functionDeclaration);

        try {
            SymbolTable.root.put(functionItem);
        } catch (ItemAlreadyExistsException e) {
        }

        var mainItem = new MainItem(mainDeclaration);
        var mainSymbolTable = new SymbolTable(SymbolTable.top, "main");
        mainItem.setMainItemSymbolTable(mainSymbolTable);

        SymbolTable.push(mainItem.getMainItemSymbolTable());

        for (var stmt : mainDeclaration.getMainStatements()) {
            stmt.accept(this);
        }
        validForReturn = true;
        SymbolTable.pop();
        functionItem.setFunctionDeclaration(functionDeclaration);
        return null;
    }

    @Override
    public Void visit(ForloopStmt forloopStmt) {
        Identifier array_name = forloopStmt.getArrayName();
        array_name.accept(expressionTypeChecker);

        try {
            ForLoopItem forLoopItem = (ForLoopItem) SymbolTable.root
                    .get(FunctionItem.STARTKEY + forloopStmt.toString());
            SymbolTable.push((forLoopItem.getForLoopSymbolTable()));
        } catch (ItemNotFoundException e) {

        }
        for (var stmt : forloopStmt.getStatements())
            stmt.accept(this);
        SymbolTable.pop();

        return null;
    }

    @Override
    public Void visit(AssignStmt assignStmt) {
        Type tl = assignStmt.getLValue().accept(expressionTypeChecker);
        Type tr = assignStmt.getRValue().accept(expressionTypeChecker);
        Expression lexpr = assignStmt.getLValue();
        Expression rexpr = assignStmt.getRValue();
        if (!expressionTypeChecker.isLvalue(lexpr)) {
            LeftSideNotLValue exception = new LeftSideNotLValue(assignStmt.getLine());
            typeErrors.add(exception);
        }
        if (!expressionTypeChecker.sameType(tl, tr)) {
            UnsupportedOperandType exception = new UnsupportedOperandType(
                    assignStmt.getLine(), BinaryOperator.gt.name());
            typeErrors.add(exception);
        }

        return null;
    }

    @Override
    public Void visit(VarDecStmt varDecStmt) {
        Type tl = varDecStmt.getType();
        if (varDecStmt.getInitialExpression() != null) {

            Type tr = varDecStmt.getInitialExpression().accept(expressionTypeChecker);
            if (!expressionTypeChecker.sameType(tl, tr)) {
                UnsupportedOperandType exception = new UnsupportedOperandType(
                        varDecStmt.getLine(), BinaryOperator.gt.name());
                typeErrors.add(exception);
            }
        }
        return null;
    }

    @Override
    public Void visit(FunctionCall functionCall) {
        expressionTypeChecker.setIsInFunctionCallStmt(true);
        try {
            SymbolTable.root.get(FunctionItem.STARTKEY + functionCall.getUFuncName().getName());
        } catch (ItemNotFoundException e) {

        }

        expressionTypeChecker.setIsInFunctionCallStmt(false);

        return null;
    }

}
