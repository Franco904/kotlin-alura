package entity

import br.com.alura.bytebank.entity.Conta
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ContaTest {
    @Test
    fun `Deve instanciar conta com os valor iniciais corretos`() {
        val conta = Conta(
            titular = "Franco",
            numero = 1000
        )

        assertEquals("Franco", conta.titular)
        assertEquals(1000, conta.numero)
        assertEquals(0.0, conta.saldo)
    }
}