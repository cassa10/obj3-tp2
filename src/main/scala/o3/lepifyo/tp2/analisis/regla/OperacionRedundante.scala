package o3.lepifyo.tp2.analisis.regla

import NivelGravedad.Tipo
import o3.lepifyo.tp2.analisis.{MensajeProblema, Problema}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.literales.NumeroLiteralAST
import o3.lepifyo.tp2.ast.operaciones.{DivisionAST, MultiplicacionAST, RestaAST, SumaAST}

case class OperacionRedundante() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Advertencia

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case SumaAST(_, NumeroLiteralAST(0)) | SumaAST(NumeroLiteralAST(0), _) =>
        identificarProblema(MensajeProblema.SumaCero, nivelGravedad, elementoAST)
      case RestaAST(_, NumeroLiteralAST(0)) =>
        identificarProblema(MensajeProblema.RestarCero, nivelGravedad, elementoAST)
      case MultiplicacionAST(_, NumeroLiteralAST(1)) | MultiplicacionAST(NumeroLiteralAST(1), _) =>
        identificarProblema(MensajeProblema.MultiplicarUno, nivelGravedad, elementoAST)
      case DivisionAST(_, NumeroLiteralAST(1)) =>
        identificarProblema(MensajeProblema.DividirUno, nivelGravedad, elementoAST)
      case _ => None
    }
  }

}
