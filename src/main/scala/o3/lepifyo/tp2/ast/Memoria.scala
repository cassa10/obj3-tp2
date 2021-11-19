package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.ast.variables.Variable
import o3.lepifyo.tp2.resultado.ResultadoExpresion

object Memoria {

  var variables: Map[String, ResultadoExpresion] = Map.empty

  def guardar(variable: Variable): Unit = variables += (variable.nombre -> variable.valor)

  def buscar(nombre: String): ResultadoExpresion = variables(nombre)

}
