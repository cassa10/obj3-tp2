package o3.lepifyo.tp2.resultado

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto

case class ResultadoLambda(parametros: List[String], cuerpo: List[ElementoAST], contexto: Contexto) extends ResultadoExpresion {

  override def toString: String = "lambda"

}
