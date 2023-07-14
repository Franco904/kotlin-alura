package br.com.alura.bytebank.util

import br.com.alura.bytebank.entity.Livro
import br.com.alura.bytebank.util.BigDecimalUtils.average
import java.math.BigDecimal
import java.math.RoundingMode

fun createCollections() {
//    createAges()
//    createWages()

//    handleRanges()
//    handleAggregation()
//    handleObjectArrays()
//    handleSort()

    createBooksLists()
}

fun createAges() {
    val ages = intArrayOf(19, 38, 3, 75, 47)

    var greater = 0
    var smallest = Int.MAX_VALUE

    // Com laço for
    for (age in ages) {
        if (age > greater) {
            greater = age
        }
    }

    // Com função forEach
    ages.forEach { age ->
        if (age < smallest) {
            smallest = age
        }
    }

    println(greater)
    println(smallest)
}

fun createWages() {
    val wages: DoubleArray = doubleArrayOf(1050.0, 2300.05, 12500.0, 8437.9, 3670.0)

    val increase = 1.1
    for (index in wages.indices) {
        wages[index] *= increase
    }

    println(wages.contentToString())

    for ((i, _) in wages.withIndex()) {
        wages[i] *= increase
    }

    println(wages.contentToString())

    wages.forEachIndexed { i, _ ->
        wages[i] *= increase
    }

    println(wages.contentToString())
}

fun handleRanges() {
    val serie: IntRange = 1.rangeTo(100)

    for (s in serie.step(2)) {
        println("$s")
    }

    // Ou
    for (s in 1..100 step 2) {
        println("$s")
    }

    // Não inclui o 10 (fim exclusivo)
    for (s in 1.until(10)) {
        println("$s")
    }

    // Range reverso
    for (s in 100 downTo 1 step 2) {
        println("$s")
    }

    // Operador "in" usado no contexto de Contém ou não contém
    val wageRange = 1400..3200
    val wage = 2300
    if (wage in wageRange) println("Dentro do intervalo de salários")

    // CharRange
    val alphabet = 'a'..'z'
    if ('k' in alphabet) println("k está dentro do alfabeto")
    if ('ç' in alphabet) println("ç está dentro do alfabeto")
}

fun handleAggregation() {
    val ages = intArrayOf(19, 38, 3, 75, 47)

    // Agregação
    println(ages.max())
    println(ages.min())
    println(ages.sum())
    println(ages.average())

    println(ages.all { it >= 18 })
    println(ages.any { it >= 18 })
    println(ages.filter { it >= 18 })
    println(ages.find { it >= 40 })

    println(ages.reduce { total, current -> total + current })

    // Como se fosse um reduce
    println(ages.fold(initial = ages.sum()) { total, current -> total + current })
    println(ages.count { it > 30 })
}

fun handleObjectArrays() {
    // init: Lambda que gera os valores iniciais em cada posição do array
    val wages = Array<BigDecimal>(size = 5) { BigDecimal.ZERO }

    /* Recomendação é converter de string e não de double
    porque double possui problema de arredondamento na representação */
    wages[0] = "1550.55".toBigDecimal()
    wages[1] = "2350.05".toBigDecimal()
    wages[2] = "15000.00".toBigDecimal()

    println(wages.contentToString())

    // Ou
    val wagesBetter = BigDecimalUtils.bigDecimalArrayOf("1550.55", "2350.05", "15000.00")
    println(wagesBetter.contentToString())

    val increase = "1.15".toBigDecimal()
    val increasedWages: Array<BigDecimal> = wages
        .map { wage ->
            val increasedWage = if (wage < "8000".toBigDecimal()) wage * increase else wage
            increasedWage.setScale(2, RoundingMode.UP)
        }
        .toTypedArray()

    println(increasedWages.contentToString())
}

fun handleSort() {
    val wages = BigDecimalUtils.bigDecimalArrayOf("1550.55", "2350.05", "15000.00", "2400.0", "984.56")

    val threeHighest = wages.sorted().takeLast(3).toTypedArray()
    println(threeHighest.contentToString())

    val wageAverage = threeHighest.average()
    println(wageAverage)
}

