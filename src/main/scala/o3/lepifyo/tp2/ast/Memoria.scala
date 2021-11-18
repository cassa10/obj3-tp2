package o3.lepifyo.tp2.ast

object Memoria {

  var variables: Map[String, ElementoAST] = Map.empty

  def guardar(variable: Variable): Unit = variables += (variable.nombre -> variable.elementoAST)

  def buscar(nombre: String): ElementoAST = variables(nombre).evaluarse()

}
