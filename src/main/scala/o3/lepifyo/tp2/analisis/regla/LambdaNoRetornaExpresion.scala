package o3.lepifyo.tp2.analisis.regla

import o3.lepifyo.tp2.analisis.regla.NivelGravedad.Tipo
import o3.lepifyo.tp2.analisis.{MensajeProblema, Problema}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.lambda.LambdaAST
import o3.lepifyo.tp2.ast.variables.{AsignacionAST, DeclaracionVariableAST}

case class LambdaNoRetornaExpresion() extends Regla {
  override val nivelGravedad: Tipo = NivelGravedad.Error

  override def apply(elementoAST: ElementoAST): Option[Problema] = elementoAST match {
    case LambdaAST(_, cuerpo) =>
      cuerpo.last match {
        case AsignacionAST(_, _) | DeclaracionVariableAST(_, _) => identificarProblema(MensajeProblema.LambdaNoRetornaExpresion, nivelGravedad, elementoAST)
        case _ => None
      }
    case _ => None
  }
}
