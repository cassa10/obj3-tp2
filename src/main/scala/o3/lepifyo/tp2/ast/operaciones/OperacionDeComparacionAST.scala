package o3.lepifyo.tp2.ast.operaciones

import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ejecucion.Memoria
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion, ResultadoNumeroLiteral}

abstract class OperacionDeComparacionAST extends OperacionBinariaAST {

  override def operacion: (AnyVal, AnyVal) => ResultadoExpresion

  override def evaluarse(memoria: Memoria): ResultadoExpresion = {
    (operador1.evaluarse(memoria), operador2.evaluarse(memoria)) match {
      case (ResultadoNumeroLiteral(valor1), ResultadoNumeroLiteral(valor2)) => operacion(valor1, valor2)
      case (ResultadoBooleanoLiteral(valor1), ResultadoBooleanoLiteral(valor2)) => operacion(valor1, valor2)
      case _ => throw ErrorDeTipos()
    }
  }

}
