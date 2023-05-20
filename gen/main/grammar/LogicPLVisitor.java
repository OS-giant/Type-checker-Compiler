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

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogicPLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicPLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LogicPLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#functionDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDec(LogicPLParser.FunctionDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#functionVarDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionVarDec(LogicPLParser.FunctionVarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#mainBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainBlock(LogicPLParser.MainBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LogicPLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#assignSmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignSmt(LogicPLParser.AssignSmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LogicPLParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#localVarDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVarDeclaration(LogicPLParser.LocalVarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(LogicPLParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDeclaration(LogicPLParser.ArrayDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#arrayInitialValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitialValue(LogicPLParser.ArrayInitialValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#arrayList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayList(LogicPLParser.ArrayListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#printSmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintSmt(LogicPLParser.PrintSmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#printExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(LogicPLParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(LogicPLParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#queryType1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryType1(LogicPLParser.QueryType1Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#queryType2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryType2(LogicPLParser.QueryType2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#returnSmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSmt(LogicPLParser.ReturnSmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#forLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(LogicPLParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(LogicPLParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#implication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplication(LogicPLParser.ImplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LogicPLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(LogicPLParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(LogicPLParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#andExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr2(LogicPLParser.AndExpr2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#eqExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(LogicPLParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#eqExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr2(LogicPLParser.EqExpr2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#compExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(LogicPLParser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#compExpr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr2(LogicPLParser.CompExpr2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#additive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(LogicPLParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#additive2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive2(LogicPLParser.Additive2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#multicative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulticative(LogicPLParser.MulticativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#multicative2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulticative2(LogicPLParser.Multicative2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(LogicPLParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOther(LogicPLParser.OtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(LogicPLParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(LogicPLParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#numericValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericValue(LogicPLParser.NumericValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(LogicPLParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#predicateIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateIdentifier(LogicPLParser.PredicateIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link LogicPLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(LogicPLParser.TypeContext ctx);
}