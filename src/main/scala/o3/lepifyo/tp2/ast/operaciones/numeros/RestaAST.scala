package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class RestaAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST with OperacionBinariaNumerosAST {
  def operacion: (Int, Int) => ElementoAST = (x, y) => NumeroLiteral(x - y)
}
