package br.com.alura.bytebank.util

import kotlin.random.Random

fun handleFunctions() {
    functionsAndLambdas()
    hof()
    scopeFunctions()
    receiver()
}

fun functionsAndLambdas() {
    // Function type variables
    val functionExample: () -> Unit = ::noReturnFun
    println(functionExample)
    functionExample()

    val sumFunction: (Int, Int) -> Int = ::sumNumbers
    println(sumFunction(5, 7))

    // Function type class
    val invoker1 = Invoker()
    invoker1()

    val invoker2 = Invoker()
    invoker2(10)

    val sum = Sum()
    sum(4, 5)

    // Anonymous functions
    val anon: () -> Unit = fun() {
        println("Dentro da função anônima 1")
    }
    anon()

    // Lambdas
    val lambda1: () -> Unit = {
        println("Dentro da lambda 1")
    }
    lambda1()

    // Single param lambda
    val lambda2: (param: Int) -> Int = lambda@{
        if (it > 100) {
            // Label return
            return@lambda it + 50
        }

        it + 100
    }
    println(lambda2(200))
    println(lambda2(2))

    // Two params lambda
    val lambda3: (Int, Int) -> Unit = { firstParam: Int, secondParam: Int ->
        println("Sum: ${firstParam + secondParam}")
    }
    lambda3(49, 3)

    // Empty lambda
    val lambda4: () -> Unit = {}
    lambda4()
}

fun hof() {
    // Let
//    getStringOrNull(bool = false)?.let {
//        println("String não é nula")
//    } ?: println("String é nula")

    // Higher-Order (função que recebe outra dentro dela, pode ser construtor de classe tb)
    higherOrderFunctionInlined(1) {
        println("HOF")
    }

    // Crossinline: Proibe retornos da função externa, restringindo-os para a função lambda
    val result = crossinlined(1) {
        // Error: 'return' is not allowed here
        // return true
        true
    }
    println(result)
}

fun scopeFunctions() {
    // Funções de escopo temporário sobre algum objeto Kotlin (aumenta a concisão e legibilidade do código)
    // Objetos de contexto (receiver): referência "this" ou "it" dependendo da função
    //
    // https://kotlinlang.org/docs/scope-functions.html

    // let (verificação de nulidade e inicialização de variável sem acessar seus membros)
    val texto: String? = getStringOrNull(bool = false)
    texto?.let {
        println("String não é nula")
    } ?: println("String é nula")

    val number = 15
    val sum = number.let {
        val innerSum = it + 20

        // retorna o resultado da lambda
        it + innerSum
    }
    println(sum)

    // also (ação adicional sobre alguma ação desempenhada por um método) -> "Também faça..."
    Endereco().also {
        it.mergeRuaBairro()
        // retorna o objeto de contexto
    }.also {
        println(it)
        // retorna o objeto de contexto
    }

    // apply (configuração das propriedades do objeto) -> "Aplique as seguintes atribuições ao objeto"
    Endereco().apply {
        numero = 112
        rua = "Avenida Rio Branco"
        bairro = "Agronômica"

        // retorna o objeto de contexto
    }

    // run
    // extension: configuração do objeto para obter algum resultado computado, e inicialização de variável acessando seus membros
    val endereco = Endereco(numero = 300)
    val numberPlus100PlusNumber = endereco.run {
        val numberUpdated = getNumberPlus100()

        // retorna o resultado da lambda
        numberUpdated + numero
    }
    println(numberPlus100PlusNumber)

    // non-extension: Rodar uma expressão e atribuir o resultado da computação à uma variável (uma função pontual)
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"

        // retorna o resultado da lambda
        Regex("[$sign]?[$digits$hexDigits]+")
    }

    for (match in hexNumberRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
        println(match.value)
    }

    // with (agrupar chamadas de métodos do objeto) -> "com esse objeto, faça..."
    val ruaMerged = with(endereco) {
        // retorna o resultado da lambda
        mergeRuaBairro()
    }
    println(ruaMerged)

    // Guia
    // Chama um ou mais membros do objeto de contexto -> Utilize o objeto de contexto receiver.
    // Não chama membros do objeto do contexto -> Utilize o objeto de contexto como argumento.
    // Não faz a configuração do objeto, inicializações ou chamadas de membros -> Utilize ou let(),
    // para verificação de nulidade, e also(), para demais casos.

    // Outras funções de escopo secundárias
    // takeIf e takeUnless

    val randomNumber = Random.nextInt(100)

    val parOuNull = randomNumber.takeIf { it % 2 == 0 }
    val imparOuNull = randomNumber.takeIf { it % 2 == 0 }
}

fun receiver() {
    val sumNumbersPlus80 = receiverFun(56, 4) {
        this + 80
    }.also {
        println(it)
    }
}

fun receiverFun(a: Int, b: Int, receiverCallback: Int.() -> Int): Int {
    val sum = a + b
    return sum.receiverCallback()
}

fun noReturnFun() {
    println("hello world!")
}

fun sumNumbers(a: Int, b: Int): Int = a + b

class Invoker : () -> Unit {
    operator fun invoke(a: Int) {
        println(a + 10)
    }

    override fun invoke() {
        println("From custom function class")
    }
}

class Sum : (Int, Int) -> Unit {
    override fun invoke(a: Int, b: Int): Unit {
        println("Sum(): ${a + b}")
    }
}

fun getStringOrNull(bool: Boolean): String? {
    return if (bool) "String" else null
}

// Higher-Order Function
// Sobre inline functions (otimização de performance): https://kotlinlang.org/docs/inline-functions.html
// https://stackoverflow.com/questions/44471284/when-to-use-an-inline-function-in-kotlin
inline fun higherOrderFunctionInlined(first: Int, function: () -> Unit) {
    if (first == 1) {
        function()
    }
}

inline fun crossinlined(first: Int, crossinline function: () -> Boolean): Boolean {
    return if (first == 1) {
        function()
    } else {
        false
    }
}

data class Endereco(
    var numero: Int = 142,
    var rua: String = "Rua Itabira",
    var bairro: String = "Coqueiros",
) {
    fun getNumberPlus100(): Int {
        return numero + 100
    }

    fun mergeRuaBairro(): String {
        rua = "$rua, $bairro"
        return rua
    }
}