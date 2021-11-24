package o3.lepifyo.tp2.ast.literales

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ejecucion.Contexto
import o3.lepifyo.tp2.resultado.ResultadoNumeroLiteral

case class NumeroLiteralAST(valor: Int) extends ElementoAST {

  override def evaluarse(contexto: Contexto): ResultadoNumeroLiteral = ResultadoNumeroLiteral(valor)

}
