package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class BooleanoLiteral(valor: Boolean) extends ElementoAST {

  override def evaluarse(): BooleanoLiteral = this

  //FIXME: Analizar si permitir que alguna regla ne permitan escribir algun booleano literal? Permitirselo a todos
  override def analizarse(reglas: List[Regla]): List[Option[Problema]] = List(None)

  override def toString: String = valor.toString

}
