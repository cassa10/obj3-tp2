package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.tp2.ast.ElementoAST

class Interprete {

  def interpretar(ast: List[ElementoAST]): Any = {
    ast.head.evaluarse() //hasta ahora alcanza con suponer al ast con un solo elemento
  }

}
