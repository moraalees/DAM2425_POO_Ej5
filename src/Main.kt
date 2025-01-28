fun pedirTiempo(msj: String, aceptaVacio: Boolean = false): Int {

    var num: Int? = null

    do {
        print(msj)
        val entrada = readln().trim()

        if (aceptaVacio && entrada.isEmpty()) {
            num = 0
        } else {
            try {
                num = entrada.toInt()
            } catch (e: NumberFormatException) {
                println("ERROR - Número no válido, inténtelo otra vez...")
            }
        }
    } while (num == null)

    return num
}


fun main() {

    val hora = pedirTiempo("Introduzca la hora: ")
    val min = pedirTiempo("Introduzca los min: ", true)
    val seg = pedirTiempo("Introduzca los seg: ", true)

    val tiempo1 = Tiempo(hora, min, seg)
    println(tiempo1)

    val horaT = pedirTiempo("Introduzca la hora de t: ")
    val minT = pedirTiempo("Introduzca los min de t: ", true)
    val segT = pedirTiempo("Introduzca los seg de t: ", true)

    val tiempoT = Tiempo(horaT, minT, segT)
    println("Tiempo t: $tiempoT")

    if (tiempo1.incrementar(tiempoT)) {
        println("El tiempo después de incrementar: $tiempo1")
    } else {
        println("Error al incrementar: el tiempo resultante supera 23:59:59")
    }

    if (tiempo1.decrementar(tiempoT)) {
        println("El tiempo después de decrementar: $tiempo1")
    } else {
        println("Error al decrementar: el tiempo resultante es menor que 00:00:00")
    }


    val resultadoComparacion = tiempo1.comparar(tiempoT)
    if (resultadoComparacion == -1) {
        println("El tiempo 1 es menor que t.")
    } else if (resultadoComparacion == 0){
        println("El tiempo 1 es igual a t.")
    } else {
        println("El tiempo 1 es mayor que t.")
    }


    val tiempoCopiado = tiempo1.copiar()
    println("Nuevo objeto copiado: $tiempoCopiado")
    tiempo1.copiar(tiempoT)
    println("Tiempo1 después de copiar t: $tiempo1")


    val tiempoSumado = tiempo1.sumar(tiempoT)
    if (tiempoSumado != null) {
        println("El tiempo después de sumar: $tiempoSumado")
    } else {
        println("Error al sumar: el resultado supera 23:59:59")
    }


    val tiempoRestado = tiempo1.restar(tiempoT)
    if (tiempoRestado != null) {
        println("El tiempo después de restar: $tiempoRestado")
    } else {
        println("Error al restar: el resultado es menor que 00:00:00")
    }


    if (tiempo1.esMayorQue(tiempoT)) {
        println("El tiempo 1 es mayor que t.")
    } else {
        println("El tiempo 1 no es mayor que t.")
    }


    if (tiempo1.esMenorQue(tiempoT)) {
        println("El tiempo 1 es menor que t.")
    } else {
        println("El tiempo 1 no es menor que t.")
    }
}