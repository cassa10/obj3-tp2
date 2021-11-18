package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.{BooleanoLiteral, ElementoAST, NumeroLiteral}

abstract class OperacionDeComparacionAST extends OperacionBinariaAST {

  override def operacion: (AnyVal, AnyVal) => ElementoAST

  override def evaluarse(): ElementoAST = {
    (operador1.evaluarse(), operador2.evaluarse()) match {
      case (NumeroLiteral(valor1), NumeroLiteral(valor2)) => operacion(valor1, valor2)
      case (BooleanoLiteral(valor1), BooleanoLiteral(valor2)) => operacion(valor1, valor2)
      case _ => throw ErrorDeTipos()
    }
  }

}
