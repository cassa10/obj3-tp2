package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

trait Regla {

  val nivelGravedad: NivelGravedad.Tipo

  def apply(astElem: ElementoAST): Option[Problema]

}
