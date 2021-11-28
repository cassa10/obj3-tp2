package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class LambdaAST(parametros: List[String], cuerpo: List[ElementoAST]) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion = {
    cuerpo.last match {
      case LambdaAST(parametros2, cuerpo2) => LambdaAST(parametros2, cuerpo2).evaluarse(new Contexto(contexto))
      case _ => ResultadoLambda(parametros, cuerpo)
    }
//    ResultadoLambda(parametros, cuerpo)
  }
}
