package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class NumeroLiteral(valor: Int) extends ElementoAST {

  override def evaluarse(): NumeroLiteral = this

  def analizarse(reglas: List[Regla]): List[Option[Problema]] = reglas.map(regla => regla.apply(this))

  override def toString: String = valor.toString

}
