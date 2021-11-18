package o3.lepifyo.tp2.resultado

case class ResultadoNumeroLiteral(valor: Int) extends ResultadoExpresion {

  override def toString: String = valor.toString

}
