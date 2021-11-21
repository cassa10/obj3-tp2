package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Memoria
import o3.lepifyo.tp2.resultado.ResultadoExpresion

case class VariableAST(nombre: String) extends ElementoAST {
  override def evaluarse(): ResultadoExpresion = Memoria.obtenerVariable(nombre)

  override def analizarse(reglas: List[Regla]): List[Problema] = ???
}
