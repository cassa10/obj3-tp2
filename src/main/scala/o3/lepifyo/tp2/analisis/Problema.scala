package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.analisis.regla.NivelGravedad
import o3.lepifyo.tp2.ast.ElementoAST

case class Problema(descripcion: String, nivelGravedad: NivelGravedad.Tipo, astElem: ElementoAST)

object MensajeProblema {
  val DivisionPorCero = "No se puede dividir por cero"
  val RestarCero = "Restar cero es redundante"
  val SumaCero = "Sumar cero es redundante"
  val MultiplicarUno = "Multiplicar por uno es redundante"
  val DividirUno = "Dividir por uno es redundante"
  val VariableDuplicada: String => String = (nombre: String) => s"La variable $nombre ya se encuentra declarada"
}