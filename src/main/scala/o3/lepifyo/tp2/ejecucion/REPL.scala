package o3.lepifyo.tp2.ejecucion

import o3.lepifyo.parser.ParserFactory

import scala.io.StdIn.readLine

object REPL {

  val parser = ParserFactory.build

  def main(args: Array[String]): Unit = {
    var keepRunning = true
    val contexto = Contexto.contextoGlobal
    while (keepRunning) {
      print("> ")
      val input = readLine
      input match {
        case "exit" => keepRunning = false
        case _ => parsearEInterpretar(input, contexto)
      }
    }
  }

  def parsearEInterpretar(input: String, contexto: Contexto): Unit = {
    try {
      println(Interprete.interpretar(parser.parsear(input), contexto))
    } catch {
      //TODO: Mejorar el class matching y personalizar mas el mensaje de error
      case e: Exception => println("Error: " + e.getMessage)
    }
  }

}