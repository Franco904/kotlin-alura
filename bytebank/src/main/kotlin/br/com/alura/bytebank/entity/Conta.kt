package br.com.alura.bytebank.entity

open class Conta(
    val titular: String,
    val numero: Int,
) {
    var saldo: Double = 0.0

    init {
        totalContas += 1
    }

    open fun depositar(valor: Double) {
        saldo += valor
    }

    open fun sacar(valor: Double) {
        if (saldo >= valor) {
            saldo -= valor
        }
    }

    companion object {
        var totalContas: Int = 0
            private set
    }
}