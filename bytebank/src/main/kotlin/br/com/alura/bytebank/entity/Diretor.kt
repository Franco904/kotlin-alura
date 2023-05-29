package br.com.alura.bytebank.entity

class Diretor(
    nome: String,
    cpf: String,
    salario: Double,
    private val senha: Int,
    val plr: Double,
) : Funcionario(
    nome = nome,
    cpf = cpf,
    salario = salario,
) {
    override val bonificacao get() = salario + plr

    fun autenticar(senha: Int): Boolean {
        return this.senha == senha
    }
}