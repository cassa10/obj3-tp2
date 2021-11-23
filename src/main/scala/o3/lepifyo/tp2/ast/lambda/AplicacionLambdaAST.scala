package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.parser.ParserFactory.Expresion
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.variables.VariableAST
import o3.lepifyo.tp2.ejecucion.{Interprete, Memoria}
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class AplicacionLambdaAST(funcion: Expresion, argumentos: List[Expresion]) extends ElementoAST {

  val interprete = new Interprete()

  override def evaluarse(): ResultadoExpresion = {
    funcion match {
      case LambdaAST(parametros, cuerpo) => interpretarLambda(parametros, cuerpo)
      case VariableAST(nombre) =>
        Memoria.obtenerValorVariable(nombre) match {
          case ResultadoLambda(parametros, cuerpo) => interpretarLambda(parametros, cuerpo)
        }
      case _ => throw ErrorDeTipos()
    }
  }

  private def interpretarLambda(parametros: List[String], cuerpo: List[ElementoAST]) = {
    asignarParametros(parametros, argumentos)
    interprete.interpretar(cuerpo)
  }

  private def asignarParametros(parametros: List[String], argumentos: List[Expresion]) = {
    (parametros zip argumentos).foreach(t => Memoria.guardarVariable(t._1, t._2.evaluarse()))
  }

}

