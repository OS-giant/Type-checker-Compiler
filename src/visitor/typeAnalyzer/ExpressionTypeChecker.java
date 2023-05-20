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
    public ArrayList<CompileError> typeErrors;
    public ExpressionTypeChecker(ArrayList<CompileError> typeErrors){
        this.typeErrors = typeErrors;
    }

    public boolean sameType(Type el1, Type el2){
        //TODO check the two type are same or not

        return false;
    }

    public boolean isLvalue(Expression expr){
        //TODO check the expr are lvalue or not

        return false;
    }


    @Override
    public Type visit(UnaryExpression unaryExpression) {

        Expression uExpr = unaryExpression.getOperand();
        Type expType = uExpr.accept(this);
        UnaryOperator operator = unaryExpression.getUnaryOperator();

        //TODO check errors and return the type
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
