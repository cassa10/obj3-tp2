package o3.lepifyo.tp2.resultado

import o3.lepifyo.tp2.ast.ElementoAST

case class ResultadoLambda(parametros: List[String], cuerpo: List[ElementoAST]) extends ResultadoExpresion {

  override def toString: String = "lambda"

}
