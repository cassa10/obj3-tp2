package o3.lepifyo.parser

import o3.lepifyo.tp2.ast.{BooleanoLiteral, DivisionAST, ElementoAST, MultiplicacionAST, NumeroLiteral, RestaAST, SumaAST}

class ParserFactory {

  type Expresion = ElementoAST
  type Programa = List[Expresion]

  def programa(expresiones: Expresion*) = expresiones.toList

  def numero(n: Int) = NumeroLiteral(n)

  def booleano(b: Boolean) = BooleanoLiteral(b)

  def suma(o1: Expresion, o2: Expresion) = SumaAST(o1, o2)

  def resta(o1: Expresion, o2: Expresion) = RestaAST(o1, o2)

  def multiplicacion(o1: Expresion, o2: Expresion) = MultiplicacionAST(o1, o2)

  def division(o1: Expresion, o2: Expresion) = DivisionAST(o1, o2)

  def build: ParserLepifyo[Programa, Expresion] = new ParserLepifyo[Programa, Expresion](
    programa = programa,
    numero = numero,
    booleano = booleano,
    suma = suma,
    resta = resta,
    multiplicacion = multiplicacion,
    division = division
  )

}
