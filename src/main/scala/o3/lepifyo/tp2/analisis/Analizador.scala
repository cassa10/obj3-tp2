package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

class Analizador(val reglas: List[Regla]) {

  def analizar(ast: List[ElementoAST]): List[Problema] = ast.flatMap(_.analizarse(reglas))

}
