package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.ast.exception.{ErrorDeTipos, ErrorDivisionPorCero}
import o3.lepifyo.tp2.resultado.{ResultadoAsignacionVariable, ResultadoBooleanoLiteral, ResultadoLambda, ResultadoNumeroLiteral}
import o3.lepifyo.tp2.ejecucion.{Interprete, Contexto}
import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InterpreteSpec extends AnyFunSpec with Matchers with BeforeAndAfter {

  val parser = ParserFactory.build

  var contexto = Contexto.contextoGlobal

  before {
    contexto = Contexto.contextoGlobal
  }

  it("el resultado de interpretar un programa con un número literal es la representación del mismo número literal") {
    val ast = parser.parsear("12")

    Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(12))
  }

  it("el resultado de interpretar un programa con un booleano literal es la representación del mismo booleano literal") {
    val ast = parser.parsear("true")

    Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(true))
  }

  describe("operaciones aritmeticas") {

    it("el resultado de interpretar un programa con la operación aritmética de suma de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 + 2")

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(3))
    }

    it("no se puede interpretar un programa con una operación de suma si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true + 2")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación de suma si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 + false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación aritmética de resta de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("2 - 1")

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
    }

    it("no se puede interpretar un programa con una operación de resta si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true - 2")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación de resta si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 - false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación aritmética de multiplicacion de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("18 * 2")

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(36))
    }

    it("no se puede interpretar un programa con una operación de multiplicacion si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true * 2")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación de multiplicacion si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 * false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación aritmética de division de dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("18 / 2")

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(9))
    }

    it("no se puede interpretar un programa con una operación de division si el primer operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("true / 2")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación de division si el segundo operador no es un numero literal ni una operación aritmetica") {
      val ast = parser.parsear("1 / false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación de divisor con denominador 0") {
      val ast = parser.parsear("1 / 0")

      an[ErrorDivisionPorCero] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con operaciones aritmeticas combinadas es la representación del resultado de las operaciones") {
      val ast = parser.parsear("18 / (4 * 3 - 3)")

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(2))
    }

  }

  describe("operaciones de comparación") {
    //FIXME hacemos dos tests por cada uno?
    it("el resultado de interpretar un programa con la operación igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 == 1")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(true))
    }

    it("el resultado de interpretar un programa con la operación igual entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true == true")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación igual con el primer operador de un tipo distinto al segundo") {
      val ast = parser.parsear("true == 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación igual con el segundo operador de un tipo distinto al primero") {
      val ast = parser.parsear("1 == false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación distinto entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 != 1")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(false))
    }

    it("el resultado de interpretar un programa con la operación distinto entre dos booleanos literales es la representación del resultado") {
      val ast = parser.parsear("true != true")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación distinto con el primer operador de un tipo distinto al segundo") {
      val ast = parser.parsear("true != 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación distinto con el segundo operador de un tipo distinto al primero") {
      val ast = parser.parsear("1 != false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación menor entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 < 3")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación menor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true < 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación menor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 < false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación mayor entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 > 3")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación mayor con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true > 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación mayor con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 > false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación menor o igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 <= 3")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(true))
    }

    it("no se puede interpretar un programa con una operación menor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true <= 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación menor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 <= false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("el resultado de interpretar un programa con la operación mayor o igual entre dos numeros literales es la representación del resultado") {
      val ast = parser.parsear("1 >= 3")

      Interprete.interpretar(ast, contexto) should equal(ResultadoBooleanoLiteral(false))
    }

    it("no se puede interpretar un programa con una operación mayor o igual con el primer operador de un tipo distinto a un numero") {
      val ast = parser.parsear("true >= 1")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

    it("no se puede interpretar un programa con una operación mayor o igual con el segundo operador de un tipo distinto a un numero") {
      val ast = parser.parsear("1 >= false")

      an[ErrorDeTipos] should be thrownBy Interprete.interpretar(ast, contexto)
    }

  }

  describe("variables") {

    it("el resultado de interpretar un programa con la declaración de una variable es la representación de declaración de variable") {
      val ast = parser.parsear("let edad = 27")

      Interprete.interpretar(ast, contexto) should equal(ResultadoAsignacionVariable())
    }

    it("el resultado de interpretar un programa con la lectura de una variable previamente declarada es la representación del valor inicial asignado a la variable") {
      val programa = """let edad = 27
                       |edad"""
      val ast = parser.parsear(programa)

      Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(27))
    }

    it("el resultado de interpretar un programa con la escritura de una variable previamente declarada es la representación de asignación de variable") {
      val programa =
        """let añoActual = 2020
          |añoActual = añoActual + 1"""
      val ast = parser.parsear(programa)

      Interprete.interpretar(ast, contexto) should equal(ResultadoAsignacionVariable())
    }

    describe("lambdas") {

      describe("declaración") {

        it("el resultado de interpretar un programa con la declaración de una lambda es la representación de la asignación de una variable") {
          val ast = parser.parsear("let siempreUno = () -> 1")

          Interprete.interpretar(ast, contexto) should equal(ResultadoAsignacionVariable())
        }

        it("el resultado de interpretar un programa con la declaración de una lambda con múltiples líneas es la representación de la asignación de una variable") {
          val programa =
            """let siempreUno = () -> {
              | let x = 1
              | x
              |}"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoAsignacionVariable())
        }

      }

      describe("aplicación") {

        it("el resultado de interpretar un programa con la aplicación de una lambda es la representación del resultado de la aplicación de la lambda") {
          val ast = parser.parsear("(() -> 1)()")

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con un parámetro es la representación del resultado de la aplicación de la lambda con el argumento correspondiente") {
          val ast = parser.parsear("((x) -> x + 1)(2)")

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(3))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con más de un parámetro es la representación del resultado de la aplicación de la lambda con los argumentos correspondientes") {
          val ast = parser.parsear("((x, y) -> x + y)(3, 2)")

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(5))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda previamente asignada a una variable es la representación del resultado de la aplicación de la lambda") {
          val programa =
            """let siempreUno = () -> 1
              |siempreUno()"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con un parámetro es el resultado de la aplicación de la lambda con el argumento correspondiente") {
          val programa =
            """let masUno = (x) -> x + 1
              |masUno(2)"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(3))
        }

        it("el resultado de interpretar un programa con la aplicación de una lambda con más de un parámetro es el resultado de la aplicación de la lambda con los argumentos correspondientes") {
          val programa =
            """let suma = (x, y) -> x + y
              |suma(3, 2)"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(5))
        }

        it("en la interpretación de un programa, una lambda puede acceder una variable definida en su contexto padre") {
          val programa =
            """let var = 1
              |let lambdaQueUsaVar = () -> {
              | var + 1
              |}
              |lambdaQueUsaVar()
              |"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(2))
        }

        it("en la interpretación de un programa, una lambda definida con un parámetro de igual nombre que una variable definida en su contexto padre, oculta esta última y utiliza el parámetro de la lambda") {
          val programa =
            """let x = 1
              |let lambdaQueUsaXDeParametro = (x) -> {
              | x + 1
              |}
              |lambdaQueUsaXDeParametro(5)
              |"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(6))
        }

        it("en la interpretación de un programa, una lambda definida con un parámetro de igual nombre que una variable definida en su contexto padre, oculta esta última en su contexto pero deja de ocultarla luego de su ejecución") {
          val programa =
            """let x = 1
              |let lambdaQueUsaXDeParametro = (x) -> {
              | x + 1
              |}
              |x
              |"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
        }

        it("en la interpretación de un programa, una lambda puede modificar una variable definida en su contexto padre") {
          val programa =
            """let x = 1
              |let lambdaQueModificaX = (y) -> {
              | x = y
              |}
              |lambdaQueModificaX(5)
              |x
              |"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(5))
        }

        it("en la interpretación de un programa, una lambda puede definir y modificar variables en su contexto") {
          val programa =
            """let devolverY = () -> {
              | let y = 1
              | y = y + 1
              | y
              |}
              |devolverY()
              |"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(2))
        }

        it("en la interpretación de un programa, las variables definidas en el contexto de una lambda no son accesibles luego de su ejecución") {
          val programa =
            """|let devolverX = () -> {
               | let x = 1
               | x
               |}
               |devolverX()
               |x
               |"""
          val ast = parser.parsear(programa)

          a[RuntimeException] should be thrownBy Interprete.interpretar(ast, contexto)
        }

        it("en la interpretación de un programa, una lambda que define una variable ya definida en su contexto padre, oculta esta última en su contexto pero deja de ocultarla luego de su ejecución") {
            val programa =
              """let var = 1
                |let lambdaQueDefineVar = () -> {
                | let var = 5
                | var
                |}
                |var
                |"""
            val ast = parser.parsear(programa)

            Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
          }

        it("en la interpretación de un programa, anidar lambdas genera que cada lambda tenga su contexto, teniendo como contexto padre al contexto en donde fue definida") {
          val programa =
            """let x = 1
              |let sumar = (x) -> (y) -> x + y
              |sumar(2)(x)"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(3))
        }

        ignore("en la interpretación de un programa, anidar lambdas genera que cada una de ellas interprete el valor de las variables según su propio contexto, resultando irrelevante desde qué contexto se la invoca") {
          val programa =
            """let x = 1
              |let devolverX = () -> x
              |let otroDevolverX = () -> {
              |  let x = 2
              |  devolverX()
              |}
              |otroDevolverX()"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(1))
        }

        it("en la interpretación de un programa, se pueden acceder a las variables definidas en cualquier contexto que sea ancestro del contexto de la lambda que se aplica") {
          val programa =
            """let x = 1
              |let sumarAX = (y) -> {
              |  ((y) -> x + y - 1)(y + 1)
              |}
              |sumarAX(2)"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(3))
        }

        ignore("en la interpretación de un programa, los argumentos de una lambda se evalúan de izquierda a derecha") {
          val programa =
            """let x = 1
              |let modificarXYDevolverSucesor = (y) -> {
              |  x = y
              |  y + 1
              |}
              |let sumar = (x) -> (y) -> x + y
              |let aplicarA = (f, x) -> {
              |  f(x)
              |}
              |aplicarA(sumar(modificarXYDevolverSucesor(2)), x)"""
          val ast = parser.parsear(programa)

          Interprete.interpretar(ast, contexto) should equal(ResultadoNumeroLiteral(5))
        }

        }

      }

    }

}
