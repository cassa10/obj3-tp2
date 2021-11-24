package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.ejecucion.Memoria
import o3.lepifyo.tp2.resultado.ResultadoExpresion

trait ElementoAST {

  def evaluarse(memoria: Memoria): ResultadoExpresion

}
