package o3.lepifyo.tp2.resultado

import o3.lepifyo.tp2.ast.ElementoAST

case class ResultadoLambda(argumentos: List[String], cuerpo: List[ElementoAST]) extends ResultadoExpresion {

  override def toString: String = {
    if (cuerpo.size == 1) {
      s"(${argumentos.mkString(",")}) -> ${cuerpo.head.toString}"
    } else {
      s"(${argumentos.mkString(",")}) -> { \n ${cuerpo.map(_.toString.concat("\n"))} \n }"
    }
  }

}
