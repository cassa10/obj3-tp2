package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

trait OperacionBinariaNumerosAST {
  def operador1: ElementoAST
  def operador2: ElementoAST
  def operacion: (Int, Int) => ElementoAST

  def evaluarse(): ElementoAST = {
    operador1.evaluarse() match {
      case NumeroLiteral(valor1) => aplicarOperacion(operacion, valor1, operador2)
      case _ => throw ErrorDeTipos()
    }
  }

  def aplicarOperacion(operacion: (Int, Int) => ElementoAST, valor: Int, operador: ElementoAST): ElementoAST = {
    operador.evaluarse() match {
      case NumeroLiteral(valor2) => operacion(valor, valor2)
      case _ => throw ErrorDeTipos()
    }
  }

}
