package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.exception.ErrorDivisionPorCero
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class DivisionAST(operador1: ElementoAST, operador2: ElementoAST) extends OperacionAritmeticaAST {

  def operacion: (Int, Int) => ElementoAST = (x, y) => {
    y match {
      case 0 => throw ErrorDivisionPorCero()
      case _ => NumeroLiteral(x / y)
    }
  }

}
