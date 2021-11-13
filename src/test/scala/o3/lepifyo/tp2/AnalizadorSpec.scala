package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.analisis.Analizador
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AnalizadorSpec extends AnyFunSpec with Matchers {

  val parser = {
    val parserFactory = new ParserFactory
    parserFactory.build
  }

  val analizador = new Analizador

  describe("análisis de programa") {

    it("No se encuentran problemas al analizar un programa correcto") {
      val ast = parser.parsear("1 + 1")

      val problemasEncontrados = analizador.analizar(ast)

      problemasEncontrados shouldBe empty
    }

    it("Se encuentra un error al analizar un programa que contiene una división por cero con una descripcion") {
      val ast = parser.parsear("1 / 0")

      val problemasEncontrados = analizador.analizar(ast)

      problemasEncontrados should have size 1
    }

  }
}
