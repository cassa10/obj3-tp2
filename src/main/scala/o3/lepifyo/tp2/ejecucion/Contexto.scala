package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.resultado.ResultadoExpresion

import scala.collection.mutable.Map

class Contexto(val contextoPadre: Contexto) {

  var variables: Map[String, ResultadoExpresion] = Map.empty

  def guardarVariable(nombre: String, valorInicial: ResultadoExpresion): Unit = variables += (nombre -> valorInicial)

  def actualizarVariable(nombre: String, valorNuevo: ResultadoExpresion): Unit = if (tieneContextoPadre()) {
    if (variables.contains(nombre)) {
      variables(nombre) = valorNuevo
    } else {
      contextoPadre.actualizarVariable(nombre, valorNuevo)
    }
  } else {
    variables(nombre) = valorNuevo
  }

  def tieneContextoPadre(): Boolean = contextoPadre != null

  def obtenerValorVariable(nombre: String): ResultadoExpresion = if (tieneContextoPadre()) {
    variables.getOrElse(nombre, contextoPadre.obtenerValorVariable(nombre))
  } else {
    variables(nombre)
  }

}

object Contexto {

  def contextoGlobal: Contexto = new Contexto(null)

}


