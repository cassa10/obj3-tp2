package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}

case class Variable(nombre: String, elementoAST: ElementoAST) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = new ResultadoBooleanoLiteral(true) //agregar nuevo tipo de retorno¿?

  override def analizarse(reglas: List[Regla]): List[Problema] = List()

  override def toString: String = ""

}
