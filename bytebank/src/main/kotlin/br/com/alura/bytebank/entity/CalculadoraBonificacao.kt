package br.com.alura.bytebank.entity

class CalculadoraBonificacao {
    var total: Double = 0.0
        private set

    fun registrar(funcionario: Funcionario) {
        total += funcionario.bonificacao
    }
}