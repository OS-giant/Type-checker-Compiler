package compileError.Type;

import compileError.CompileError;

public class FunctionNotDeclared extends CompileError {
    int line;
    String name;
    public FunctionNotDeclared(int line, String name){
        this.line = line;
        this.name = name;
    }
    public String getMessage(){
        return "Line " + line + ": Function "  + name + " is not declared";
    }

}

