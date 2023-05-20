package visitor;

import ast.node.Program;
import ast.node.declaration.*;
import ast.node.declaration.ArgDeclaration;
import ast.node.expression.*;
import ast.node.expression.values.BooleanValue;
import ast.node.expression.values.FloatValue;
import ast.node.expression.values.IntValue;
import ast.node.statement.*;
import ast.type.primitiveType.BooleanType;
import ast.type.primitiveType.FloatType;

public interface IVisitor<T> {

    T visit(Program program);
    T visit(ArgDeclaration argDeclaration);
    T visit(MainDeclaration mainDeclaration);
    T visit(FuncDeclaration funcDeclaration);
    T visit(UnaryExpression unaryExpression);
    T visit(BinaryExpression binaryExpression);
    T visit(Identifier identifier);
    T visit(ArrayAccess arrayAccess);
    T visit(FunctionCall funcCall);
    T visit(QueryExpression queryExpression);
    T visit(IntValue value);
    T visit(FloatValue value);
    T visit(BooleanValue value);
    T visit(ArrayDecStmt arrayDecStmt);
    T visit(ForloopStmt forloopStmt);
    T visit(ImplicationStmt implicationStmt);
    T visit(PredicateStmt predicateStmt);
    T visit(ReturnStmt returnStmt);
    T visit(VarDecStmt varDecStmt);
    T visit(PrintStmt printStmt);
    T visit(AssignStmt assignStmt);
    T visit(BooleanType value);
    T visit(FloatType value);
}
