package br.com.alura.bytebank.contract

import br.com.alura.bytebank.entity.Conta

interface Transferivel {
    fun transferir(valor: Double, destino: Conta)
}