fun createBooksLists() {
    val books = mutableListOf(
        Livro("Iracema", "José de Alencar", 1887),
        Livro("Livro dois", "Qualquer autor", 2000),
        Livro("Livro 3", "Outro autor", 2020),
        Livro("Camelo do Deserto", "Kun Iomie", 2021, "Sextante"),
        null,
    )

    books.add(Livro("Sagarana", "João Guimarães", 1883, "Sextante"))

    books.removeAt(2)
    books.remove(books[1])

    // Passa a permitir o sorted quando sobrescrevemos o compareTo na classe Livro
//    val booksSortedByYear = books.sorted().toMutableList()
//    println(booksSortedByYear.withMarkers())

    // Usando sortBy
    books.sortBy { it?.anoPublicacao }

    println(books.filter { it?.editora == "Sextante" }.toMutableList().withMarkers())

    books
        .filterNotNull()
        .groupBy { it.editora ?: "Não informada" }
        .forEach { (editora, booksWithEditora) ->
            println("Editora: $editora | Livros: ${booksWithEditora.joinToString { it.titulo }}")
        }
}

fun MutableList<Livro?>.withMarkers(): String {
    return filterNotNull().joinToString(separator = "\n") { " - ${it.titulo} de ${it.autor}" }
}

fun testCollectionsCopy() {
    val controller = NamesController()
    controller.addName("Franco")

    controller.addName("Remover")
    controller.removeName("Remover")

    val a = controller.names
}

class NamesController {
    val names: Collection<String> get() = _names.toList()

    fun addName(name: String) {
        _names.add(name)
    }

    fun removeName(name: String) {
        _names.remove(name)
    }

    companion object {
        private val _names = mutableListOf<String>()
    }
}

fun handleSets() {
    val assisteAndroid = setOf("Franco", "Augusto", "Mamá", "Mamá")
    val assisteKotlin = setOf("Henrique", "Pedro", "Franco")

    val assisteAoMenosUm = assisteAndroid union assisteKotlin
    val assisteAmbos = assisteAndroid intersect assisteKotlin

    val assisteSoKotlin = assisteKotlin subtract assisteAndroid
    val assisteSoAndroid = assisteAndroid subtract assisteKotlin

    println(assisteAoMenosUm)
    println(assisteAmbos)

    println(assisteSoKotlin)
    println(assisteSoAndroid)
}

fun handleMaps() {
    val mapExample1 = mapOf(Pair("Número 1", 7), Pair("Número 2", 2), Pair("Número 3", 16))
    val mapExample2 = mutableMapOf("1" to 1, "2" to 12, "3" to 41)

    println(mapExample1)
    println(mapExample2)

    println(mapExample1["Número 1"]) // 7
    println(mapExample1["1"]) // null
    println(mapExample2["1"]) // 1

    for (entry in mapExample1) {
        println("Key: ${entry.key}, Value: ${entry.value}")
    }

    mapExample2["4"] = 70
    mapExample2.putIfAbsent("4", 6)
    mapExample2.remove("2")

    for (entry in mapExample2) {
        println("Key: ${entry.key}, Value: ${entry.value}")
    }

    val valueDefault = mapExample2.getOrDefault("16", 50)
    val valueElse = mapExample2.getOrElse("16") { 25 }

    println(valueDefault)
    println(valueElse)

    val keys = mapExample2.keys
    val values = mapExample2.values

    println(keys)
    println(values)

    val evenValues = mapExample2.filterValues { it % 2 == 0 }
    println(evenValues)

    println(mapExample2 + Pair("45", 67))
    println(mapExample2 - "3")

    mapExample2 += setOf("9" to 55, "18" to 92) // putAll
    mapExample2.values.remove(1)
    println(mapExample2)

    val pedidos = listOf(
        Pedido(1, "Pedido 1", 15.0),
        Pedido(2, "Pedido 2", 17.5),
        Pedido(3, "Pedido 3", 99.9),
        Pedido(4, "Pedido 4", 1.99),
    )

    val pedidosMap = pedidos.associate { (it.numero to (it.valor > 50)) }
    println(pedidosMap)

    val pedidosByNumero = pedidos.associateBy { it.numero }
    println(pedidosByNumero)

    val pedidosWithValorCondition = pedidos.associateWith { it.valor > 50 }
    println(pedidosWithValorCondition)

    val enumMap: Map<PessoaEnum, Int?> = PessoaEnum.values().associateWith { null }
    println(enumMap)

    val pedidosGroupedValorCondition = pedidos.groupBy { it.valor > 50 }
    println(pedidosGroupedValorCondition)

    val pedidosGrouping = pedidos.groupingBy { it.valor > 50 }
    println(pedidosGrouping.eachCount())
}

data class Pedido(
    val numero: Int,
    val nome: String,
    val valor: Double,
)

enum class PessoaEnum {
    PESSOA,
    INDIVIDUO,
    CIDADAO,
}