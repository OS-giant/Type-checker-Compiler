package ast.node.declaration;

import ast.node.expression.Identifier;
import ast.node.statement.Statement;
import ast.type.Type;
import visitor.IVisitor;

import java.util.ArrayList;

//Line -> FUNCTION
public class FuncDeclaration extends Declaration {
    private Identifier name;
    private Type returnType;
    private ArrayList<ArgDeclaration> args = new ArrayList<>();
    private ArrayList<Statement> statements = new ArrayList<>();

    public FuncDeclaration(Identifier name, Type returnType, ArrayList<ArgDeclaration> args, ArrayList<Statement> statements){
        this.name = name;
        this.returnType = returnType;
        this.args = args;
        this.statements = statements;
    }

    public Identifier getName() {
        return name;
    }

    public void setArgs(ArrayList<ArgDeclaration> args) {
        this.args = args;
    }
    public ArrayList<ArgDeclaration> getArgs() {
        return args;
    }
    public void addArgs(ArgDeclaration arg) {
        args.add(arg);
    }
    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }
    public ArrayList<Statement> getStatements() {
        return statements;
    }
    public void addStatements(Statement statement) {
        statements.add(statement);
    }

    public Type getType() {
        return returnType;
    }

    public void setType(Type returnType) {
        this.returnType = returnType;
    }

    public Identifier getIdentifier() {
        return name;
    }

    public void setIdentifier(Identifier name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FuncDeclaration" + name.toString();
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
