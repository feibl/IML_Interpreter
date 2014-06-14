package ch.mse.app.logic

package object Syntax { type Ident = String }

sealed trait BoolOperator
case object CondAnd extends BoolOperator
case object CondOr extends BoolOperator

abstract class BoolExpr
case class LitBExpr(value: Boolean) extends BoolExpr
case class NegBExpr(expr: BoolExpr) extends BoolExpr
case class DyaBExpr(operator: BoolOperator, op1: BoolExpr, op2: BoolExpr) extends BoolExpr

sealed trait ArithOperator
case object Times extends ArithOperator
case object Div extends ArithOperator
case object Plus extends ArithOperator
case object Minus extends ArithOperator
case object Mod extends ArithOperator

abstract class ArithExpr
case class LitAExpr(value: Int) extends ArithExpr
case class IdAExpr(ident: Syntax.Ident) extends ArithExpr
case class DyaAExpr(operator: ArithOperator, op1: ArithExpr, op2: ArithExpr) extends ArithExpr

abstract class Command
case class AssiCmd(ident: Syntax.Ident, op: ArithExpr) extends Command