package br.com.alura.bytebank.entity

class Colaborador(
    nome: String,
    cpf: String,
    salario: Double,
) : Funcionario(
    nome = nome,
    cpf = cpf,
    salario = salario,
) {
    override val bonificacao: Double
        get() = salario * 0.1
}