package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}
import o3.lepifyo.tp2.ast.ElementoAST

case class MayorAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionAritmeticaAST {

  def operacion: (Int, Int) => ResultadoExpresion = (x, y) => ResultadoBooleanoLiteral(x > y)

}
