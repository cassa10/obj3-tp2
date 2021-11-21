package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}

case class BooleanoLiteralAST(valor: Boolean) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = ResultadoBooleanoLiteral(valor)

}
