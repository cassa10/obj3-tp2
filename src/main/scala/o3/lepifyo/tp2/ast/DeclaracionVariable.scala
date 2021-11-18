package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}

case class DeclaracionVariable(nombre: String, valorInicial: ElementoAST) extends ElementoAST {

  override def evaluarse(): ElementoAST = {
    val variable = Variable(nombre, valorInicial.evaluarse())
    Memoria.guardar(variable)
    variable
  }

  override def analizarse(reglas: List[Regla]): List[Problema] = List()

}
