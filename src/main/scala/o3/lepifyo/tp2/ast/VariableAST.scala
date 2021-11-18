package o3.lepifyo.tp2.ast
import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class VariableAST(nombre: String) extends ElementoAST {
  override def evaluarse(): ElementoAST = Memoria.buscar(nombre)

  override def analizarse(reglas: List[Regla]): List[Problema] = ???
}
