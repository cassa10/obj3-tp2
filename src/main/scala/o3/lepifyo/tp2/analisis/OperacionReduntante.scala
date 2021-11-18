package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.analisis.NivelGravedad.Tipo
import o3.lepifyo.tp2.ast.operaciones.{DivisionAST, MultiplicacionAST, RestaAST, SumaAST}
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

case class OperacionReduntante() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Advertencia

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case SumaAST(_, NumeroLiteral(0)) | SumaAST(NumeroLiteral(0), _) =>
        Option(Problema(MensajeProblema.SumaCero, nivelGravedad, elementoAST))
      case RestaAST(_, NumeroLiteral(0)) =>
        Option(Problema(MensajeProblema.RestarCero, nivelGravedad, elementoAST))
      case MultiplicacionAST(_, NumeroLiteral(1)) | MultiplicacionAST(NumeroLiteral(1), _) =>
        Option(Problema(MensajeProblema.MultiplicarUno, nivelGravedad, elementoAST))
      case DivisionAST(_, NumeroLiteral(1)) =>
        Option(Problema(MensajeProblema.DividirUno, nivelGravedad, elementoAST))
      case _ => None
    }
  }

}