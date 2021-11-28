package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.ast.exception.ErrorVariableNoEncontrada
import o3.lepifyo.tp2.resultado.ResultadoExpresion

import scala.collection.mutable.Map

class Contexto(val contextoPadre: Contexto) {

  var variables: Map[String, ResultadoExpresion] = Map.empty

  def guardarVariable(nombre: String, valorInicial: ResultadoExpresion): Unit = variables += (nombre -> valorInicial)

  def actualizarVariable(nombre: String, valorNuevo: ResultadoExpresion): Unit = {
    if (tieneContextoPadre()) {
      actualizarVariableOSino(nombre, valorNuevo, () => {
        contextoPadre.actualizarVariable(nombre, valorNuevo)
      })
    } else {
      actualizarVariableOSino(nombre, valorNuevo, () => {
        throw ErrorVariableNoEncontrada(nombre)
      })
    }
  }

  private def actualizarVariableOSino(nombre:String, valorNuevo:ResultadoExpresion, elseFn:() => Unit): Unit = {
    if (variables.contains(nombre)) {
      variables(nombre) = valorNuevo
    } else {
      elseFn()
    }
  }

  def tieneContextoPadre(): Boolean = contextoPadre != null

  def obtenerValorVariable(nombre: String): ResultadoExpresion = {
    if (tieneContextoPadre()) {
      variables.getOrElse(nombre, contextoPadre.obtenerValorVariable(nombre))
    } else {
      variables.getOrElse(nombre, throw ErrorVariableNoEncontrada(nombre))
    }
  }

}

object Contexto {

  def contextoGlobal: Contexto = new Contexto(null)

}


