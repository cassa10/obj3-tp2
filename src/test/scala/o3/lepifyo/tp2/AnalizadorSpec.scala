package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.analisis.{Analizador, Error, Problema, Regla}
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}
import o3.lepifyo.tp2.ast.operaciones.numeros.DivisionAST
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
      val definicion = (elemAst : ElementoAST) => {
        elemAst match {
          case DivisionAST(_, NumeroLiteral(0)) => Option(Problema("No se puede dividir por cero", Error(), elemAst))
          case _ => None
        }
      }
      val reglas = List(new Regla(definicion))
      val problemasEncontrados = analizador.analizar(ast, reglas)

      problemasEncontrados shouldBe empty
    }

    it("Se encuentra un error al analizar un programa que contiene una división por cero con una descripcion") {
      val ast = parser.parsear("1 / 0")
      val definicion = (elemAst : ElementoAST) => {
        elemAst match {
          case DivisionAST(_, NumeroLiteral(0)) => Option(Problema("No se puede dividir por cero", Error(), elemAst))
          case _ => None
        }
      }
      val reglas = List(new Regla(definicion))
      val problemasEncontrados = analizador.analizar(ast, reglas)

      problemasEncontrados should have size 1
      problemasEncontrados.head shouldBe Problema("No se puede dividir por cero", Error(), DivisionAST(NumeroLiteral(1), NumeroLiteral(0)))
    }

    it("Se encuentran errores al analizar un programa que contiene mas de una división por cero donde cada una tiene su descripcion") {
      val ast = parser.parsear("100 + 5 \n 100 + (1 / 0) * 1 / 20 \n (50 / 0)")
      val definicion = (elemAst : ElementoAST) => {
        elemAst match {
          case DivisionAST(_, NumeroLiteral(0)) => Option(Problema("No se puede dividir por cero", Error(), elemAst))
          case _ => None
        }
      }
      val reglas = List(new Regla(definicion))
      val problemasEncontrados = analizador.analizar(ast, reglas)

      problemasEncontrados should have size 2
      problemasEncontrados.head shouldBe Problema("No se puede dividir por cero", Error(), DivisionAST(NumeroLiteral(1), NumeroLiteral(0)))
      problemasEncontrados(1) shouldBe Problema("No se puede dividir por cero", Error(), DivisionAST(NumeroLiteral(50), NumeroLiteral(0)))
    }

  }
}
