package calculator

class Processor {

    fun run() {
        do {
            val expression = inputExpression() ?: break
            val tokens = tokenizeExpression(expression)
            val result = calculate(tokens)
            printResult(expression, result)
        } while (true)
    }

    private fun tokenizeExpression(expression: String): List<String> {
        return expression.split(" ")

    }

    private fun inputExpression(): String? {
        return readlnOrNull()
    }

    private fun add(leftOperand: Int, rightOperand: Int): String {
        return (leftOperand + rightOperand).toString()
    }

    private fun subtract(leftOperand: Int, rightOperand: Int): String {
        return (leftOperand - rightOperand).toString()
    }

    private fun multiply(leftOperand: Int, rightOperand: Int): String {
        return (leftOperand * rightOperand).toString()
    }

    private fun divide(leftOperand: Int, rightOperand: Int): String {
        return (leftOperand / rightOperand.toDouble()).toString()
    }

    private fun calculate(tokens: List<String>): String {
        val leftOperand = tokens[0].toInt()
        val operator = tokens[1]
        val rightOperand = tokens[2].toInt()

        return when (operator) {
            "+" -> add(leftOperand, rightOperand)
            "-" -> subtract(leftOperand, rightOperand)
            "*" -> multiply(leftOperand, rightOperand)
            "/" -> divide(leftOperand, rightOperand)
            else -> throw IllegalArgumentException("[ERROR] 허용되지 않은 연산자입니다. -> 입력된 연산자: $operator")
        }
    }

    private fun printResult(expression: String, result: String) {
        println("$expression = $result")
    }
}
