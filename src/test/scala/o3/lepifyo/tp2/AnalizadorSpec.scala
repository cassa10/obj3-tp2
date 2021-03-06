package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.parser.ParserFactory.Programa
import o3.lepifyo.tp2.analisis.regla.NivelGravedad
import o3.lepifyo.tp2.analisis.{Analizador, MensajeProblema, Problema}
import o3.lepifyo.tp2.ast.operaciones.{DivisionAST, MultiplicacionAST, RestaAST, SumaAST}
import o3.lepifyo.tp2.ast.variables.{DeclaracionVariableAST, VariableAST}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.lambda.LambdaAST
import o3.lepifyo.tp2.ast.literales.NumeroLiteralAST
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AnalizadorSpec extends AnyFunSpec with Matchers {

  private val parser = ParserFactory.build

  describe("análisis de programa") {

    it("No se encuentran problemas al analizar un programa correcto") {
      val ast = parser.parsear("1 + 1")

      val problemasEncontrados = Analizador.analizar(ast)

      problemasEncontrados shouldBe empty
    }

    describe("Análisis de programas con división por cero") {

      it("Se encuentra un error al analizar un programa que contiene una división por cero") {
        val ast = parser.parsear("1 / 0")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.DivisionPorCero,
          NivelGravedad.Error,
          DivisionAST(NumeroLiteralAST(1), NumeroLiteralAST(0))
        )
      }

      it("Se encuentran errores al analizar un programa que contiene mas de una división por cero") {
        val ast = parser.parsear("100 + 5 \n 100 + (1 / 0) / 20 \n (50 / 0)")

        val List(problema1, problema2) = Analizador.analizar(ast)

        problema1 shouldBe Problema(MensajeProblema.DivisionPorCero, NivelGravedad.Error, DivisionAST(NumeroLiteralAST(1), NumeroLiteralAST(0)))
        problema2 shouldBe Problema(MensajeProblema.DivisionPorCero, NivelGravedad.Error, DivisionAST(NumeroLiteralAST(50), NumeroLiteralAST(0)))
      }

    }

    describe("Análisis de programas con operaciones redundantes") {

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se sume cero") {
        val ast = parser.parsear("1 + 0")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.SumaCero,
          NivelGravedad.Advertencia,
          SumaAST(NumeroLiteralAST(1), NumeroLiteralAST(0))
        )
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a cero le sea sumado otro número") {
        val ast = parser.parsear("0 + 1")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.SumaCero,
          NivelGravedad.Advertencia,
          SumaAST(NumeroLiteralAST(0), NumeroLiteralAST(1))
        )
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se reste cero") {
        val ast = parser.parsear("1 - 0")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.RestarCero,
          NivelGravedad.Advertencia,
          RestaAST(NumeroLiteralAST(1), NumeroLiteralAST(0))
        )
     }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se multiplique por uno") {
        val ast = parser.parsear("2 * 1")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.MultiplicarUno,
          NivelGravedad.Advertencia,
          MultiplicacionAST(NumeroLiteralAST(2), NumeroLiteralAST(1))
        )
     }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a uno se lo multiplique por otro número") {
        val ast = parser.parsear("1 * 2")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.MultiplicarUno,
          NivelGravedad.Advertencia,
          MultiplicacionAST(NumeroLiteralAST(1), NumeroLiteralAST(2))
        )
    }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se divida por uno") {
        val ast = parser.parsear("2 / 1")

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.DividirUno,
          NivelGravedad.Advertencia,
          DivisionAST(NumeroLiteralAST(2), NumeroLiteralAST(1))
        )
     }

      it("Se encuentra un error al analizar un programa en el que se declara más de una vez la misma variable") {
        val programa =
          """let var = 1
            |let var = 2"""
        val ast = parser.parsear(programa)

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.VariableDuplicada("var"),
          NivelGravedad.Error,
          DeclaracionVariableAST("var", NumeroLiteralAST(2))
        )
      }

    }

    describe("Análisis de programas con problemas en lambdas") {

      it("Se encuentra un error al analizar un programa en el que se declara una lambda cuya ultima instruccion no es una expresion") {
        val programa =
          """let lambda = () -> {
            | let x = 2
            |}"""
        val ast = parser.parsear(programa)

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.LambdaNoRetornaExpresion,
          NivelGravedad.Error,
          LambdaAST(List.empty, List(DeclaracionVariableAST("x", NumeroLiteralAST(2))))
        )
      }

      it("Se encuentra un error al analizar un programa en el que se declara una lambda con mas de un parametro con el mismo nombre") {
        val programa =
          """let otraLambda = (n, n) -> {
            | n
            |}"""
        val ast = parser.parsear(programa)

        assertarQueElUnicoProblemaCumple(ast,
          MensajeProblema.NombreDeParametrosRepetido,
          NivelGravedad.Error,
          LambdaAST(List("n", "n"), List(VariableAST("n")))
        )
      }

    }

  }

  private def assertarQueElUnicoProblemaCumple(ast: Programa,
                                               mensajeEsperado: String,
                                               nivelGravedadEsperado: NivelGravedad.Value,
                                               astEsperado: ElementoAST
                                              ): Unit = {
    val List(Problema(mensaje, nivelGravedad, astConProblema)) = Analizador.analizar(ast)

    mensaje shouldBe mensajeEsperado
    nivelGravedad shouldBe nivelGravedadEsperado
    astConProblema shouldBe astEsperado
  }
}
