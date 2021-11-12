package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.analisis.Problema
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class DivisionAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST with OperacionBinariaNumerosAST {
  def operacion: (Int, Int) => ElementoAST = (x, y) => NumeroLiteral(x / y)

  override def analizarse(): Option[Problema] = {
    operador2.evaluarse() match {
      case NumeroLiteral(0) => Option(new Problema)
      case _ => None
    }
  }
}
