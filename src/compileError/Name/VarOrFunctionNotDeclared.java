package compileError.Name;

import compileError.CompileError;

public class VarOrFunctionNotDeclared extends CompileError {

    int line;
    String name;
    public VarOrFunctionNotDeclared(int line, String name){
        this.line = line;
        this.name = name;
    }
    public String getMessage(){
        return "Line " + line + ": Variable or function "  + name + " is not declared";
    }


}