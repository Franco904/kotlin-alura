package br.com.alura.bytebank.entity

var totalContas = 0
    private set

class ContaSalario(
    titular: String,
    numero: Int,
): Conta(titular, numero) {
    override fun sacar(valor: Double) {
        val valorComTaxa = valor + valor * 0.01

        super.sacar(valorComTaxa)
    }
}
