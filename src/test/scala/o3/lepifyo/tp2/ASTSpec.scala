package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserLepifyo
import o3.lepifyo.tp2.ast.{BooleanoLiteral, ElementoAST, NumeroLiteral}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ASTSpec extends AnyFunSpec with Matchers {

  val interprete = new Interprete

  it("el resultado de interpretar un programa con un número literal es la representación del mismo número literal") {
    type Expresion = ElementoAST
    type Programa = List[Expresion] //TODO: modelar programa

    def programa(expresiones: Expresion*) = expresiones.toList
    def numero(n: Int) = NumeroLiteral(n)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      numero = numero,
    )

    val ast = parser.parsear("12")

    interprete.interpretar(ast) should equal(NumeroLiteral(12))
  }

  it("el resultado de interpretar un programa con un booleano literal es la representación del mismo booleano literal") {
    type Expresion = ElementoAST
    type Programa = List[Expresion] //TODO: modelar programa

    def programa(expresiones: Expresion*) = expresiones.toList
    def booleano(b: Boolean) = BooleanoLiteral(b)

    val parser = new ParserLepifyo[Programa, Expresion](
      programa = programa,
      booleano = booleano,
    )

    val ast = parser.parsear("true")

    interprete.interpretar(ast) should equal(BooleanoLiteral(true))
  }

}
