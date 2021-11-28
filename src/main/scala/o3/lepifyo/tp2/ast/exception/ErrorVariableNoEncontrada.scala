package o3.lepifyo.tp2.ast.exception

case class ErrorVariableNoEncontrada(nombre: String) extends RuntimeException(s"Variable ${nombre} no encontrada")
