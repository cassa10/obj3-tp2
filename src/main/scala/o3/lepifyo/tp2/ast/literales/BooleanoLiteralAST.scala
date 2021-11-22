package o3.lepifyo.tp2.ast.literales

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}

case class BooleanoLiteralAST(valor: Boolean) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = ResultadoBooleanoLiteral(valor)

}
