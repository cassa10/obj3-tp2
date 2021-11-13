package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

class Regla(definicion: ElementoAST => Option[Problema]) {
  def apply(astElem: ElementoAST): Option[Problema] = definicion(astElem)
}
