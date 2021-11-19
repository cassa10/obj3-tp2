package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}
import o3.lepifyo.tp2.ast.ElementoAST

case class DistintoAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionDeComparacionAST {

  def operacion: (AnyVal, AnyVal) => ResultadoExpresion = (x, y) => ResultadoBooleanoLiteral(x != y)

}
