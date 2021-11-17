package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.parser.ParserFactory.Programa
import o3.lepifyo.tp2.analisis.{Analizador, DivisionPorCero, NivelGravedad, OperacionReduntante, Problema}
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}
import o3.lepifyo.tp2.ast.operaciones.numeros.{DivisionAST, MultiplicacionAST, RestaAST, SumaAST}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AnalizadorSpec extends AnyFunSpec with Matchers {

  private val parser = ParserFactory.build
  private val analizador = new Analizador(List(new DivisionPorCero, new OperacionReduntante))

  describe("análisis de programa") {

    it("No se encuentran problemas al analizar un programa correcto") {
      val ast = parser.parsear("1 + 1")

      val problemasEncontrados = analizador.analizar(ast)

      problemasEncontrados shouldBe empty
    }

    describe("Análisis de programas con división por cero") {

      it("Se encuentra un error al analizar un programa que contiene una división por cero") {
        val ast = parser.parsear("1 / 0")

        assertarQueElUnicoProblemaCumple(ast,
          "No se puede dividir por cero",
          NivelGravedad.Error,
          DivisionAST(NumeroLiteral(1), NumeroLiteral(0))
        )
      }

      it("Se encuentran errores al analizar un programa que contiene mas de una división por cero") {
        val ast = parser.parsear("100 + 5 \n 100 + (1 / 0) / 20 \n (50 / 0)")

        val List(problema1, problema2) = analizador.analizar(ast)

        problema1 shouldBe Problema("No se puede dividir por cero", NivelGravedad.Error, DivisionAST(NumeroLiteral(1), NumeroLiteral(0)))
        problema2 shouldBe Problema("No se puede dividir por cero", NivelGravedad.Error, DivisionAST(NumeroLiteral(50), NumeroLiteral(0)))
      }

    }

    describe("Análisis de programas con operaciones redundantes") {

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se sume cero") {
        val ast = parser.parsear("1 + 0")

        assertarQueElUnicoProblemaCumple(ast,
          "Sumar cero es redundate",
          NivelGravedad.Advertencia,
          SumaAST(NumeroLiteral(1), NumeroLiteral(0))
        )
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a cero le sea sumado otro número") {
        val ast = parser.parsear("0 + 1")

        assertarQueElUnicoProblemaCumple(ast,
          "Sumar cero es redundate",
          NivelGravedad.Advertencia,
          SumaAST(NumeroLiteral(0), NumeroLiteral(1))
        )
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se reste cero") {
        val ast = parser.parsear("1 - 0")

        assertarQueElUnicoProblemaCumple(ast,
          "Restar cero es redundate",
          NivelGravedad.Advertencia,
          RestaAST(NumeroLiteral(1), NumeroLiteral(0))
        )
     }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se multiplique por uno") {
        val ast = parser.parsear("2 * 1")

        assertarQueElUnicoProblemaCumple(ast,
          "Multiplicar por uno es redundate",
          NivelGravedad.Advertencia,
          MultiplicacionAST(NumeroLiteral(2), NumeroLiteral(1))
        )
     }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a uno se lo multiplique por otro número") {
        val ast = parser.parsear("1 * 2")

        assertarQueElUnicoProblemaCumple(ast,
          "Multiplicar por uno es redundate",
          NivelGravedad.Advertencia,
          MultiplicacionAST(NumeroLiteral(1), NumeroLiteral(2))
        )
    }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se divida por uno") {
        val ast = parser.parsear("2 / 1")

        assertarQueElUnicoProblemaCumple(ast,
          "Dividir por uno es redundate",
          NivelGravedad.Advertencia,
          DivisionAST(NumeroLiteral(2), NumeroLiteral(1))
        )
     }

    }

  }

  private def assertarQueElUnicoProblemaCumple(ast: Programa,
                                               mensajeEsperado: String,
                                               nivelGravedadEsperado: NivelGravedad.Value,
                                               astEsperado: ElementoAST
                                              ): Unit = {
    val List(Problema(mensaje, nivelGravedad, astConProblema)) = analizador.analizar(ast)

    mensaje shouldBe mensajeEsperado
    nivelGravedad shouldBe nivelGravedadEsperado
    astConProblema shouldBe astEsperado
  }
}
