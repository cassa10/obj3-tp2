package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.ResultadoExpresion

object Interprete {

  def interpretar(ast: List[ElementoAST], contexto: Contexto): ResultadoExpresion = ast.map(_.evaluarse(contexto)).last

}
