package o3.lepifyo.parser

import o3.lepifyo.tp2.ast.operaciones.{DistintoAST, DivisionAST, IgualAST, MayorAST, MayorIgualAST, MenorAST, MenorIgualAST, MultiplicacionAST, RestaAST, SumaAST}
import o3.lepifyo.tp2.ast.variables.{AsignacionAST, DeclaracionVariableAST, VariableAST}
import o3.lepifyo.tp2.ast.ElementoAST
import o3.lepifyo.tp2.ast.lambda.{AplicacionLambdaAST, LambdaAST}
import o3.lepifyo.tp2.ast.literales.{BooleanoLiteralAST, NumeroLiteralAST}

object ParserFactory {

  type Expresion = ElementoAST
  type Programa = List[Expresion]

  def programa(expresiones: Expresion*) = expresiones.toList

  def numero(n: Int) = NumeroLiteralAST(n)

  def booleano(b: Boolean) = BooleanoLiteralAST(b)

  def suma(o1: Expresion, o2: Expresion) = SumaAST(o1, o2)

  def resta(o1: Expresion, o2: Expresion) = RestaAST(o1, o2)

  def multiplicacion(o1: Expresion, o2: Expresion) = MultiplicacionAST(o1, o2)

  def division(o1: Expresion, o2: Expresion) = DivisionAST(o1, o2)

  def igual(o1: Expresion, o2: Expresion) = IgualAST(o1, o2)

  def distinto(o1: Expresion, o2: Expresion) = DistintoAST(o1, o2)

  def menor(o1: Expresion, o2: Expresion) = MenorAST(o1, o2)

  def mayor(o1: Expresion, o2: Expresion) = MayorAST(o1, o2)

  def menorIgual(o1: Expresion, o2: Expresion) = MenorIgualAST(o1, o2)

  def mayorIgual(o1: Expresion, o2: Expresion) = MayorIgualAST(o1, o2)

  def declaracionVariable(nombre: String, valorInicial: Expresion) = DeclaracionVariableAST(nombre, valorInicial)

  def variable(nombre: String) = VariableAST(nombre)

  def asignacion(nombre: String, valorNuevo: Expresion) = AsignacionAST(nombre, valorNuevo)

  def lambda(parametros: List[String], cuerpo: List[Expresion]) = LambdaAST(parametros, cuerpo)

  def aplicacion(funcion: Expresion, argumentos: List[Expresion]) = AplicacionLambdaAST(funcion, argumentos)

  def build: ParserLepifyo[Programa, Expresion] = new ParserLepifyo[Programa, Expresion](
    programa = programa,
    numero = numero,
    booleano = booleano,
    suma = suma,
    resta = resta,
    multiplicacion = multiplicacion,
    division = division,
    igual = igual,
    distinto = distinto,
    menor = menor,
    mayor = mayor,
    menorIgual = menorIgual,
    mayorIgual = mayorIgual,
    declaracionVariable = declaracionVariable,
    variable = variable,
    asignacion = asignacion,
    lambda = lambda,
    aplicacion = aplicacion,
  )

}
