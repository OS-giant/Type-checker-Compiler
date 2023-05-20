package ast.node.declaration;

import java.util.ArrayList;

import ast.node.statement.*;

import visitor.IVisitor;

//Line -> MAIN
public class MainDeclaration extends Declaration {

    private ArrayList<Statement> mainStmts = new ArrayList<>();

    public MainDeclaration(ArrayList<Statement> mainStmts) {
        this.mainStmts = mainStmts;
    }

    public ArrayList<Statement> getMainStatements() { return mainStmts; }

    public void setMainStatements(ArrayList<Statement> mainStmts) {
        this.mainStmts = mainStmts;
    }

    public void addMainStatement(Statement statement) {
        mainStmts.add(statement);
    }

    @Override
    public String toString() {
        return "MainDeclaration";
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}