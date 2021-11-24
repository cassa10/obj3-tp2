package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.parser.ParserFactory.Expresion
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.variables.VariableAST
import o3.lepifyo.tp2.ejecucion.{Interprete, Memoria}
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class AplicacionLambdaAST(funcion: Expresion, argumentos: List[Expresion]) extends ElementoAST {

  val interprete = new Interprete()

  override def evaluarse(memoria: Memoria): ResultadoExpresion = {
    funcion match {
      case LambdaAST(parametros, cuerpo) => interpretarLambda(parametros, cuerpo, memoria)
      case VariableAST(nombre) =>
        memoria.obtenerValorVariable(nombre) match {
          case ResultadoLambda(parametros, cuerpo) => interpretarLambda(parametros, cuerpo, memoria)
        }
      case _ => throw ErrorDeTipos()
    }
  }

  private def interpretarLambda(parametros: List[String], cuerpo: List[ElementoAST], memoria: Memoria) = {
    asignarParametros(parametros, argumentos, memoria)
    interprete.interpretar(cuerpo, memoria)
  }

  private def asignarParametros(parametros: List[String], argumentos: List[Expresion], memoria: Memoria) = {
    (parametros zip argumentos).foreach(t => memoria.guardarVariable(t._1, t._2.evaluarse(memoria)))
  }

}

