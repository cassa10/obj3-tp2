package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.parser.ParserFactory

import scala.io.StdIn.readLine

object REPL {

  val parser = {
    val parserFactory = new ParserFactory
    parserFactory.build
  }

  val interprete = new Interprete

  // TODO: Agregar handleo de excepciones para no poderder el repl
  // TODO: Cambiar por while true
  def main(args: Array[String]): Unit = {
    print("> ")
    val input = readLine

    if (input == "exit") return

    val ast = parser.parsear(input)
    val resultado = interprete.interpretar(ast)
    println(resultado.evaluarse)

    main(args)
  }

}
