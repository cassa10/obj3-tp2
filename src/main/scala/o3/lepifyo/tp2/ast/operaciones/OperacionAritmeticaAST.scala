package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.{ResultadoExpresion, ResultadoNumeroLiteral}

abstract class OperacionAritmeticaAST extends OperacionBinariaAST {

  override def evaluarse(contexto: Contexto): ResultadoExpresion = {
    (operador1.evaluarse(contexto), operador2.evaluarse(contexto)) match {
      case (ResultadoNumeroLiteral(valor1), ResultadoNumeroLiteral(valor2)) => operacion(valor1, valor2)
      case _ => throw ErrorDeTipos()
    }
  }

}
