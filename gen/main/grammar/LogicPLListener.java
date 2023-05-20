// Generated from /home/yektanet/Edu/TA/PLC/CA3V2/src/main/grammar/LogicPL.g4 by ANTLR 4.12.0
package main.grammar;

import ast.node.*;
import ast.node.expression.*;
import ast.node.statement.*;
import ast.node.declaration.*;
import ast.node.expression.values.*;
import ast.node.expression.operators.*;
import ast.type.primitiveType.*;
import ast.type.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogicPLParser}.
 */
public interface LogicPLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LogicPLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LogicPLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#functionDec}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDec(LogicPLParser.FunctionDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#functionDec}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDec(LogicPLParser.FunctionDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#functionVarDec}.
	 * @param ctx the parse tree
	 */
	void enterFunctionVarDec(LogicPLParser.FunctionVarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#functionVarDec}.
	 * @param ctx the parse tree
	 */
	void exitFunctionVarDec(LogicPLParser.FunctionVarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#mainBlock}.
	 * @param ctx the parse tree
	 */
	void enterMainBlock(LogicPLParser.MainBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#mainBlock}.
	 * @param ctx the parse tree
	 */
	void exitMainBlock(LogicPLParser.MainBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LogicPLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LogicPLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#assignSmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignSmt(LogicPLParser.AssignSmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#assignSmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignSmt(LogicPLParser.AssignSmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LogicPLParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LogicPLParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#localVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVarDeclaration(LogicPLParser.LocalVarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#localVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVarDeclaration(LogicPLParser.LocalVarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(LogicPLParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(LogicPLParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(LogicPLParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(LogicPLParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#arrayInitialValue}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitialValue(LogicPLParser.ArrayInitialValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#arrayInitialValue}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitialValue(LogicPLParser.ArrayInitialValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#arrayList}.
	 * @param ctx the parse tree
	 */
	void enterArrayList(LogicPLParser.ArrayListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#arrayList}.
	 * @param ctx the parse tree
	 */
	void exitArrayList(LogicPLParser.ArrayListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#printSmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintSmt(LogicPLParser.PrintSmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#printSmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintSmt(LogicPLParser.PrintSmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(LogicPLParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(LogicPLParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(LogicPLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(LogicPLParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#queryType1}.
	 * @param ctx the parse tree
	 */
	void enterQueryType1(LogicPLParser.QueryType1Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#queryType1}.
	 * @param ctx the parse tree
	 */
	void exitQueryType1(LogicPLParser.QueryType1Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#queryType2}.
	 * @param ctx the parse tree
	 */
	void enterQueryType2(LogicPLParser.QueryType2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#queryType2}.
	 * @param ctx the parse tree
	 */
	void exitQueryType2(LogicPLParser.QueryType2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#returnSmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnSmt(LogicPLParser.ReturnSmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#returnSmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnSmt(LogicPLParser.ReturnSmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(LogicPLParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(LogicPLParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(LogicPLParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(LogicPLParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#implication}.
	 * @param ctx the parse tree
	 */
	void enterImplication(LogicPLParser.ImplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#implication}.
	 * @param ctx the parse tree
	 */
	void exitImplication(LogicPLParser.ImplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LogicPLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LogicPLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(LogicPLParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(LogicPLParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(LogicPLParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(LogicPLParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr2(LogicPLParser.AndExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#andExpr2}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr2(LogicPLParser.AndExpr2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(LogicPLParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(LogicPLParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#eqExpr2}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr2(LogicPLParser.EqExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#eqExpr2}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr2(LogicPLParser.EqExpr2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#compExpr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(LogicPLParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#compExpr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(LogicPLParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#compExpr2}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr2(LogicPLParser.CompExpr2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#compExpr2}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr2(LogicPLParser.CompExpr2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#additive}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(LogicPLParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#additive}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(LogicPLParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#additive2}.
	 * @param ctx the parse tree
	 */
	void enterAdditive2(LogicPLParser.Additive2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#additive2}.
	 * @param ctx the parse tree
	 */
	void exitAdditive2(LogicPLParser.Additive2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#multicative}.
	 * @param ctx the parse tree
	 */
	void enterMulticative(LogicPLParser.MulticativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#multicative}.
	 * @param ctx the parse tree
	 */
	void exitMulticative(LogicPLParser.MulticativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#multicative2}.
	 * @param ctx the parse tree
	 */
	void enterMulticative2(LogicPLParser.Multicative2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#multicative2}.
	 * @param ctx the parse tree
	 */
	void exitMulticative2(LogicPLParser.Multicative2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(LogicPLParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(LogicPLParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#other}.
	 * @param ctx the parse tree
	 */
	void enterOther(LogicPLParser.OtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#other}.
	 * @param ctx the parse tree
	 */
	void exitOther(LogicPLParser.OtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(LogicPLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(LogicPLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(LogicPLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(LogicPLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void enterNumericValue(LogicPLParser.NumericValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#numericValue}.
	 * @param ctx the parse tree
	 */
	void exitNumericValue(LogicPLParser.NumericValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(LogicPLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(LogicPLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#predicateIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterPredicateIdentifier(LogicPLParser.PredicateIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#predicateIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitPredicateIdentifier(LogicPLParser.PredicateIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogicPLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(LogicPLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogicPLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(LogicPLParser.TypeContext ctx);
}