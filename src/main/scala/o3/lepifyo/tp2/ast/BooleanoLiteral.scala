package o3.lepifyo.tp2.ast

case class BooleanoLiteral(valor: Boolean) extends ElementoAST {

  override def evaluarse(): BooleanoLiteral = this

}
