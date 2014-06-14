package ch.mse.app.logic

import Interpreter._
import scala.collection.mutable.HashMap

object InterpreterTest {

  def main(args: Array[String]) {
    val bExpr = DyaBExpr(CondAnd, LitBExpr(true), LitBExpr(false));
    println(eval(bExpr, new HashMap[String, Int]()));
    var state: State = new HashMap[String, Int]();
    val assiA = AssiCmd("a", LitAExpr(5));
    val assiB = AssiCmd("b", LitAExpr(10));
    val aExpr = DyaAExpr(Times, IdAExpr("a"), IdAExpr("b"));
    state = eval(assiA, state);
    state = eval(assiB, state);
    println(eval(aExpr, state));
  }

}