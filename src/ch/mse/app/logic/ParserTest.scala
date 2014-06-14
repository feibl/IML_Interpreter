package ch.mse.app.logic

import IMLParser._
import Interpreter._
import scala.util.parsing.combinator._
import scala.collection.mutable.HashMap

object ParserTest {

  def main(args: Array[String]) {
    val result = parseAll(program, """
    	a := 3;
    	|b := 4;
    	|c := a * b + b * 2""".stripMargin);
    println(result)
    val commands = result.get
    var state: State = new HashMap[String, Int]();
    for (command <- commands) {
      state = eval(command, state);
    }
    println(state)
  }

}