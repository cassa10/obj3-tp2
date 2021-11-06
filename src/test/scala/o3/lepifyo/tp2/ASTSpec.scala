package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserLepifyo
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ASTSpec extends AnyFunSpec with Matchers {

  it("el resultado de interpretar un número literal es el mismo número literal") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList

      def numero(n: Int) = NumeroLiteral(n)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
      )

      val ast = parser.parsear("12")

      val interprete = new Interprete[Programa]

      interprete.interpretar(ast) should equal(12)
  }

}
