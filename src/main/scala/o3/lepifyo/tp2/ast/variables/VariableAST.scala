package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.ResultadoExpresion

case class VariableAST(nombre: String) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion = contexto.obtenerValorVariable(nombre)
  
}
