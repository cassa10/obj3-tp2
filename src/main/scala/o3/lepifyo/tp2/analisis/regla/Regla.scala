package o3.lepifyo.tp2.analisis.regla

import o3.lepifyo.tp2.analisis.Problema
import o3.lepifyo.tp2.analisis.regla.NivelGravedad.Tipo
import o3.lepifyo.tp2.ast.ElementoAST

trait Regla {

  val nivelGravedad: NivelGravedad.Tipo

  def apply(astElem: ElementoAST): Option[Problema]

  def identificarProblema(mensajeProblema: String, nivelGravedad: Tipo, elementoAST: ElementoAST) =
    Option(Problema(mensajeProblema, nivelGravedad, elementoAST))

}
