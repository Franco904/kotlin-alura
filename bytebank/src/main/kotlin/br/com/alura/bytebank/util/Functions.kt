package br.com.alura.bytebank.util

import br.com.alura.bytebank.contract.Transferivel
import br.com.alura.bytebank.entity.*
import br.com.alura.bytebank.exceptions.SaldoInsuficienteException

fun testConditions(saldo: Double) {
    when {
        saldo > 0.0 -> println("Conta é positiva!")
        saldo == 0.0 -> println("Conta é neutra.")
        else -> println("Conta é negativa.")
    }
}

fun iterateLoop() {
    outer_for@ for (i in 6 downTo 1) {
        for (j in 1..4) {
            if (j == 3) {
                break@outer_for
            }
            println("Vez de $j !")
        }
        println("Conta $i criada!")
    }

    var i = 0
    var atualiza = 10

    while (i < 5) {
        atualiza += i
        i++
    }

    println(atualiza)
}

fun handleFuncionarios() {
    val colaborador = Colaborador(
        nome = "Franco",
        cpf = "128.331.589-04",
        salario = 1000.0,
    )

//    println("Nome do colaborador: ${colaborador.nome}")
//    println("CPF do colaborador: ${colaborador.cpf}")
//    println("Salário do colaborador: ${colaborador.salario}")
//    println("Bonificação do salário do colaborador: ${colaborador.bonificacao}")
//
//    println("\n============================\n")

    val diretor = Diretor(
        nome = "Mendonça",
        cpf = "111.111.111-11",
        salario = 7000.0,
        senha = 123123,
        plr = 250.0,
    )

    val analista = Analista(
        nome = "Maria",
        cpf = "111.222.333-44",
        salario = 4500.0,
        senha = 123123,
    )

//    println("Nome do diretor: ${diretor.nome}")
//    println("CPF do diretor: ${diretor.cpf}")
//    println("Salário do diretor: ${diretor.salario}")
//    println("PLR do diretor: ${diretor.plr}")
//    println("Bonificação do salário do diretor: ${diretor.bonificacao}")
//
//    println("\n============================\n")

    CalculadoraBonificacao().apply {
        registrar(colaborador)
        registrar(diretor)
        registrar(analista)
        println("Bonificação total: $total")
    }


}

fun handleContas() {
    val contaFranco = ContaPoupanca(
        titular = "Franco",
        numero = 1000,
    )

    val contaLaura = Conta(
        titular = "Laura",
        numero = 1001,
    )

    contaFranco.depositar(500.0)
    contaLaura.depositar(500.0)

    contaFranco.sacar(50.0)
    contaLaura.sacar(100.0)

    try {
        contaFranco.transferir(50.0, contaLaura)
    } catch (e: Exception) {
        when (e) {
            is SaldoInsuficienteException -> println(e)
            else -> e.printStackTrace()
        }
    }

    println("Titular da conta 1: ${contaFranco.titular}")
    println("Número da conta 1: ${contaFranco.numero}")
    println("Saldo da conta 1: ${contaFranco.saldo}")

    println("Titular da conta 2: ${contaLaura.titular}")
    println("Número da conta 2: ${contaLaura.numero}")
    println("Saldo da conta 2: ${contaLaura.saldo}")

    testConditions(contaFranco.saldo)
    iterateLoop()

    Testes.testaSingleton()

    val ob: Transferivel = object : Transferivel {
        override fun transferir(valor: Double, destino: Conta) {
            TODO("Not yet implemented")
        }
    }

    println("Total de contas: ${Conta.totalContas}")
}

fun handleEnderecoAny() {
    val endereco = Endereco(cep = "88034530")
    val novoEndereco = Endereco(cep = "88034530")

    val (title, message) = getDialogContent()

    println(endereco == novoEndereco)
    println(endereco.hashCode())
    println(endereco.toString())

    val isCancelling = when(title) {
        "Deseja cancelar a operação?" -> true
        else -> false
    }

    if (isCancelling) return
}

fun getDialogContent(): Pair<String, String> {
    val dialogTitle = "Deseja cancelar a operação?"
    val dialogMessage = "Seus dados não serão perdidos."

    return dialogTitle to dialogMessage
}
