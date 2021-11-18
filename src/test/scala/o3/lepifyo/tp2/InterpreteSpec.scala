package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.analisis.Analizador
import o3.lepifyo.tp2.ast.exception.{ErrorDeTipos, ErrorDivisionPorCero}
import o3.lepifyo.tp2.ast.{BooleanoLiteral, NumeroLiteral, Variable}
import o3.lepifyo.tp2.ejecucion.Interprete
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InterpreteSpec extends AnyFunSpec with Matchers {

  val parser = ParserFactory.build

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

    it("el resultado de interpretar un programa con la operación aritmética de suma de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 + 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(3))
    }

    it("no se puede interpretar un programa con una operación de suma si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true + 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación de suma si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 + false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación aritmética de resta de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("2 - 1")

      interprete.interpretar(ast) should equal(NumeroLiteral(1))
    }

    it("no se puede interpretar un programa con una operación de resta si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true - 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación de resta si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 - false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación aritmética de multiplicacion de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("18 * 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(36))
    }

    it("no se puede interpretar un programa con una operación de multiplicacion si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true * 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación de multiplicacion si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 * false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación aritmética de division de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("18 / 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(9))
    }

    it("no se puede interpretar un programa con una operación de division si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true / 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación de division si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 / false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación de divisor con denominador 0") {
      val ast = parser.parsear("1 / 0")

      an[ErrorDivisionPorCero] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con operaciones aritmeticas combinadas es la representación del resultado de las operaciones") {
      val ast = parser.parsear("18 / (4 * 3 - 3)")

      interprete.interpretar(ast) should equal(NumeroLiteral(2))
    }

  }

  describe("operaciones de comparación") {
    //FIXME hacemos dos tests por cada uno?
    it("el resultado de interpretar un programa con la operación igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 == 1")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("el resultado de interpretar un programa con la operación igual entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true == true")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación igual con el primer operador de un tipo distinto al segundo") {
      val ast = parser.parsear("true == 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación igual con el segundo operador de un tipo distinto al primero") {
      val ast = parser.parsear("1 == false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación distinto entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 != 1")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("el resultado de interpretar un programa con la operación distinto entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true != true")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación distinto con el primer operador de un tipo distinto al segundo") {
      val ast = parser.parsear("true != 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación distinto con el segundo operador de un tipo distinto al primero") {
      val ast = parser.parsear("1 != false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación menor entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 < 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación menor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true < 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación menor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 < false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación mayor entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 > 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación mayor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true > 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación mayor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 > false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación menor o igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 <= 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación menor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true <= 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación menor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 <= false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operación mayor o igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 >= 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación mayor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true >= 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operación mayor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 >= false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

  }

  describe("declaración de variable") {

    it("el resultado de interpretar un programa con la declaración de una variable es la representación de la variable definida con el valor definido asociado") {
      val ast = parser.parsear("let edad = 27")

      interprete.interpretar(ast) should equal(Variable("edad", NumeroLiteral(27)))
    }

  }

}
