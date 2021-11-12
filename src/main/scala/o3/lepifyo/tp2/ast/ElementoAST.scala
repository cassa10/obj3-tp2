package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.Problema

trait ElementoAST {

  def evaluarse(): ElementoAST

  def analizarse(): Option[Problema] = None

}
