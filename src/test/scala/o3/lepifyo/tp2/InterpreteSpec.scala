package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.analisis.Analizador
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.{BooleanoLiteral, NumeroLiteral}
import o3.lepifyo.tp2.ejecucion.Interprete
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InterpreteSpec extends AnyFunSpec with Matchers {

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

    it("el resultado de interpretar un programa con la operacion aritmetica de suma de dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 + 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(3))
    }

    it("no se puede interpretar un programa con una operacion de suma si el primer operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("true + 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion de suma si el segundo operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("1 + false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de resta de dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("2 - 1")

      interprete.interpretar(ast) should equal(NumeroLiteral(1))
    }

    it("no se puede interpretar un programa con una operacion de resta si el primer operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("true - 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion de resta si el segundo operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("1 - false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de multiplicacion de dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("18 * 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(36))
    }

    it("no se puede interpretar un programa con una operacion de multiplicacion si el primer operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("true * 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion de multiplicacion si el segundo operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("1 * false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion aritmetica de division de dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("18 / 2")

      interprete.interpretar(ast) should equal(NumeroLiteral(9))
    }

    it("no se puede interpretar un programa con una operacion de division si el primer operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("true / 2")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion de division si el segundo operador no es un numero literal ni una operacion aritmetica") {
      val ast = parser.parsear("1 / false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion de divisor con denominador 0") {
      val ast = parser.parsear("1 / 0")
      //FIXME: Por simpleza del refactor se deja ArithmeticExcepcion pero ver si levantar una propia.
      an[ArithmeticException] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con operaciones aritmeticas combinadas es la representacion estas operaciones") {
      val ast = parser.parsear("18 / (4 * 3 - 3)")

      interprete.interpretar(ast) should equal(NumeroLiteral(2))
    }

  }

  describe("operaciones de comparacion") {
    //FIXME hacemos dos tests por cada uno?
    it("el resultado de interpretar un programa con la operacion igual entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 == 1")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operacion igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true == 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 == false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion distinto entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 != 1")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operacion distinto con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true != 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion distinto con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 != false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion menor entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 < 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operacion menor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true < 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion menor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 < false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion mayor entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 > 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operacion mayor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true > 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion mayor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 > false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion menor o igual entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 <= 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operacion menor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true <= 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion menor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 <= false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("el resultado de interpretar un programa con la operacion mayor o igual entre dos numeros literales es la representacion del resultado") {
      val ast = parser.parsear("1 >= 3")

      interprete.interpretar(ast) should equal(BooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operacion mayor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true >= 1")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

    it("no se puede interpretar un programa con una operacion mayor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 >= false")

      an[ErrorDeTipos] should be thrownBy interprete.interpretar(ast)
    }

  }


}
