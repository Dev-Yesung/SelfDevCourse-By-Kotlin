package calculator

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ProcessorSuccessTest {

    private val processor = Processor()
    private lateinit var outputStream: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
    }

    @CsvSource(
        "0 + 1, 0 + 1 = 1",
        "5 + 6, 5 + 6 = 11",
        "4 + 24, 4 + 24 = 28",
        "-3 + -4, -3 + -4 = -7",
        "-33 + 44, -33 + 44 = 11"
    )
    @ParameterizedTest
    fun `표준입력으로 올바른 덧셈식이 들어왔을 때, 덧셈 결과를 표준출력한다`(expression: String, expectedResult: String) {
        // given
        setInExpression(expression)

        // when
        processor.run()
        val actualResult = outputStream.toString()

        // then
        assertTrue(actualResult.contains(expectedResult))
    }

    @CsvSource(
        "0 - 1, 0 - 1 = -1",
        "5 - 6, 5 - 6 = -1",
        "4 - 24, 4 - 24 = -20",
        "-3 - -4, -3 - -4 = 1",
        "-33 - 44, -33 - 44 = -77"
    )
    @ParameterizedTest
    fun `표준입력으로 올바른 뺄셈식이 들어왔을 때, 뺄셈 결과를 표준출력한다`(expression: String, expectedResult: String) {
        // given
        setInExpression(expression)

        // when
        processor.run()
        val actualResult = outputStream.toString()

        // then
        assertTrue(actualResult.contains(expectedResult))
    }

    @CsvSource(
        "0 * 1, 0 * 1 = 0",
        "5 * 6, 5 * 6 = 30",
        "4 * 24, 4 * 24 = 96",
        "-3 * -4, -3 * -4 = 12",
        "-33 * 44, -33 * 44 = -1452"
    )
    @ParameterizedTest
    fun `표준입력으로 올바른 곱셈식이 들어왔을 때, 곱셈 결과를 표준출력한다`(expression: String, expectedResult: String) {
        // given
        setInExpression(expression)

        // when
        processor.run()
        val actualResult = outputStream.toString()

        // then
        assertTrue(actualResult.contains(expectedResult))
    }

    @CsvSource(
        "0 / 1, 0 / 1 = 0.0",
        "5 / 2, 5 / 2 = 2.5",
        "4 / 20, 4 / 20 = 0.2",
        "-3 / -4, -3 / -4 = 0.75",
        "-30 / 40, -30 / 40 = -0.75"
    )
    @ParameterizedTest
    fun `표준입력으로 올바른 나눗셈식이 들어왔을 때, 나눗셈 결과를 표준출력한다`(expression: String, expectedResult: String) {
        // given
        setInExpression(expression)

        // when
        processor.run()
        val actualResult = outputStream.toString()

        // then
        assertTrue(actualResult.contains(expectedResult))
    }

    private fun setInExpression(expression: String) {
        val input = ByteArrayInputStream(expression.toByteArray())
        System.setIn(input)
    }
}
