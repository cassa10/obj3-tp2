package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserLepifyo
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UsoParserSpec extends AnyFunSpec with Matchers {
    it("uso del parser con números literales") {
      type Expresion = Int
      type Programa = List[Expresion]

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = n

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
      )

      val ast = parser.parsear("12")

      ast should equal(programa(numero(12)))
  }
}
