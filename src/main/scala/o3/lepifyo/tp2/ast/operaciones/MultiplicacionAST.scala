package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoNumeroLiteral}
import o3.lepifyo.tp2.ast.ElementoAST

case class MultiplicacionAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionAritmeticaAST {

  def operacion: (Int, Int) => ResultadoExpresion = (x, y) => ResultadoNumeroLiteral(x * y)

}
