package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.analisis.regla.{DivisionPorCero, LambdaNoRetornaExpresion, OperacionRedundante, ParametroDuplicado, Regla, VariableDuplicada}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.lambda.LambdaAST
import o3.lepifyo.tp2.ast.literales.{BooleanoLiteralAST, NumeroLiteralAST}
import o3.lepifyo.tp2.ast.operaciones.OperacionBinariaAST
import o3.lepifyo.tp2.ast.variables.{AsignacionAST, DeclaracionVariableAST, VariableAST}

object Analizador {

  val reglas: List[Regla] = List(
    DivisionPorCero,
    OperacionRedundante,
    VariableDuplicada,
    LambdaNoRetornaExpresion,
    ParametroDuplicado
  ).map(x => x.apply())

  def analizar(ast: List[ElementoAST]): List[Problema] = ast.flatMap(analizarAST)

  private def analizarAST(ast: ElementoAST): List[Problema] = {
    ast match {
      case OperacionBinariaAST(op1, op2) => analizarElementoActual(ast)
        .concat(analizarAST(op1))
        .concat(analizarAST(op2))
      case DeclaracionVariableAST(_, astAsignado) => analizarElementoActual(ast)
        .concat(analizarAST(astAsignado))
      case LambdaAST(_, cuerpo) => analizarElementoActual(ast)
        .concat(analizar(cuerpo))
      case AsignacionAST(_, astAsignado) => analizarElementoActual(ast)
        .concat(analizarAST(astAsignado))
      case BooleanoLiteralAST(_) | NumeroLiteralAST(_) | VariableAST(_) => analizarElementoActual(ast)
    }
  }

  private def analizarElementoActual(ast: ElementoAST): List[Problema] = {
    reglas.map(regla => regla.apply(ast))
      .filter(_.isDefined)
      .map(_.get)
  }
}