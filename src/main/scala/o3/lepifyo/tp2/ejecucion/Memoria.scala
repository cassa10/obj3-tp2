package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.resultado.ResultadoExpresion

import scala.collection.mutable.Map

object Memoria {

  var variables: Map[String, ResultadoExpresion] = Map.empty

  def guardarVariable(nombre: String, valorInicial: ResultadoExpresion): Unit = variables += (nombre -> valorInicial)

  def obtenerVariable(nombre: String): ResultadoExpresion = variables(nombre)

  def actualizarVariable(nombre: String, valorNuevo: ResultadoExpresion): Unit = variables(nombre) = valorNuevo

}
