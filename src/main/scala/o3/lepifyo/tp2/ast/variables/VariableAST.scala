package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.{ElementoAST, Memoria}
import o3.lepifyo.tp2.resultado.ResultadoExpresion

case class VariableAST(nombre: String) extends ElementoAST {
  override def evaluarse(): ResultadoExpresion = Memoria.buscar(nombre)

  override def analizarse(reglas: List[Regla]): List[Problema] = ???
}
