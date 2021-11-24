package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.{ResultadoAsignacionVariable, ResultadoExpresion}

case class AsignacionAST(nombre: String, valorNuevo: ElementoAST) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion = {
    contexto.actualizarVariable(nombre, valorNuevo.evaluarse(contexto))
    ResultadoAsignacionVariable()
  }

}
