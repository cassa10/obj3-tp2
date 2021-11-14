package o3.lepifyo.tp2

import o3.lepifyo.parser.ParserFactory
import o3.lepifyo.tp2.analisis.{Advertencia, Analizador, Error, Problema, Regla}
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}
import o3.lepifyo.tp2.ast.operaciones.numeros.{DivisionAST, MultiplicacionAST, RestaAST, SumaAST}
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

    describe("Análisis de programas con división por cero") {

      it("Se encuentra un error al analizar un programa que contiene una división por cero") {
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

      it("Se encuentran errores al analizar un programa que contiene mas de una división por cero") {
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

    describe("Análisis de programas con operaciones reduntantes") {

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se sume cero") {
        val ast = parser.parsear("1 + 0")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case SumaAST(_, NumeroLiteral(0)) => Option(Problema("Sumar cero es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Sumar cero es redundate", Advertencia(), SumaAST(NumeroLiteral(1), NumeroLiteral(0)))
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a cero le sea sumado otro número") {
        val ast = parser.parsear("0 + 1")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case SumaAST(NumeroLiteral(0), _) => Option(Problema("Sumar cero es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Sumar cero es redundate", Advertencia(), SumaAST(NumeroLiteral(0), NumeroLiteral(1)))
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se reste cero") {
        val ast = parser.parsear("1 - 0")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case RestaAST(_, NumeroLiteral(0)) => Option(Problema("Restar cero es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Restar cero es redundate", Advertencia(), RestaAST(NumeroLiteral(1), NumeroLiteral(0)))
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se multiplique por uno") {
        val ast = parser.parsear("2 * 1")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case MultiplicacionAST(_, NumeroLiteral(1)) => Option(Problema("Multiplicar por uno es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Multiplicar por uno es redundate", Advertencia(), MultiplicacionAST(NumeroLiteral(2), NumeroLiteral(1)))
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente a uno se lo multiplique por otro número") {
        val ast = parser.parsear("1 * 2")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case MultiplicacionAST(NumeroLiteral(1), _) => Option(Problema("Multiplicar por uno es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Multiplicar por uno es redundate", Advertencia(), MultiplicacionAST(NumeroLiteral(1), NumeroLiteral(2)))
      }

      it("Se encuentra una advertencia al analizar un programa en el que explícitamente se divida por uno") {
        val ast = parser.parsear("2 / 1")
        val definicion = (elemAst : ElementoAST) => {
          elemAst match {
            case DivisionAST(_, NumeroLiteral(1)) => Option(Problema("Dividir por uno es redundate", Advertencia(), elemAst))
            case _ => None
          }
        }
        val reglas = List(new Regla(definicion))
        val problemasEncontrados = analizador.analizar(ast, reglas)

        problemasEncontrados should have size 1
        problemasEncontrados.head shouldBe Problema("Dividir por uno es redundate", Advertencia(), DivisionAST(NumeroLiteral(2), NumeroLiteral(1)))
      }

    }

  }
}
