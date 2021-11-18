package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.analisis.{Problema, Regla}

abstract class OperacionBinariaAST extends ElementoAST {

  def operador1: ElementoAST

  def operador2: ElementoAST

  def operacion: (Int, Int) => ElementoAST

  def evaluarse(): ElementoAST

  def analizarse(reglas: List[Regla]): List[Problema] = {
    reglas.map(regla => regla.apply(this.asInstanceOf[ElementoAST]))
      .filter(_.isDefined)
      .map(_.get)
      .concat(operador1.analizarse(reglas))
      .concat(operador2.analizarse(reglas))
  }

}
