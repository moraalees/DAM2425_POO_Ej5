class Tiempo(var hora: Int, var min: Int, var seg: Int){
    constructor(hora: Int) : this(hora, 0, 0)

    constructor(hora: Int, min: Int) : this(hora, min, 0)

    companion object{
        const val MAX_HORA = 24
    }

    init {
        require( min >= 0) { "Min debe ser positivo o cero" }
        require( seg >= 0) { "Seg debe ser positivo o cero" }
        require( hora >= 0) { "Seg debe ser positivo o cero" }

        if (seg > 59){
            min += (seg / 60)
            seg %= 60
        }

        if (min > 59){
            hora += (min / 60)
            min %= 60
        }

        require(
            value = (hora in 0..<MAX_HORA ||
                    hora == 24 && min == 0 && seg == 0)) { "Hora no válida, (máximo 24 00 00)!" }
    }

    fun incrementar(t: Tiempo): Boolean{
        var horaFinal = hora + t.hora
        var minutoFinal = min + t.min
        var segundoFinal = seg + t.seg

        if (segundoFinal > 59){
            minutoFinal += (segundoFinal / 60)
            segundoFinal %= 60
        }

        if (minutoFinal > 59){
            horaFinal += (minutoFinal / 60)
            minutoFinal %= 60
        }

        if (horaFinal >= 24){
            return false
        } else {
            hora = horaFinal
            min = minutoFinal
            seg = segundoFinal
            return true
        }
    }

    fun decrementar(t: Tiempo): Boolean {
        var horaFinal = hora - t.hora
        var minutoFinal = min - t.min
        var segundoFinal = seg - t.seg

        if (segundoFinal < 0) {
            minutoFinal -= 1
            segundoFinal += 60
        }

        if (minutoFinal < 0) {
            horaFinal -= 1
            minutoFinal += 60
        }

        if (horaFinal < 0) {
            return false
        } else {
            hora = horaFinal
            min = minutoFinal
            seg = segundoFinal
            return true
        }
    }

    fun comparar(t: Tiempo): Int {
        val totalSegundosThis = hora * 3600 + min * 60 + seg
        val totalSegundosT = t.hora * 3600 + t.min * 60 + t.seg

        return when {
            totalSegundosThis < totalSegundosT -> -1
            totalSegundosThis == totalSegundosT -> 0
            else -> 1
        }
    }

    fun copiar(): Tiempo {
        return Tiempo(hora, min, seg)
    }

    fun copiar(t: Tiempo) {
        this.hora = t.hora
        this.min = t.min
        this.seg = t.seg
    }


    fun sumar(t: Tiempo): Tiempo? {
        var nuevaHora = this.hora + t.hora
        var nuevoMin = this.min + t.min
        var nuevoSeg = this.seg + t.seg

        if (nuevoSeg >= 60) {
            nuevoMin += nuevoSeg / 60
            nuevoSeg %= 60
        }
        if (nuevoMin >= 60) {
            nuevaHora += nuevoMin / 60
            nuevoMin %= 60
        }

        if (nuevaHora < 24) {
            return Tiempo(nuevaHora, nuevoMin, nuevoSeg)
        } else {
            return null
        }
    }

    fun restar(t: Tiempo): Tiempo? {
        var nuevaHora = this.hora - t.hora
        var nuevoMin = this.min - t.min
        var nuevoSeg = this.seg - t.seg

        if (nuevoSeg < 0) {
            nuevoMin -= 1
            nuevoSeg += 60
        }
        if (nuevoMin < 0) {
            nuevaHora -= 1
            nuevoMin += 60
        }

        if (nuevaHora >= 0) {
            return Tiempo(nuevaHora, nuevoMin, nuevoSeg)
        } else {
            return null
        }
    }

    fun esMayorQue(t: Tiempo): Boolean {
        val totalSegundosThis = hora * 3600 + min * 60 + seg
        val totalSegundosT = t.hora * 3600 + t.min * 60 + t.seg
        return totalSegundosThis > totalSegundosT
    }

    fun esMenorQue(t: Tiempo): Boolean {
        val totalSegundosThis = hora * 3600 + min * 60 + seg
        val totalSegundosT = t.hora * 3600 + t.min * 60 + t.seg
        return totalSegundosThis < totalSegundosT
    }

    override fun toString(): String {
        return "${"%02d".format(hora)}h ${"%02d".format(min)}m ${"%02d".format(seg)}s"
    }
}