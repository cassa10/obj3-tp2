package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class LambdaAST(parametros: List[String], cuerpo: List[ElementoAST]) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion =
    ResultadoLambda(parametros, cuerpo, new Contexto(contexto))
}
