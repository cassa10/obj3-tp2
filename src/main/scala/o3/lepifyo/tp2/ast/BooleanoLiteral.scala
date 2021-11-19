package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}

case class BooleanoLiteral(valor: Boolean) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = ResultadoBooleanoLiteral(valor)

  override def analizarse(reglas: List[Regla]): List[Problema] =
    reglas.map(regla => regla.apply(this))
    .filter(_.isDefined)
    .map(_.get)

}
