package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class NumeroLiteral(valor: Int) extends ElementoAST {

  override def evaluarse(): NumeroLiteral = this

  //FIXME: Analizar si permitir que alguna regla ne permitan escribir algun numero literal?
  def analizarse(reglas: List[Regla]): List[Option[Problema]] = List(None)

}
