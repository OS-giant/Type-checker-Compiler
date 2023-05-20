package visitor;

import ast.node.Program;
import ast.node.declaration.*;
import ast.node.declaration.ArgDeclaration;
import ast.node.expression.*;
import ast.node.expression.values.BooleanValue;
import ast.node.expression.values.FloatValue;
import ast.node.expression.values.IntValue;
import ast.node.statement.*;
import ast.type.Type;
import ast.type.primitiveType.BooleanType;
import ast.type.primitiveType.FloatType;

public class Visitor<T> implements IVisitor<T> {
    @Override
    public T visit(Program program) {
        return null;
    }
    @Override
    public T visit (ArgDeclaration argDeclaration) {
        return null;
    }
    @Override
    public T visit(MainDeclaration mainDeclaration) {
        return null;
    }
    @Override
    public T visit(FuncDeclaration funcDeclaration) {
        return null;
    }
    @Override
    public T visit(UnaryExpression unaryExpression) {
        return null;
    }
    @Override
    public T visit(BinaryExpression binaryExpression) {
        return null;
    }
    @Override
    public T visit(Identifier identifier) {
        return null;
    }
    @Override
    public T visit(ArrayAccess arrayAccess) {
        return null;
    }
    @Override
    public T visit(FunctionCall funcCall) {
        return null;
    }
    @Override
    public T visit(QueryExpression queryExpression) {
        return null;
    }
    @Override
    public T visit(IntValue value) {
        return null;
    }
    @Override
    public T visit(FloatValue value) {
        return null;
    }
    @Override
    public T visit(BooleanValue value) {
        return null;
    }
    @Override
    public T visit(ArrayDecStmt arrayDecStmt) {
        return null;
    }
    @Override
    public T visit(ForloopStmt forloopStmt) {
        return null;
    }
    @Override
    public T visit(ImplicationStmt implicationStmt) {
        return null;
    }
    @Override
    public T visit(PredicateStmt predicateStmt) {
        return null;
    }
    @Override
    public T visit(ReturnStmt returnStmt) {
        return null;
    }
    @Override
    public T visit(VarDecStmt varDecStmt) {
        return null;
    }
    @Override
    public T visit(PrintStmt printStmt) {
        return null;
    }
    @Override
    public T visit(AssignStmt assignStmt) {
        return null;
    }
    @Override
    public T visit(BooleanType value) {
        return null;
    }
    @Override
    public T visit(FloatType value) { return null; }
}
