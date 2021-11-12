package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

class Analizador {

  def analizar(ast: List[ElementoAST]): List[Problema] = ast.map(x => x.analizarse()).flatten

}
