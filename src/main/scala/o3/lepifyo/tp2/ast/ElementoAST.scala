package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}

trait ElementoAST {

  def evaluarse(): ElementoAST

  def analizarse(reglas: List[Regla]): List[Option[Problema]]

}
