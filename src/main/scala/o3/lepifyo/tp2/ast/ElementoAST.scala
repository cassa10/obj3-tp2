package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.ResultadoExpresion

trait ElementoAST {

  def evaluarse(contexto: Contexto): ResultadoExpresion

}
