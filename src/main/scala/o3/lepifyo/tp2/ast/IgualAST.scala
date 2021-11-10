package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.ast.exception.ErrorDeTipos

case class IgualAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST {

  override def evaluarse(): ElementoAST = {
    operador1.evaluarse() match {
      case NumeroLiteral(valor1) => aplicarConOperador2(valor1, operador2)
      case _ => throw ErrorDeTipos()
    }
  }

  def aplicarConOperador2(valor: Int, operador: ElementoAST) = {
    operador.evaluarse() match {
      case NumeroLiteral(valor2) => BooleanoLiteral(valor == valor2)
      case _ => throw ErrorDeTipos()
    }
  }
}
