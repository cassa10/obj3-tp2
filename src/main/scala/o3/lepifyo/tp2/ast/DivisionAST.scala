package o3.lepifyo.tp2.ast

case class DivisionAST(operador1: ElementoAST, operador2: ElementoAST) extends ElementoAST {

  override def representar(): DivisionAST = this

}

object DivisionAST {

  def apply(operador1: ElementoAST, operador2: ElementoAST) = new DivisionAST(operador1, operador2)

}
