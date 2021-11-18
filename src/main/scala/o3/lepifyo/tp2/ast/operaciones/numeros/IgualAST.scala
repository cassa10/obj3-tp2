package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.{BooleanoLiteral, ElementoAST}

case class IgualAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionDeComparacionAST {

  def operacion: (AnyVal, AnyVal) => ElementoAST = (x, y) => BooleanoLiteral(x == y)

}
