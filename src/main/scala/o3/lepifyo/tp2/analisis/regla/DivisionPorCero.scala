package o3.lepifyo.tp2.analisis.regla

import NivelGravedad.Tipo
import o3.lepifyo.tp2.analisis.{MensajeProblema, Problema}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.literales.NumeroLiteralAST
import o3.lepifyo.tp2.ast.operaciones.DivisionAST

case class DivisionPorCero() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Error

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case DivisionAST(_, NumeroLiteralAST(0)) => identificarProblema(MensajeProblema.DivisionPorCero, nivelGravedad, elementoAST)
      case _ => None
    }
  }

}
