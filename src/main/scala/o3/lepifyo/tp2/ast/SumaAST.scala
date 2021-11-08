package o3.lepifyo.tp2.ast

case class SumaAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST {

  override def representar(): SumaAST = this

}

object SumaAST {

  def apply(operador1: ElementoAST, operador2: ElementoAST) = new SumaAST(operador1, operador2)

}
