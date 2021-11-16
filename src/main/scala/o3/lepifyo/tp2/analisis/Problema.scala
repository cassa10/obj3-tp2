package o3.lepifyo.tp2.analisis

import o3.lepifyo.tp2.ast.ElementoAST

case class Problema(descripcion: String, nivelGravedad: NivelGravedad.Tipo, astElem:ElementoAST)

// TODO: Exportar los mensajes de problema como constantes para usar desde los test y desde la regla