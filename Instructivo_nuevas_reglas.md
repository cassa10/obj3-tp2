# Definición de nuevas reglas

El analizador, además de las reglas que define, cuenta con la posibilidad de que el usuario pueda agregar nuevas reglas para el análisis estático de código.

# Instructivo

## 1. Crear regla
Crear case class para representar la regla a aplicar. Debe extender de trait <code>Regla</code>.

## 2. Establecer gravedad
Indicar si es un Error o una Advertencia.
Sobrescribir el nivel de gravedad de la regla en la property <code>nivelGravedad</code> de la case class.

## 3. Definir mensaje
Indicar el mensaje de que debe mostrarse al detectar el problema.
Agregar mensaje en object <code>MensajeProblema</code>.

## 4. Implementar regla
Implementar la aplicación de la regla. El método debe aplicar pattern matching sobre el elementoAST para identificar el problema y retornarlo con los parámetros correspondientes.
Sobrescribir <code>apply(elementoAST: ElementoAST): Option[Problema]</code> en case class. 

## 5. Aplicar regla
Proporcionar regla a analizador para que pueda ser aplicada.
Agregar la clase creada en la property <code>reglas: List[Regla]</code> del object <code>Analizador</code>

# Ejemplo:

Agregamos una regla nueva para poder identificar restas innecesarias en un programa.
Por ejemplo: 10 + **5 - 5**.

### 1. Crear nueva regla
```
case class RestaInnecesaria() extends Regla { }
```

### 2. Indicar que se trata de una regla de advertencia
```
case class RestaInnecesaria() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Advertencia

}
```

### 3. Agregar el mensaje correspondiente a la nueva regla
```
object MensajeProblema {
  ...
  val RestaInnecesaria = "Restar un número a sí mismo es innecesario"
}
```

### 4. Implementar la lógica de la nueva regla
```
case class RestaInnecesaria() extends Regla {

  override val nivelGravedad: Tipo = NivelGravedad.Advertencia

  def apply(elementoAST: ElementoAST): Option[Problema] = {
    elementoAST match {
      case RestaAST(NumeroLiteralAST(x), NumeroLiteralAST(y)) if (x == y) => identificarProblema(MensajeProblema.RestaInnecesaria, nivelGravedad, elementoAST)
      case _ => None
    }
  }

}
```

### 5. Agregar nueva regla al "motor de reglas" del analizador
```
object Analizador {

  val reglas: List[Regla] = List(
    .
    .
    .
    RestaInnecesaria
  ).map(x => x.apply())

  ...
}
```