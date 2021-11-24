package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.ResultadoExpresion

class Interprete {

  def interpretar(ast: List[ElementoAST], contexto: Contexto): ResultadoExpresion = {
    ast.map(_.evaluarse(contexto)).last
    //TODO `interpretar` puede recibir una memoria, que la pasa a evaluarse, y que la memoria conozca a la memoria
    // de arriba
  }

}
