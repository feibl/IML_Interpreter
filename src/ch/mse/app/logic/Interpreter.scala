package ch.mse.app.logic

import scala.collection.mutable.Map

object Interpreter {

  type Value = Int
  type State = Map[String, Value]

  def eval(expr: BoolExpr, state: State): Boolean =
    expr match {
      case LitBExpr(value) => value
      case NegBExpr(expr) => !eval(expr, state)
      case DyaBExpr(CondAnd, op1, op2) => eval(op1, state) && eval(op2, state)
      case DyaBExpr(CondOr, op1, op2) => eval(op1, state) || eval(op2, state)
    }

  def eval(expr: ArithExpr, state: State): Int =
    expr match {
      case LitAExpr(value) => value
      case IdAExpr(ident) => state(ident)
      case DyaAExpr(Times, op1, op2) => eval(op1, state) * eval(op2, state)
      case DyaAExpr(Div, op1, op2) => eval(op1, state) / eval(op2, state)
      case DyaAExpr(Mod, op1, op2) => eval(op1, state) % eval(op2, state)
      case DyaAExpr(Plus, op1, op2) => eval(op1, state) + eval(op2, state)
      case DyaAExpr(Minus, op1, op2) => eval(op1, state) - eval(op2, state)
    }

  def eval(expr: Command, state: State): State =
    expr match {
      case AssiCmd(ident, op) => state.put(ident, eval(op, state)); state
    }

}