
package visitor.typeAnalyzer;

import ast.node.expression.*;
import ast.node.expression.operators.BinaryOperator;
import ast.node.expression.operators.UnaryOperator;
import ast.node.expression.values.BooleanValue;
import ast.node.expression.values.FloatValue;
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
import symbolTable.symbolTableItems.ArrayItem;
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

    public boolean is_boolean_operator(BinaryOperator op) {
        if ((op.ordinal() == BinaryOperator.eq.ordinal()) ||
                (op.ordinal() == BinaryOperator.neq.ordinal()) ||
                (op.ordinal() == BinaryOperator.or.ordinal()) ||
                (op.ordinal() == BinaryOperator.and.ordinal()) ||
                (op.ordinal() == BinaryOperator.gt.ordinal()) ||
                (op.ordinal() == BinaryOperator.gte.ordinal()) ||
                (op.ordinal() == BinaryOperator.lt.ordinal()) ||
                (op.ordinal() == BinaryOperator.lte.ordinal()) ||
                (op.ordinal() == BinaryOperator.gt.ordinal()) ||
                (op.ordinal() == BinaryOperator.gt.ordinal()))
            return true;
        return false;

    }

    public boolean sameType(Type el1, Type el2) {
        if (el1 instanceof NoType && el2 instanceof NoType)
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
    public Type visit(QueryExpression queryExpression) {

        if (queryExpression.getVar() == null)
            return new NoType();
        else
            queryExpression.getVar().accept(this);
        return new BooleanType();
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {

        Expression uExpr = unaryExpression.getOperand();
        Type expType = uExpr.accept(this);
        UnaryOperator operator = unaryExpression.getUnaryOperator();

        if ((expType instanceof FloatType) && !operator.equals(UnaryOperator.not)) {
            return new FloatType();
        } else if ((expType instanceof BooleanType) && operator.equals(UnaryOperator.not)) {
            return new BooleanType();
        } else if ((expType instanceof IntType) && !operator.equals(UnaryOperator.not)) {
            return new IntType();
        } else if (expType instanceof NoType) { // don't added ti error of operands
            return new NoType();
        } else {
            typeErrors.add(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
            return new NoType();
        }
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Type tl = binaryExpression.getLeft().accept(this);
        Type tr = binaryExpression.getRight().accept(this);
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        if ((!(tl instanceof NoType) && !(tr instanceof NoType)) &&
                !sameType(tl, tr)) {
            UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
            typeErrors.add(exception);
            return new NoType();
        }
        if ((tl instanceof NoType) || (tr instanceof NoType))
            return new NoType();
        if (is_boolean_operator(operator) && sameType(tl, tr)) {
            Type t = new BooleanType();
            return t;
        }
        return new NoType();
    }

    public Type visit(ArrayAccess arrayAccess) {
        try {
            if (SymbolTable.top.get(VariableItem.STARTKEY + arrayAccess.getName()) instanceof VariableItem) {
                VariableItem varSym = (VariableItem) SymbolTable.top.get(VariableItem.STARTKEY +
                        arrayAccess.getName());
                return varSym.getType();
            } else {
                ArrayItem varSym = (ArrayItem) SymbolTable.top.get(VariableItem.STARTKEY +
                        arrayAccess.getName());
                return varSym.getType();
            }
        } catch (ItemNotFoundException exception3) {
            VarNotDeclared exception = new VarNotDeclared(arrayAccess.getLine(), arrayAccess.getName());
            typeErrors.add(exception);
            return new NoType();
        }
    }

    @Override

    public Type visit(Identifier identifier) {
        // identifier should be return value
        // if identifier is var --> return val
        // if func --> return return of func
        boolean is_func = identifier.iam_function_variable;
        if (is_func) {
            try {
                FunctionItem funcSym = (FunctionItem) SymbolTable.root.get(FunctionItem.STARTKEY +
                        identifier.getName());
                ArrayList<Type> args = funcSym.argTypes;
                if (args.size() == 1)
                    if (args.get(0) instanceof NoType)
                        args = new ArrayList<>();
                return funcSym.getFuncDeclaration().getType();

            } catch (ItemNotFoundException exception2) {
                FunctionNotDeclared exception = new FunctionNotDeclared(
                        identifier.getLine(), identifier.getName());
                typeErrors.add(exception);
                return new NoType();
            }
        }
        try {
            if (SymbolTable.top.get(VariableItem.STARTKEY + identifier.getName()) instanceof VariableItem) {
                VariableItem varSym = (VariableItem) SymbolTable.top.get(VariableItem.STARTKEY +
                        identifier.getName());
                return varSym.getType();
            } else {
                ArrayItem varSym = (ArrayItem) SymbolTable.top.get(VariableItem.STARTKEY +
                        identifier.getName());
                return varSym.getType();
            }
        } catch (ItemNotFoundException exception3) {
            VarNotDeclared exception = new VarNotDeclared(identifier.getLine(), identifier.getName());
            typeErrors.add(exception);
            return new NoType();
        }
    }

    @Override
    public Type visit(FunctionCall functionCall) {
        this.seenNoneLvalue = true;
        Integer count = typeErrors.size();
        functionCall.getUFuncName().iam_function_variable = true;
        Type returnType = functionCall.getUFuncName().accept(this);
        if (typeErrors.size() - count == 1)// if identifier belong to
            // funcall remove last type_errors
            typeErrors.remove(typeErrors.size() - 1);

        if ((returnType instanceof NoType)) {
            FunctionNotDeclared funNotDec = new FunctionNotDeclared(functionCall.getLine(),
                    functionCall.getUFuncName().getName());
            typeErrors.add(funNotDec);
        }

        for (var arg : functionCall.getArgs()) {
            arg.accept(this);
        }
        return returnType;
    }

    @Override
    public Type visit(IntValue value) {
        this.seenNoneLvalue = true;
        return new IntType();
    }

    @Override
    public Type visit(FloatValue value) {
        this.seenNoneLvalue = true;
        return new FloatType();
    }

    @Override
    public Type visit(BooleanValue value) {
        this.seenNoneLvalue = true;
        return new BooleanType();
    }
}
