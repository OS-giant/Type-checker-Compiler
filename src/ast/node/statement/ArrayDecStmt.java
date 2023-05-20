package ast.node.statement;

import ast.node.expression.Expression;
import ast.type.Type;
import visitor.IVisitor;
import ast.node.expression.Identifier;

import java.util.ArrayList;

//Line -> identifier
public class ArrayDecStmt extends Statement {
    private Identifier identifier;
    private Type type;
    private int arrSize;
    private ArrayList<Expression> initialValues = new ArrayList<>();

    public ArrayDecStmt(Identifier identifier, Type type, int arrSize) {
        this.identifier = identifier;
        this.type = type;
        this.arrSize = arrSize;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getArrSize() {
        return arrSize;
    }

    public void setArrSize(int arrSize) {
        this.arrSize = arrSize;
    }

    public void setInitialValues(ArrayList<Expression> initialValues) {
        this.initialValues = initialValues;
    }
    public ArrayList<Expression> getInitialValues() {
        return initialValues;
    }
    public void addInitialValue(Expression InitialValue) {
        initialValues.add(InitialValue);
    }

    @Override
    public String toString() {
        return "ArrayDeclaration " + arrSize;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}