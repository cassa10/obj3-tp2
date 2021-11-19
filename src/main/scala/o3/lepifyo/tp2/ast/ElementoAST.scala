package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.resultado.ResultadoExpresion

trait ElementoAST {

  def evaluarse(): ResultadoExpresion

  def analizarse(reglas: List[Regla]): List[Problema]

}
