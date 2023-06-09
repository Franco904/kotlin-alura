package br.com.alura.bytebank.exceptions

class SaldoInsuficienteException : Exception(
    message = "O saldo desta conta é insuficiente para realizar a transferência solicitada.",
) {
    override fun printStackTrace() {
        println("[SaldoInsuficienteException] Stacktrace:")
        super.printStackTrace()
    }
}