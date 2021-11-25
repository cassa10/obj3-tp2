package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.parser.ParserFactory

import scala.io.StdIn.readLine

object REPL {

  val parser = ParserFactory.build

  def main(args: Array[String]): Unit = {
    var keepRunning = true
    while (keepRunning) {
      print("> ")
      val input = readLine
      input match {
        case "exit" => keepRunning = false
        case _ => parsearEInterpretar(input)
      }
    }
  }

  def parsearEInterpretar(input: String): Unit = {
    try {
      println(Interprete.interpretar(parser.parsear(input), Contexto.contextoGlobal))
    } catch {
      //TODO: Mejorar el class matching y personalizar mas el mensaje de error
      case e: Exception => println("Error: " + e.getMessage)
    }
  }

}