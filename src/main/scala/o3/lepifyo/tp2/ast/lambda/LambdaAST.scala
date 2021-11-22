package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.parser.ParserFactory.Expresion
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class LambdaAST(parametros: List[String], cuerpo: List[Expresion]) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = ResultadoLambda(parametros, cuerpo)
}
