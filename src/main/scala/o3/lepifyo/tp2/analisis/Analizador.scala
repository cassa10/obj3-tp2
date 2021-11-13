package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

class Analizador {

  def analizar(ast: List[ElementoAST], reglas: List[Regla]): List[Problema] = ast.flatMap(
    _.analizarse(reglas).filter(_.isDefined).map(_.get)
  )

}
