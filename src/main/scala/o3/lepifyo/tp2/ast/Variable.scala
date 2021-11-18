package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class Variable(nombre: String, elementoAST: ElementoAST) extends ElementoAST {

  override def evaluarse(): ElementoAST = this

  override def analizarse(reglas: List[Regla]): List[Problema] = List()

}
