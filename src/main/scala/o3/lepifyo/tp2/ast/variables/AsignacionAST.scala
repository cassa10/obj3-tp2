package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Memoria
import o3.lepifyo.tp2.resultado.{ResultadoAsignacionVariable, ResultadoExpresion}

case class AsignacionAST(nombre: String, valorNuevo: ElementoAST) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = {
    Memoria.actualizarVariable(nombre, valorNuevo.evaluarse())
    ResultadoAsignacionVariable()
  }

  override def analizarse(reglas: List[Regla]): List[Problema] =
    reglas.map(regla => regla.apply(this.asInstanceOf[ElementoAST]))
      .filter(_.isDefined)
      .map(_.get)

}
