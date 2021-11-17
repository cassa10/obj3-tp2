package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class BooleanoLiteral(valor: Boolean) extends ElementoAST {

  override def evaluarse(): BooleanoLiteral = this

  override def analizarse(reglas: List[Regla]): List[Problema] =
    reglas.map(regla => regla.apply(this))
    .filter(_.isDefined)
    .map(_.get)

  override def toString: String = valor.toString

}
