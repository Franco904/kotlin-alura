package br.com.alura.bytebank.entity

class Gerente(
    nome: String,
    cpf: String,
    salario: Double,
    private val senha: Int,
) : Funcionario(
    nome = nome,
    cpf = cpf,
    salario = salario,
) {
    override val bonificacao
        get() = salario * .5

    fun autenticar(senha: Int): Boolean {
        return this.senha == senha
    }
}