package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Memoria
import o3.lepifyo.tp2.resultado.{ResultadoAsignacionVariable, ResultadoExpresion}

case class DeclaracionVariableAST(nombre: String, valorInicial: ElementoAST) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = {
    Memoria.guardarVariable(nombre, valorInicial.evaluarse())
    ResultadoAsignacionVariable()
  }

}
