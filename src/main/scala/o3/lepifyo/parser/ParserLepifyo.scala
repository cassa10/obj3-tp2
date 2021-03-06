package o3.lepifyo.parser

import o3.lepifyo.parser.ParserLepifyo.{MissingFunctionError, ParseError}

import scala.util.matching.Regex
import scala.util.parsing.combinator._

case class ParserLepifyo[TPrograma, TExpresion](
  programa: List[TExpresion] => TPrograma = { _: List[TExpresion] => throw MissingFunctionError("programa") },
  numero: Int => TExpresion = { _: Int => throw MissingFunctionError("numero") },
  booleano: Boolean => TExpresion = { _: Boolean => throw MissingFunctionError("booleano") },
  string: String => TExpresion = { _: String => throw MissingFunctionError("string") },
  suma: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("suma") },
  resta: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("resta") },
  multiplicacion: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("multiplicacion") },
  division: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("division") },
  igual: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("igual") },
  distinto: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("distinto") },
  mayor: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("mayor") },
  mayorIgual: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("mayorIgual") },
  menor: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("menor") },
  menorIgual: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("menorIgual") },
  declaracionVariable: (String, TExpresion) => TExpresion = { (_: String, _: TExpresion) => throw MissingFunctionError("declaracionVariable") },
  variable: String => TExpresion = { _: String => throw MissingFunctionError("variable") },
  asignacion: (String, TExpresion) => TExpresion = { (_: String, _: TExpresion) => throw MissingFunctionError("asignacion") },
  concatenacion: (TExpresion, TExpresion) => TExpresion = { (_: TExpresion, _: TExpresion) => throw MissingFunctionError("concatenacion") },
  si: (TExpresion, List[TExpresion], List[TExpresion]) => TExpresion = { (_: TExpresion, _: List[TExpresion], _: List[TExpresion]) => throw MissingFunctionError("si") },
  lambda: (List[String], List[TExpresion]) => TExpresion = { (_: List[String], _: List[TExpresion]) => throw MissingFunctionError("lambda") },
  aplicacion: (TExpresion, List[TExpresion]) => TExpresion = { (_: TExpresion, _: List[TExpresion]) => throw MissingFunctionError("aplicacion") }
) extends RegexParsers {
  
  protected override val whiteSpace: Regex = """\h+""".r

  def parsear(textoPrograma: String): TPrograma = {
    def parserEspacios: Parser[String] = """\s*""".r
    def parserNumero: Parser[TExpresion] = """[0-9]+""".r ^^ { n => numero(n.toInt) }
    def parserBooleano: Parser[TExpresion] = "true" ^^^ booleano(true) | "false" ^^^ booleano(false)
    def parserString: Parser[TExpresion] = """"\s*""".r ~ """(\\\\|\\"|[^"])*""".r <~ "\"" ^^ {
      // Consumir los espacios del inicio (con la primera regex) es necesario porque si us??ramos ~> descartar??a
      // los espacios al inicio del string
      case inicioConEspacios ~ restoDelString =>
        string(
          (inicioConEspacios.drop(1) + restoDelString)
            .replace("\\\"", "\"")
            .replace("\\\\", "\\")
        )
    }

    def parserIdentificador: Parser[String] = {
      val emoji = """[\p{block=Emoticons}\p{block=Miscellaneous Symbols and Pictographs}]"""
      val letra = """\p{L}\p{M}*"""
      val numero = """\p{N}"""

      s"((_|$letra|$emoji)(_|$letra|$numero|$emoji)*)".r
    }

    def parserVariable: Parser[TExpresion] = parserIdentificador ^^ variable

    def parserLiteral = parserString | parserNumero | parserBooleano | parserVariable | "(" ~> parserExpresion <~ ")"

    def parserLambda: Parser[TExpresion] =
      ((("(" ~> repsep(parserIdentificador, ",") <~ ")") | parserIdentificador ^^ (List(_))) <~ "->") ~
        parserBloque ^^ {
          case parametros ~ cuerpo => lambda(parametros, cuerpo)
        }
    def parserAplicacion: Parser[TExpresion] =
      parserLiteral ~ ("(" ~> repsep(parserExpresion, ",") <~ ")").+ ^^ {
      case funcion ~ aplicaciones => aplicaciones.foldLeft(funcion)(aplicacion)
    }

    def parserFactor: Parser[TExpresion] = parserAplicacion | parserLiteral

    def parserTermino = chainl1(parserFactor, parserOperadores("*" -> multiplicacion, "/" -> division))

    def parserOperadores(operadores: (String, (TExpresion, TExpresion) => TExpresion)*) =
      operadores.map {
        case (simboloOperador, constructorOperacion) =>
          simboloOperador <~ parserEspacios ^^^ constructorOperacion
      }.reduce(_ | _)

    def parserMiembros = chainl1(parserTermino, parserOperadores("+" -> suma, "-" -> resta))

    def parserConcatenacion = chainl1(parserMiembros, parserOperadores("++" -> concatenacion))

    def parserMiembroDesigualdad = chainl1(
      parserConcatenacion,
      parserOperadores(">=" -> mayorIgual, "<=" -> menorIgual, ">" -> mayor, "<" -> menor)
    )

    def parserExpresion = parserLambda | parserIf |
      chainl1(parserMiembroDesigualdad, parserOperadores("==" -> igual, "!=" -> distinto))
    
    def parserDeclaracionVariables = ("let " ~> parserIdentificador <~ "=" <~ parserEspacios) ~ parserExpresion ^^ {
      case identificador ~ expresion => declaracionVariable(identificador, expresion)
    }
    def parserAsignacion = (parserIdentificador <~ "=" <~ parserEspacios) ~ parserExpresion ^^ {
      case identificador ~ expresion => asignacion(identificador, expresion)
    }

    def parserInstruccion = parserDeclaracionVariables | parserAsignacion | parserExpresion
    def parserBloque = "{" ~> parserInstrucciones <~ "}" | (parserInstruccion ^^ { List(_) })

    def parserIf: Parser[TExpresion] = ("if" ~> parserExpresion <~ "then") ~ parserBloque ~ ("else" ~> parserBloque).? ^^ {
      case cond ~ pos ~ neg => si(cond, pos, neg.getOrElse(List()))
    }

    def parserInstrucciones = parserEspacios ~> (parserInstruccion <~ parserEspacios).*  <~ parserEspacios

    def parserPrograma = parserInstrucciones ^^ programa

    parseAll(parserPrograma, textoPrograma.stripMargin) match {
      case Success(matched, _) => matched
      case Failure(message, rest) => throw ParseError(s"$message: ${rest.source}")
      case Error(message, rest) => throw ParseError(s"$message: ${rest.source}")
    }
  }
}

object ParserLepifyo {
  case class ParseError(message: String) extends RuntimeException(message)
  case class MissingFunctionError(fn: String) extends RuntimeException("Falta especificar una funci??n para: " + fn)
}
