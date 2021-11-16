package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class DeclaracionVariable(nombre: String, valorInicial: ElementoAST) extends ElementoAST {

  override def evaluarse(): ElementoAST = Variable(nombre, valorInicial.evaluarse())

  override def analizarse(reglas: List[Regla]): List[Option[Problema]] = List(None)

}
