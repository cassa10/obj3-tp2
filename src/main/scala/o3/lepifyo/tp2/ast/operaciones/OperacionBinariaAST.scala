package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.ResultadoExpresion

abstract class OperacionBinariaAST extends ElementoAST {

  def operador1: ElementoAST

  def operador2: ElementoAST

  def operacion: (Int, Int) => ResultadoExpresion

  def evaluarse(): ResultadoExpresion

}

object OperacionBinariaAST {
  def unapply(ast: ElementoAST): Option[(ElementoAST, ElementoAST)] = ast match {
    case operacionBinaria : OperacionBinariaAST => Some(operacionBinaria.operador1, operacionBinaria.operador2)
    case _ => None
  }
}
