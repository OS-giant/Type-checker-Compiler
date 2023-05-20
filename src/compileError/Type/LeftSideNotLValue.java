package compileError.Type;

import compileError.CompileError;

public class LeftSideNotLValue extends CompileError {
    int line;

    public LeftSideNotLValue(int line){
        this.line = line;

    }
    public String getMessage(){
        return "Line " + line + ": Left side of the assignment must be a valid lvalue" ;
    }
}
