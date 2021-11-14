package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.operaciones.numeros.DivisionAST
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class DivisionPorCero() extends Regla {

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case DivisionAST(_, NumeroLiteral(0)) => Option(Problema("No se puede dividir por cero", Error(), elementoAST))
      case _ => None
    }
  }

}
