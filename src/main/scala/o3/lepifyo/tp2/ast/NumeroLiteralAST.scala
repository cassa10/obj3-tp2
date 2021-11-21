package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.resultado.ResultadoNumeroLiteral

case class NumeroLiteralAST(valor: Int) extends ElementoAST {

  override def evaluarse(): ResultadoNumeroLiteral = ResultadoNumeroLiteral(valor)

  def analizarse(reglas: List[Regla]): List[Problema] =
    reglas.map(regla => regla.apply(this))
      .filter(_.isDefined)
      .map(_.get)

}
