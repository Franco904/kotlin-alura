package br.com.alura.bytebank.entity

data class Endereco(
    private val logradouro: String? = null,
    private val numero: Int? = null,
    private val bairro: String? = null,
    private val cidade: String? = null,
    private val estado: String? = null,
    private val cep: String? = null,
    private val complemento: String? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Endereco

        if (logradouro != other.logradouro) return false
        if (numero != other.numero) return false
        if (bairro != other.bairro) return false
        if (cidade != other.cidade) return false
        if (estado != other.estado) return false
        if (cep != other.cep) return false

        return complemento == other.complemento
    }

    override fun hashCode(): Int {
        var result = logradouro?.hashCode() ?: 0
        result = 31 * result + (numero ?: 0)
        result = 31 * result + (bairro?.hashCode() ?: 0)
        result = 31 * result + (cidade?.hashCode() ?: 0)
        result = 31 * result + (estado?.hashCode() ?: 0)
        result = 31 * result + (cep?.hashCode() ?: 0)
        result = 31 * result + (complemento?.hashCode() ?: 0)

        return result
    }

    override fun toString(): String {
        return """
            Endereco(
                logradouro = '$logradouro',
                numero = '$numero',
                bairro = '$bairro',
                cidade = '$cidade',
                estado = '$estado',
                cep = '$cep',
                complemento = '$complemento',
            )
        """
    }
}