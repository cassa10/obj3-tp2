package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.parser.ParserFactory.Expresion
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.variables.VariableAST
import o3.lepifyo.tp2.ejecucion.{Interprete, Contexto}
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class AplicacionLambdaAST(funcion: Expresion, argumentos: List[Expresion]) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion = {
    val contextoDeLambda = new Contexto(contexto)
    funcion match {
      case LambdaAST(parametros, cuerpo) => interpretarLambda(parametros, cuerpo, contextoDeLambda)
      case VariableAST(nombre) =>
        contexto.obtenerValorVariable(nombre) match {
          case ResultadoLambda(parametros, cuerpo) => interpretarLambda(parametros, cuerpo, contextoDeLambda)
        }
      case AplicacionLambdaAST(otraFuncion, otrosArgumentos) => AplicacionLambdaAST(otraFuncion, otrosArgumentos).evaluarse(contexto)
      case _ => throw ErrorDeTipos()
    }
  }

  private def interpretarLambda(parametros: List[String], cuerpo: List[ElementoAST], contextoDeLambda: Contexto) = {
    asignarParametros(parametros, argumentos, contextoDeLambda)
    Interprete.interpretar(cuerpo, contextoDeLambda)
  }

  private def asignarParametros(parametros: List[String], argumentos: List[Expresion], contextoDeLambda: Contexto) = {
    (parametros zip argumentos).foreach(t => contextoDeLambda.guardarVariable(t._1, t._2.evaluarse(contextoDeLambda)))
  }

}

