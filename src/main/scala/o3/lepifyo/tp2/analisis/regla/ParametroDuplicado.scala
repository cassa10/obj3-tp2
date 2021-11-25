package o3.lepifyo.tp2.analisis.regla
import o3.lepifyo.tp2.analisis.regla.NivelGravedad.Tipo
import o3.lepifyo.tp2.analisis.{MensajeProblema, Problema}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.lambda.LambdaAST

case class ParametroDuplicado() extends Regla {
  override val nivelGravedad: Tipo = NivelGravedad.Error

  override def apply(elementoAST: ElementoAST): Option[Problema] = elementoAST match {
    case LambdaAST(parametros, _) => if (parametros.toSet.size == parametros.size) None else identificarProblema(MensajeProblema.NombreDeParametrosRepetido, nivelGravedad, elementoAST)
    case _ => None
  }
}
