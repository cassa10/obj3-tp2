package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.{BooleanoLiteralAST, ElementoAST, NumeroLiteralAST}
import o3.lepifyo.tp2.ast.operaciones.OperacionBinariaAST
import o3.lepifyo.tp2.ast.variables.{AsignacionAST, DeclaracionVariableAST}

class Analizador(val reglas: List[Regla]) {

  def analizar(ast: List[ElementoAST]): List[Problema] = ast.flatMap(this.analizarAst)

  def analizarAst(ast: ElementoAST): List[Problema] = {
    ast match {
      case OperacionBinariaAST(op1, op2) => analizarElementoActual(ast)
        .concat(analizarAst(op1))
        .concat(analizarAst(op2))
      case DeclaracionVariableAST(_, astAsignado) => analizarElementoActual(ast)
        .concat(analizarAst(astAsignado))
      case AsignacionAST(_, astAsignado) => analizarElementoActual(ast)
        .concat(analizarAst(astAsignado))
      case BooleanoLiteralAST(_) | NumeroLiteralAST(_) => analizarElementoActual(ast)
    }
  }

  private def analizarElementoActual(ast: ElementoAST) = {
    reglas.map(regla => regla.apply(ast))
      .filter(_.isDefined)
      .map(_.get)
  }
}
