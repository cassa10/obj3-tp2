package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

trait Regla {

  def apply(astElem: ElementoAST): Option[Problema]

}
