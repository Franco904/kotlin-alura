package br.com.alura.bytebank.util

import java.math.BigDecimal

object BigDecimalUtils {
    fun bigDecimalArrayOf(vararg values: String): Array<BigDecimal> {
        return Array(values.size) { i -> values[i].toBigDecimal() }
    }

    private fun Array<BigDecimal>.sum(): BigDecimal {
        return reduce { total, current -> total + current }
    }

    fun Array<BigDecimal>.average(): BigDecimal {
        return if (this.isEmpty()) {
            BigDecimal.ZERO
        } else {
            sum() / size.toBigDecimal()
        }
    }
}