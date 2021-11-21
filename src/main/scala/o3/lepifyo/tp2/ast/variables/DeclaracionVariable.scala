package o3.lepifyo.tp2.ast.variables

import o3.lepifyo.tp2.analisis.{Problema, Regla}
import o3.lepifyo.tp2.ast.{ElementoAST, Memoria}
import o3.lepifyo.tp2.resultado.{ResultadoDeclaracionVariable, ResultadoExpresion}

case class DeclaracionVariable(nombre: String, valorInicial: ElementoAST) extends ElementoAST {

  override def evaluarse(): ResultadoExpresion = {
    val variable = Variable(nombre, valorInicial.evaluarse())
    Memoria.guardar(variable)

    ResultadoDeclaracionVariable()
  }

  override def analizarse(reglas: List[Regla]): List[Problema] =
    reglas.map(regla => regla.apply(this.asInstanceOf[ElementoAST]))
      .filter(_.isDefined)
      .map(_.get)
}
