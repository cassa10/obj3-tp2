package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

class Analizador(val reglas: List[Regla]) {

  // TODO: Implementar el patter matching y no delegarle a los elementosAST el análisis de sí mismos
  def analizar(ast: List[ElementoAST]): List[Problema] = ast.flatMap(_.analizarse(reglas))

}
