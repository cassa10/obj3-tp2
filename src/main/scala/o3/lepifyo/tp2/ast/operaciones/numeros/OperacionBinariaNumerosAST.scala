package o3.lepifyo.tp2.ast.operaciones.numeros

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.exception.ErrorDeTipos
import o3.lepifyo.tp2.ast.{ElementoAST, NumeroLiteral}

// TODO: Revisar si hace falta incorporarlo a la jerarquia como una clase o dejarlo como un trait
trait OperacionBinariaNumerosAST {
  def operador1: ElementoAST
  def operador2: ElementoAST
  def operacion: (Int, Int) => ElementoAST

  def evaluarse(): ElementoAST = {
    (operador1.evaluarse(), operador2.evaluarse()) match {
      case (NumeroLiteral(valor1), NumeroLiteral(valor2)) => operacion(valor1, valor2)
      case _ => throw ErrorDeTipos()
    }
  }

  // TODO: Podria no devolver options de problemas
  def analizarse(reglas: List[Regla]): List[Option[Problema]] = {
    reglas.map(regla => regla.apply(this.asInstanceOf[ElementoAST])).concat(
      operador1.analizarse(reglas)
    ).concat(
      operador2.analizarse(reglas)
    )
  }

}
