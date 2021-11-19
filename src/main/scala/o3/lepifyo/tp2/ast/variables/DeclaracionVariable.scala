package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.{ElementoAST, Memoria}
import o3.lepifyo.tp2.resultado.{ResultadoBooleanoLiteral, ResultadoExpresion}

case class DeclaracionVariable(nombre: String, valorInicial: ElementoAST) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = {
    val variable = Variable(nombre, valorInicial.evaluarse())
    Memoria.guardar(variable)
    ResultadoBooleanoLiteral(true) //agregar nuevo tipo de retorno¿?
  }

  override def analizarse(reglas: List[Regla]): List[Problema] = List()

}
