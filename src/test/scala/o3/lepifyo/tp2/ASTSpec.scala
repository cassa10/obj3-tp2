package o3.lepifyo.tp2

import o3.lepifyo.parser.{ParserFactory, ParserLepifyo}
import o3.lepifyo.tp2.ast.{BooleanoLiteral, DivisionAST, ElementoAST, MultiplicacionAST, NumeroLiteral, RestaAST, SumaAST}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ASTSpec extends AnyFunSpec with Matchers {

  val parser = {
    val parserFactory = new ParserFactory
    parserFactory.build
  }

  val interprete = new Interprete

  it("el resultado de interpretar un programa con un número literal es la representación del mismo número literal") {
    val ast = parser.parsear("12")

    interprete.interpretar(ast) should equal(NumeroLiteral(12))
  }

  it("el resultado de interpretar un programa con un booleano literal es la representación del mismo booleano literal") {
    val ast = parser.parsear("true")

    interprete.interpretar(ast) should equal(BooleanoLiteral(true))
  }

  describe("operaciones aritmeticas") {

    it("el resultado de interpretar un programa con la operacion aritmetica de suma de dos numeros literales es la representacion de la misma operacion") {
      val ast = parser.parsear("1 + 2")

      interprete.interpretar(ast) should equal(SumaAST(NumeroLiteral(1), NumeroLiteral(2))) //TODO: ...should equal(NumeroLiteral(3))
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de resta de dos numeros literales es la representacion de la misma operacion") {
      val ast = parser.parsear("2 - 1")

      interprete.interpretar(ast) should equal(RestaAST(NumeroLiteral(2), NumeroLiteral(1)))
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de multiplicacion de dos numeros literales es la representacion de la misma operacion") {
      val ast = parser.parsear("18 * 2")

      interprete.interpretar(ast) should equal(MultiplicacionAST(NumeroLiteral(18), NumeroLiteral(2)))
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de division de dos numeros literales es la representacion de la misma operacion") {
      val ast = parser.parsear("18 / 2")

      interprete.interpretar(ast) should equal(DivisionAST(NumeroLiteral(18), NumeroLiteral(2)))
    }

    it("el resultado de interpretar un programa con operaciones aritmeticas combinadas es la representacion estas operaciones") {
      val ast = parser.parsear("18 / (4 * 5 - 3)")

      interprete.interpretar(ast) should equal(DivisionAST(
        NumeroLiteral(18),
        RestaAST(
          MultiplicacionAST(
            NumeroLiteral(4),
            NumeroLiteral(5)),
          NumeroLiteral(3))
      ))
    }

  }

}

