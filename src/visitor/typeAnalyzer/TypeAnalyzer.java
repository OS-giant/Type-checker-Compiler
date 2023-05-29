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
import ast.node.statement.ReturnStmt;
import ast.node.statement.VarDecStmt;
import ast.node.statement.ArrayDecStmt;
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
import symbolTable.symbolTableItems.ArrayItem;
import symbolTable.symbolTableItems.ForLoopItem;
import symbolTable.symbolTableItems.FunctionItem;
import symbolTable.symbolTableItems.MainItem;
import symbolTable.symbolTableItems.VariableItem;
import visitor.Visitor;
import ast.node.declaration.ArgDeclaration;

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
        SymbolTable symbolTable = new SymbolTable(null, funcDeclaration.getName().getName());
        functionItem.setFunctionSymbolTable(symbolTable);
        SymbolTable.push(functionItem.getFunctionSymbolTable());

        for (var arg : funcDeclaration.getArgs()) {
            arg.accept(this);
        }
        for (var stmt : funcDeclaration.getStatements()) {
            stmt.accept(this);
        }
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        if (returnStmt.getExpression() != null)
            returnStmt.getExpression().accept(expressionTypeChecker);
        return null;
    }

    @Override
    public Void visit(ImplicationStmt implicationStmt) {

        SymbolTable.push(new SymbolTable(SymbolTable.top, "Implication_in_" + SymbolTable.top.name));

        Integer num_error = typeErrors.size();
        Type cond_Type = implicationStmt.getCondition().accept(expressionTypeChecker);
        if (num_error != typeErrors.size()) {
            if (!(cond_Type instanceof NoType))
                if (!(cond_Type instanceof BooleanType)) {
                    ConditionTypeNotBool exception = new ConditionTypeNotBool(implicationStmt.getLine());
                    typeErrors.add(exception);
                }
        }
        for (var s : implicationStmt.getStatements())
            s.accept(this);
        SymbolTable.pop();
        SymbolTable.pop();
        SymbolTable.push(SymbolTable.top);
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
    public Void visit(ArgDeclaration argDeclaration) {
        VarDecStmt varDecStmt = new VarDecStmt(argDeclaration.getIdentifier(), argDeclaration.getType());
        VariableItem variableItem = new VariableItem(varDecStmt);
        try {
            SymbolTable.top.put(variableItem);
        } catch (ItemAlreadyExistsException e) {
            // not this phase
        }

        return null;

    }

    @Override
    public Void visit(ForloopStmt forloopStmt) {
        Identifier array_name = forloopStmt.getArrayName();
        Type Array_item = forloopStmt.getArrayName().accept(expressionTypeChecker);
        array_name.accept(expressionTypeChecker);

        ForLoopItem forLoopItem = new ForLoopItem(forloopStmt);
        SymbolTable symbolTable_new = new SymbolTable(SymbolTable.top, "For_In_" + SymbolTable.top.name);
        forLoopItem.setFunctionSymbolTable(symbolTable_new);
        SymbolTable.push(forLoopItem.getForLoopSymbolTable());
        VariableItem variableItem = new VariableItem(forloopStmt.getIterator().getName(),
                Array_item);
        try {
            SymbolTable.top.put(variableItem);
        } catch (ItemAlreadyExistsException e) {

        }

        for (var stmt : forloopStmt.getStatements())
            stmt.accept(this);
        SymbolTable.pop();
        SymbolTable.pop();
        SymbolTable.push(SymbolTable.top);
        // تاپ درست وقتی به دست می اید که 2 بار پاپ شود
        // وگرنه تاپی که توسط پاپ ایجاد میشود ، عنصر حذف شده است
        return null;
    }

    @Override
    public Void visit(AssignStmt assignStmt) {
        Type tl = assignStmt.getLValue().accept(expressionTypeChecker);
        Type tr = assignStmt.getRValue().accept(expressionTypeChecker);

        if (!expressionTypeChecker.sameType(tl, tr) && !(tl instanceof NoType)
                && !(tr instanceof NoType)) {
            typeErrors.add(new UnsupportedOperandType(assignStmt.getLine(), BinaryOperator.assign.name()));
        }

        return null;
    }

    @Override
    public Void visit(ArrayDecStmt arrayDecStmt) {
        Type tl = arrayDecStmt.getType();
        ArrayItem arrayItem = new ArrayItem(arrayDecStmt.getIdentifier().getName(), tl);
        try {
            SymbolTable.top.put(arrayItem);
        } catch (ItemAlreadyExistsException e) {
            //
        }
        return null;
    }

    @Override
    public Void visit(VarDecStmt varDecStmt) {
        Type tl = varDecStmt.getType();
        if (varDecStmt.getInitialExpression() != null) {

            Type tr = varDecStmt.getInitialExpression().accept(expressionTypeChecker);
            if (!expressionTypeChecker.sameType(tl, tr) && !(tl instanceof NoType)
                    && !(tr instanceof NoType)) {
                UnsupportedOperandType exception = new UnsupportedOperandType(
                        varDecStmt.getLine(), BinaryOperator.assign.name());
                typeErrors.add(exception);
            }
        }
        VariableItem variableItem = new VariableItem(varDecStmt.getIdentifier().getName(),
                varDecStmt.getType());
        try {
            SymbolTable.top.put(variableItem);
        } catch (ItemAlreadyExistsException e) {
            //
        }
        return null;
    }

}
