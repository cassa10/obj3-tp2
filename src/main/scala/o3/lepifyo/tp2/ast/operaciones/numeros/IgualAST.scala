package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.{BooleanoLiteral, ElementoAST}

// TODO: Contemplar a la operacion igual tambien para booleanos
case class IgualAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionBinariaNumerosAST {
  def operacion: (Int, Int) => ElementoAST = (x, y) => BooleanoLiteral(x == y)
}
