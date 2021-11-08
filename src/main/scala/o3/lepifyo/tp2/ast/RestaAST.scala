package o3.lepifyo.tp2.ast

case class RestaAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST {

  override def representar(): RestaAST = this

}