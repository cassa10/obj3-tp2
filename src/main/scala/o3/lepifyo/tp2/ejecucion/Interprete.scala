package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.resultado.ResultadoExpresion

class Interprete {

  def interpretar(ast: List[ElementoAST]): ResultadoExpresion = {
    ast.map(_.evaluarse()).last
  }

}
