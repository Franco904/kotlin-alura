package br.com.alura.bytebank.entity

data class Livro(
    val titulo: String,
    val autor: String,
    val anoPublicacao: Long,
    val editora: String? = null,
): Comparable<Livro> {
    override fun compareTo(other: Livro): Int = anoPublicacao.compareTo(other.anoPublicacao)
}