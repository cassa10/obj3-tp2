package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

// TODO: Deberíamos lanzar una excepcion en la division por cero
case class DivisionAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionBinariaNumerosAST {
  def operacion: (Int, Int) => ElementoAST = (x, y) => NumeroLiteral(x / y)
}
