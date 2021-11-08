package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserLepifyo
import o3.lepifyo.tp2.ast.{BooleanoLiteral, DivisionAST, ElementoAST, MultiplicacionAST, NumeroLiteral, RestaAST, SumaAST}
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

  describe("operaciones aritmeticas") {
    it("el resultado de interpretar un programa con la operacion aritmetica de suma de dos numeros literales" +
      "es la representacion de la misma operacion") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = NumeroLiteral(n)
      def suma(o1: Expresion, o2: Expresion) = SumaAST(o1, o2)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
        suma = suma,
      )

      val ast = parser.parsear("1 + 2")

      interprete.interpretar(ast) should equal(SumaAST(NumeroLiteral(1), NumeroLiteral(2)))
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de resta de dos numeros literales" +
      "es la representacion de la misma operacion") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = NumeroLiteral(n)
      def resta(o1: Expresion, o2: Expresion) = RestaAST(o1, o2)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
        resta = resta,
      )

      val ast = parser.parsear("2 - 1")

      interprete.interpretar(ast) should equal(RestaAST(NumeroLiteral(2), NumeroLiteral(1)))
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de multiplicacion de dos numeros literales" +
      "es la representacion de la misma operacion") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = NumeroLiteral(n)
      def multiplicacion(o1: Expresion, o2: Expresion) = MultiplicacionAST(o1, o2)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
        multiplicacion = multiplicacion,
      )

      val ast = parser.parsear("18 * 2")

      interprete.interpretar(ast) should equal(MultiplicacionAST(NumeroLiteral(18), NumeroLiteral(2)))
    }


    it("el resultado de interpretar un programa con la operacion aritmetica de division de dos numeros literales" +
      "es la representacion de la misma operacion") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = NumeroLiteral(n)
      def division(o1: Expresion, o2: Expresion) = DivisionAST(o1, o2)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
        division = division,
      )

      val ast = parser.parsear("18 / 2")

      interprete.interpretar(ast) should equal(DivisionAST(NumeroLiteral(18), NumeroLiteral(2)))
    }

    it("el resultado de interpretar un programa con operaciones aritmeticas combinadas" +
      "es la representacion estas operaciones") {
      type Expresion = ElementoAST
      type Programa = List[Expresion] //TODO: modelar programa

      def programa(expresiones: Expresion*) = expresiones.toList
      def numero(n: Int) = NumeroLiteral(n)
      def division(o1: Expresion, o2: Expresion) = DivisionAST(o1, o2)
      def resta(o1: Expresion, o2: Expresion) = RestaAST(o1, o2)
      def multiplicacion(o1: Expresion, o2: Expresion) = MultiplicacionAST(o1, o2)

      val parser = new ParserLepifyo[Programa, Expresion](
        programa = programa,
        numero = numero,
        division = division,
        multiplicacion = multiplicacion,
        resta = resta,
      )

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

