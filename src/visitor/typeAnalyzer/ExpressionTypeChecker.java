package visitor.typeAnalyzer;

import ast.node.expression.*;
import ast.node.expression.operators.BinaryOperator;
import ast.node.expression.operators.UnaryOperator;
import ast.node.expression.values.IntValue;
import ast.type.NoType;
import ast.type.Type;
import ast.type.primitiveType.BooleanType;
import ast.type.primitiveType.FloatType;
import ast.type.primitiveType.IntType;
import compileError.CompileError;
import compileError.Type.FunctionNotDeclared;
import compileError.Type.UnsupportedOperandType;
import compileError.Type.VarNotDeclared;
import symbolTable.SymbolTable;
import symbolTable.itemException.ItemNotFoundException;
import symbolTable.symbolTableItems.FunctionItem;
import symbolTable.symbolTableItems.VariableItem;
import visitor.Visitor;
import ast.node.Node;
import java.util.ArrayList;

public class ExpressionTypeChecker extends Visitor<Type> {

    private boolean isInFunctionCallStmt;
    private boolean seenNoneLvalue = false;

    public void setIsInFunctionCallStmt(boolean _isInFunctionCallStmt) {
        this.isInFunctionCallStmt = _isInFunctionCallStmt;
    }

    public ArrayList<CompileError> typeErrors;

    public ExpressionTypeChecker(ArrayList<CompileError> typeErrors) {
        this.typeErrors = typeErrors;
    }

    public boolean sameType(Type el1, Type el2) {
        // TODO check the two type are same or not
        if (el1 instanceof NoType || el2 instanceof NoType)
            return true;

        if (el1 instanceof BooleanType && el2 instanceof BooleanType)
            return true;

        if (el1 instanceof IntType && el2 instanceof IntType)
            return true;

        if (el1 instanceof FloatType && el2 instanceof FloatType)
            return true;

        return false;
    }

    public boolean isLvalue(Expression expr) {
        // TODO check the expr are lvalue or not
        boolean previousSeenNoneLvalue = this.seenNoneLvalue;
        boolean previousIsCatchErrorsActive = Node.isCatchErrorsActive;
        this.seenNoneLvalue = false;
        Node.isCatchErrorsActive = false;
        expr.accept(this);
        boolean isLvalue = !this.seenNoneLvalue;
        Node.isCatchErrorsActive = previousIsCatchErrorsActive;
        this.seenNoneLvalue = previousSeenNoneLvalue;
        return isLvalue;
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {

        Expression uExpr = unaryExpression.getOperand();
        Type expType = uExpr.accept(this);
        UnaryOperator operator = unaryExpression.getUnaryOperator();

        // TODO check errors and return the type
        if (operator.equals(UnaryOperator.minus)) {
            if (expType instanceof IntType)
                return expType;

            if (expType instanceof NoType)
                return new NoType();
            else {
                UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
                typeErrors.add(exception);
                return new NoType();
            }

        } else if (operator.equals(UnaryOperator.plus)) {
            if (expType instanceof IntType)
                return expType;

            if (expType instanceof NoType)
                return new NoType();
            else {
                UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
                typeErrors.add(exception);
                return new NoType();
            }
        }

        else if (operator.equals(UnaryOperator.not)) {
            if (expType instanceof BooleanType)
                return expType;

            if (!(expType instanceof NoType)) {
                return new NoType();
            } else {
                UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
                typeErrors.add(exception);
            }

        } else {
            boolean isOperandLValue = this.isLvalue(unaryExpression.getOperand());
            if (expType instanceof NoType)
                return new NoType();

            if (expType instanceof IntType) {
                if (isOperandLValue)
                    return expType;

                return new NoType();
            }

            UnsupportedOperandType exception = new UnsupportedOperandType(uExpr.getLine(), operator.name());
            typeErrors.add(exception);
            return new NoType();
        }
        return new NoType();
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Type tl = binaryExpression.getLeft().accept(this);
        Type tr = binaryExpression.getRight().accept(this);
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        return new NoType();
    }

    @Override
    public Type visit(Identifier identifier) {
        try {
            FunctionItem funcSym = (FunctionItem) SymbolTable.root.getItem(FunctionItem.STARTKEY +
                    identifier.getName());
            ArrayList<Type> args = funcSym.getArgs();
            if (args.size() == 1)
                if (args.get(0) instanceof void)
                    args = new ArrayList<>();
            return funcSym.getType();

        } catch (ItemNotFoundException exception2) {
            try {
                SymbolTable.top.getItem(VariableItem.STARTKEY + identifier.getName());
                VariableItem varSym = (VariableItem) SymbolTable.top.getItem(VariableItem.STARTKEY +
                        identifier.getName());
                return varSym.getType();
            } catch (ItemNotFoundException exception3) {
                VarNotDeclared exception = new VarNotDeclared(identifier.getLine(), identifier.getName());
                typeErrors.add(exception);
                return new NoType();
            }
        }
    }

    @Override
    public Type visit(FunctionCall functionCall) {
        this.seenNoneLvalue = true;
        Type returnType = functionCall.getUFuncName().accept(this);

        if (!(returnType instanceof NoType)) {
            FunctionNotDeclared funNotDec = new FunctionNotDeclared(functionCall.getLine(), functionCall.getName());
            typeErrors.add(funNotDec);
        } else {

        }
        return new NoType();
    }

    @Override
    public Type visit(IntValue value) {
        this.seenNoneLvalue = true;
        return new IntType();
    }

    @Override
    public Type visit(FloatType value) {
        this.seenNoneLvalue = true;
        return new FloatType();
    }

    @Override
    public Type visit(BooleanType value) {
        this.seenNoneLvalue = true;
        return new BooleanType();
    }
}