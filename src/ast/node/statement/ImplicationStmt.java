package ast.node.statement;

import ast.node.expression.Expression;
import ast.node.expression.Identifier;
import ast.node.expression.Variable;
import visitor.IVisitor;

import java.util.ArrayList;

//Line -> ARROW
public class ImplicationStmt extends Statement {
    private Expression condition;
    private ArrayList<Statement> resaultStmts =  new ArrayList<>();

    public ImplicationStmt(Expression condition, ArrayList<Statement> resaultStmts) {
        this.condition = condition;
        this.resaultStmts = resaultStmts;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.resaultStmts = statements;
    }
    public ArrayList<Statement> getStatements() {
        return resaultStmts;
    }
    public void addStatements(Statement statement) {
        resaultStmts.add(statement);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "ImplicationStmt";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
