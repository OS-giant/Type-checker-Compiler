package ast.node;

import compileError.CompileError;
import visitor.IVisitor;
import visitor.Visitor;
import java.util.ArrayList;

public abstract class Node {

    private ArrayList<CompileError> errors;
    private int line;
    public static boolean isCatchErrorsActive = true;

    public void setLine(int line_num) {
        this.line = line_num;
    }

    public int getLine() {
        return this.line;
    }

    public abstract String toString();
    public abstract <T> T accept(IVisitor<T> visitor);

    public void addError(CompileError e) {
        if(Node.isCatchErrorsActive) {
            this.errors.add(e);
        }
    }

    public ArrayList<CompileError> flushErrors() {
        ArrayList<CompileError> errors = this.errors;
        this.errors = new ArrayList<>();
        return errors;
    }
}
