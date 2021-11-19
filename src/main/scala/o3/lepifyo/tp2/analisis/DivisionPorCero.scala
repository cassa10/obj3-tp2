package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.analisis.NivelGravedad.Tipo
import o3.lepifyo.tp2.ast.operaciones.DivisionAST
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class DivisionPorCero() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Error

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case DivisionAST(_, NumeroLiteral(0)) => Option(Problema(MensajeProblema.DivisionPorCero, nivelGravedad, elementoAST))
      case _ => None
    }
  }

}
