package ast.node;

import ast.node.declaration.FuncDeclaration;
import ast.node.declaration.MainDeclaration;
import visitor.IVisitor;
import visitor.Visitor;

import java.util.ArrayList;

//Line -> 0
public class Program extends Node {
    private ArrayList<FuncDeclaration> funcs = new ArrayList<>();
    private MainDeclaration programMainDeclaration;

    public void addFunc(FuncDeclaration funcDeclaration) {
        funcs.add(funcDeclaration);
    }

    public ArrayList<FuncDeclaration> getFuncs() {
        return funcs;
    }


    public MainDeclaration getMain() {
        return this.programMainDeclaration;
    }

    public void setMain(MainDeclaration mainDeclarationActors) {
        this.programMainDeclaration = mainDeclarationActors;
    }

    @Override
    public String toString() {
        return "Program";
    }
    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
