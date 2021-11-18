package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.{BooleanoLiteral, ElementoAST}

case class MenorIgualAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionAritmeticaAST {

  def operacion: (Int, Int) => ElementoAST = (x, y) => BooleanoLiteral(x <= y)

}
