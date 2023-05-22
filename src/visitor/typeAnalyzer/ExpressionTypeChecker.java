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

import java.util.ArrayList;

public class ExpressionTypeChecker extends Visitor<Type> {

    private boolean isInFunctionCallStmt;
    private boolean seenNoneLvalue = false;

    public void setIsInFunctionCallStmt(boolean _isInFunctionCallStmt) {
        this.isInFunctionCallStmt = _isInFunctionCallStmt;
    }

    public ArrayList<CompileError> typeErrors;

    public ExpressionTypeChecker(ArrayList<CompileError> typeErrors){
        this.typeErrors = typeErrors;
    }

    public boolean sameType(Type el1, Type el2){
        //TODO check the two type are same or not

        if(el1 instanceof NoType || el2 instanceof NoType)
            return true;
        
        if(el1 instanceof BooleanType && el2 instanceof BooleanType)
            return true;
        
        if(el1 instanceof IntType && el2 instanceof IntType)
            return true;

        if(el1 instanceof FloatType && el2 instanceof FloatType)
            return true;

        return false;
    }

    public boolean isLvalue(Expression expr){
        //TODO check the expr are lvalue or not
        boolean previousSeenNoneLvalue = this.seenNoneLvalue;
        boolean previousIsCatchErrorsActive = Node.isCatchErrorsActive;
        this.seenNoneLvalue = false;
        Node.isCatchErrorsActive = false;
        expression.accept(this);
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

        //TODO check errors and return the type
        
        if(operator.equals(UnaryOperator.not)){
            if (expType instanceof BooleanType)
                return new BooleanType();

            if( !(expType instanceof NoType)){
                UnsupportedOperandType exception = new UnsupportedOperandType(expType.getLine(), operator.name());
                expType.addError(exception);
            }
        } else {
            if (expType instanceof IntType)
                return new IntType();

            if(!(expType instanceof NoType)) {
                UnsupportedOperandType exception = new UnsupportedOperandType(expType.getLine(), operator.name())
                expType.addError(exception);
            }
        }

        if(expType instanceof IntType) {
            return new IntType();
        }

        else {
            typeErrors.add(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
            return new NoType();
        }
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Type tl = binaryExpression.getLeft().accept(this);
        Type tr = binaryExpression.getRight().accept(this);
        BinaryOperator operator =  binaryExpression.getBinaryOperator();

    }

    @Override
    public Type visit(Identifier identifier) {}

    @Override
    public Type visit(FunctionCall functionCall) {}

    @Override
    public Type visit(IntValue value) {
        return new IntType();
    }

    @Override
    public Type visit(FloatType value) {
        return new FloatType();
    }

    @Override
    public Type visit(BooleanType value) {
        return new BooleanType();
    }
}
