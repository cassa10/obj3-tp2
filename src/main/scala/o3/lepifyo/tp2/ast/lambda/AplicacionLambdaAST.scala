package o3.lepifyo.tp2.ast.lambda

import o3.lepifyo.parser.ParserFactory.Expresion
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.variables.VariableAST
import o3.lepifyo.tp2.ejecucion.{Interprete, Memoria}
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoLambda}

case class AplicacionLambdaAST(funcion: Expresion, argumentos: List[Expresion]) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion =

    funcion match {
      case VariableAST(nombre) => Memoria.obtenerValorVariable(nombre) match {
        case ResultadoLambda(argumentos, cuerpo) => new Interprete().interpretar(cuerpo)
      }
      case LambdaAST(parametros, cuerpo) => new Interprete().interpretar(cuerpo)
      case _ => throw ErrorDeTipos()
    }
}

