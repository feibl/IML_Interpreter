package ch.mse.app.logic

import Interpreter._
import scala.util.parsing.combinator._

//program ::= cpsCmd.
//cpsCmd ::= cmd { ";" cmd }.
//cmd ::=
//ID ":=" arithExpr.
//| "if" boolExpr "then" cpsCmd ["else" cpsCmd] "endif".
//| "while" boolExpr "do" cpsCmd "endwhile".
//| "skip".
//arithExpr ::=
//NUM
//| ID
//| "(" arithExpr ("+"|"-"|"*"|"div"|"mod") arithExpr ")".
//boolExpr
//::=
//"(" arithExpr ("<="|"<"|">="|">"|"<="|"="|"/=") arithExpr ")"
//| "NOT" boolExpr
//| "(" boolExpr ("cand" | "cor") boolExpr ")"
//| "true"
//| "false"

object IMLParser extends JavaTokenParsers {
  def numericLit = """\d+""".r
  def stringLit = """[a-zA-Z_]+""".r

  def program: Parser[List[Command]] = cpsCmd

  def cpsCmd: Parser[List[Command]] = {
    println("cpsCmd");
    cmd ~ rep(";" ~> cmd) ^^ { case x ~ xs => x :: xs }
  }

  def arithExpr: Parser[ArithExpr] = {
    term ~ ("+" | "-") ~ arithExpr ^^ {
      case l ~ "+" ~ r => DyaAExpr(Plus, l, r)
      case l ~ "-" ~ r => DyaAExpr(Minus, l, r)
    } |
      term
  }

  def term: Parser[ArithExpr] = {
    factor ~ ("*" | "div" | "mod") ~ term ^^ {
      case l ~ "*" ~ r => DyaAExpr(Times, l, r)
      case l ~ "div" ~ r => DyaAExpr(Div, l, r)
      case l ~ "mod" ~ r => DyaAExpr(Mod, l, r)
    } |
      factor
  }

  def factor: Parser[ArithExpr] = {
    "(" ~> arithExpr <~ ")" |
      numericLit ^^ { (x => LitAExpr(x.toInt)) } |
      stringLit ^^ (id => IdAExpr(id))
  }

  def cmd: Parser[Command] = {
    println("cmd");
    (stringLit <~ ":=") ~ arithExpr ^^ { case id ~ expr => AssiCmd(id, expr) }
  }

}