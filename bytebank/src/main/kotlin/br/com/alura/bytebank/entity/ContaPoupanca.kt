package br.com.alura.bytebank.entity

import br.com.alura.bytebank.contract.Transferivel
import br.com.alura.bytebank.exceptions.SaldoInsuficienteException

class ContaPoupanca(
    titular: String,
    numero: Int,
): Conta(titular, numero), Transferivel {
    override fun sacar(valor: Double) {
        val valorComTaxa = valor + valor * 0.02

        super.sacar(valorComTaxa)
    }

    override fun transferir(valor: Double, destino: Conta) {
        if (saldo < valor) throw SaldoInsuficienteException()

        saldo -= valor
        destino.depositar(valor)
    }
}