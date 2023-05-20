package compileError.Type;

import compileError.CompileError;

public class VarNotDeclared extends CompileError {
    int line;
    String name;
    public VarNotDeclared(int line, String name){
        this.line = line;
        this.name = name;
    }
    public String getMessage(){
        return "Line " + line + ": Variable "  + name + " is not declared";
    }


}
