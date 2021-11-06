package o3.lepifyo.tp2.ast

case class NumeroLiteral(valor: Int) extends ElementoAST {

  override def representar(): NumeroLiteral = this

//  override def ejecutar(): Int = valor

}

object NumeroLiteral {

  def apply(n: Int) = new NumeroLiteral(n)

}
