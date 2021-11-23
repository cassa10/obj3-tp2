package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.ast.exception.{ErrorDeTipos, ErrorDivisionPorCero}
import o3.lepifyo.tp2.ast.literales.NumeroLiteralAST
import o3.lepifyo.tp2.ast.operaciones.SumaAST
import o3.lepifyo.tp2.ast.variables.{DeclaracionVariableAST, VariableAST}
import o3.lepifyo.tp2.resultado.{ResultadoAsignacionVariable, ResultadoBooleanoLiteral, ResultadoLambda, ResultadoNumeroLiteral}
import o3.lepifyo.tp2.ejecucion.{Interprete, Memoria}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InterpreteSpec extends AnyFunSpec with Matchers {

  val parser = ParserFactory.build

  val interprete = new Interprete

  it("el resultado de interpretar un programa con un número literal es la representación del mismo número literal") {
    val ast = parser.parsear("12")

    interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(12))
  }

  it("el resultado de interpretar un programa con un booleano literal es la representación del mismo booleano literal") {
    val ast = parser.parsear("true")

    interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(true))
  }

  describe("operaciones aritmeticas") {

    it("el resultado de interpretar un programa con la operación aritmética de suma de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 + 2")

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(3))
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

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(1))
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

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(36))
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

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(9))
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

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(2))
    }

  }

  describe("operaciones de comparación") {
    //FIXME hacemos dos tests por cada uno?
    it("el resultado de interpretar un programa con la operación igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 == 1")

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(true))
    }

    it("el resultado de interpretar un programa con la operación igual entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true == true")

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(true))
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

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(false))
    }

    it("el resultado de interpretar un programa con la operación distinto entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true != true")

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(false))
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

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(true))
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

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(false))
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

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(true))
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

      interprete.interpretar(ast) should equal(ResultadoBooleanoLiteral(false))
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

  describe("variables") {

    it("el resultado de interpretar un programa con la declaración de una variable es la representación de declaración de variable y el guardado en memoria de la variable con su valor asociado") {
      val ast = parser.parsear("let edad = 27")

      interprete.interpretar(ast) should equal(ResultadoAsignacionVariable())
      Memoria.obtenerValorVariable("edad") should equal(ResultadoNumeroLiteral(27))
    }

    it("el resultado de interpretar un programa con la lectura de una variable previamente declarada es la representación del valor inicial asignado a la variable") {
      val programa = """let edad = 27
                       |edad"""
      val ast = parser.parsear(programa)

      interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(27))
    }

    it("el resultado de interpretar un programa con la escritura de una variable previamente declarada es la representación de asignación de variable y la actualización en memoria de la variable con su último valor asociado") {
      val programa =
        """let añoActual = 2020
          |añoActual = añoActual + 1"""
      val ast = parser.parsear(programa)

      interprete.interpretar(ast) should equal(ResultadoAsignacionVariable())
      Memoria.obtenerValorVariable("añoActual") should equal(ResultadoNumeroLiteral(2021))
    }

    describe("lambdas") {

      describe("declaración") {

        it("el resultado de interpretar un programa con la declaración de una lambda es la representación de la asignación de una variable y el guardado en memoria de la variable con su lambda asociada") {
          val ast = parser.parsear("let siempreUno = () -> 1")

          interprete.interpretar(ast) should equal(ResultadoAsignacionVariable())
          Memoria.obtenerValorVariable("siempreUno") should equal(ResultadoLambda(List.empty, List(NumeroLiteralAST(1))))
        }

        it("el resultado de interpretar un programa con la declaración de una lambda con múltiples líneas es la representación de la asignación de una variable y el guardado en memoria de la variable con su lambda asociada") {
          val programa =
            """let siempreUno = () -> {
              | let x = 1
              | x
              |}"""
          val ast = parser.parsear(programa)

          interprete.interpretar(ast) should equal(ResultadoAsignacionVariable())
          Memoria.obtenerValorVariable("siempreUno") should equal(ResultadoLambda(List.empty, List(DeclaracionVariableAST("x", NumeroLiteralAST(1)), VariableAST("x"))))
        }

      }

      describe("aplicación") {

        it("el resultado de interpretar un programa con la aplicación de una lambda es la representación del resultado de la aplicación de la lambda") {
          val ast = parser.parsear("(() -> 1)()")

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(1))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con un parámetro es la representación del resultado de la aplicación de la lambda con el argumento correspondiente") {
          val ast = parser.parsear("((x) -> x + 1)(2)")

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(3))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con más de un parámetro es la representación del resultado de la aplicación de la lambda con los argumentos correspondientes") {
          val ast = parser.parsear("((x, y) -> x + y)(3, 2)")

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(5))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda previamente asignada a una variable es la representación del resultado de la aplicación de la lambda") {
          val programa =
            """let siempreUno = () -> 1
              |siempreUno()"""
          val ast = parser.parsear(programa)

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(1))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con un parámetro es el resultado de la aplicación de la lambda con el argumento correspondiente") {
          val programa =
            """let masUno = (x) -> x + 1
              |masUno(2)"""
          val ast = parser.parsear(programa)

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(3))
          Memoria.obtenerValorVariable("masUno") should equal(ResultadoLambda(List("x"), List(SumaAST(VariableAST("x"), NumeroLiteralAST(1)))))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con más de un parámetro es el resultado de la aplicación de la lambda con los argumentos correspondientes") {
          val programa =
            """let suma = (x, y) -> x + y
              |suma(3, 2)"""
          val ast = parser.parsear(programa)

          interprete.interpretar(ast) should equal(ResultadoNumeroLiteral(5))
          Memoria.obtenerValorVariable("suma") should equal(ResultadoLambda(List("x", "y"), List(SumaAST(VariableAST("x"), VariableAST("y")))))
        }

      }

    }

  }

}
