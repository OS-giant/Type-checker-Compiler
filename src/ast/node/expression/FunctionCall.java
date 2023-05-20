package ast.node.expression;

import ast.node.expression.operators.UnaryOperator;
import visitor.IVisitor;

import java.util.ArrayList;

//Line -> identifier
public class FunctionCall extends Expression{

    private Identifier funcName;
    private ArrayList<Expression> args;

    public FunctionCall(ArrayList<Expression> args, Identifier funcName) {
        this.args = args;
        this.funcName = funcName;
    }

    public Identifier getUFuncName() {
        return funcName;
    }

    public void setFuncName(Identifier funcName) {
        this.funcName = funcName;
    }

    public ArrayList<Expression> getArgs() {
        return args;
    }

    public void setArgs(ArrayList<Expression> funcName) {
        this.args = funcName;
    }

    @Override
    public String toString() {
        return "FunctionCall";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
