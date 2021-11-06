package o3.lepifyo.tp2.ast

case class NumeroLiteral(valor: Int) extends ElementoAST {

  def equals(otroNumeroLiteral: NumeroLiteral) = valor == otroNumeroLiteral.valor

  def ejecutar() = valor

  def analizar() = ???

}

object NumeroLiteral {

  def apply(n: Int) = new NumeroLiteral(n)

}
