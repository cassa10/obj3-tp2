package o3.lepifyo.tp2.resultado

import o3.lepifyo.tp2.ast.ElementoAST

case class ResultadoLambda(parametros: List[String], cuerpo: List[ElementoAST]) extends ResultadoExpresion {

  override def toString: String = {
    if (cuerpo.size == 1) {
      s"(${parametros.mkString(",")}) -> ${cuerpo.head.toString}"
    } else {
      s"(${parametros.mkString(",")}) -> { \n ${cuerpo.map(_.toString.concat("\n"))} \n }"
    }
  }

}
