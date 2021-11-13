package o3.lepifyo.tp2.analisis

trait NivelProblema

case class Error() extends NivelProblema
case class Advertencia() extends NivelProblema
