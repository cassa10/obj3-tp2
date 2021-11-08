package o3.lepifyo.tp2.ast

case class BooleanoLiteral(valor: Boolean) extends ElementoAST {

  override def representar(): BooleanoLiteral = this

//  override def ejecutar(): Boolean = valor

}
