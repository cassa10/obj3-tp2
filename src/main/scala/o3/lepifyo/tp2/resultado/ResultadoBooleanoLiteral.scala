package o3.lepifyo.tp2.resultado

case class ResultadoBooleanoLiteral(valor: Boolean) extends ResultadoExpresion {

  override def toString: String = valor.toString

}
