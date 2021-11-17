package o3.lepifyo.tp2.ast

import o3.lepifyo.tp2.analisis.{Problema, Regla}

trait ElementoAST {

  // TODO: Acotar el valor de retorno. No deberíamos permitir que se devuelva un Elemento AST (estamos permitiendo devolver SumaAST por ej)
  def evaluarse(): ElementoAST

  def analizarse(reglas: List[Regla]): List[Problema]

}
