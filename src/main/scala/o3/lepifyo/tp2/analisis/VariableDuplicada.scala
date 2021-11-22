package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.analisis.NivelGravedad.Tipo
import o3.lepifyo.tp2.ast.variables.DeclaracionVariableAST
import o3.lepifyo.tp2.ast.ElementoAST

case class VariableDuplicada() extends Regla {

  var cantidadDeclaradas: Map[String, Int] = Map.empty

  override val nivelGravedad: Tipo = NivelGravedad.Error

  def apply(elementoAST: ElementoAST): Option[Problema] = elementoAST match {
    case DeclaracionVariableAST(nombre, _) =>
      cantidadDeclaradas += (nombre -> (cantidadDeclaradas.getOrElse(nombre, 0) + 1))
      if (cantidadDeclaradas(nombre) > 1) Option(Problema(MensajeProblema.VariableDuplicada(nombre), nivelGravedad, elementoAST)) else None
    case _ => None
  }

}