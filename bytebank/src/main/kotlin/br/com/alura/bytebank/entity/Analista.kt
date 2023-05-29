package br.com.alura.bytebank.entity

class Analista(
    nome: String,
    cpf: String,
    salario: Double,
    private val senha: Int,
): Funcionario(
    nome = nome,
    cpf = cpf,
    salario = salario,
) {
    override val bonificacao get() = salario + (salario * 0.3)
}