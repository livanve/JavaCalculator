import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        testAddition(calculator);
        testSubtraction(calculator);
        testMultiplication(calculator);
        testDivision(calculator);
        testPercentage(calculator);
        testSquareRoot(calculator);
        testDecimalInput(calculator);
        testZeroDivision(calculator);
    }

    private static void testAddition(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.valueOf(5);

        BigDecimal expectedResult = num1.add(num2);

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '+');
        assert result.equals(expectedResult) : "Addition test failed";
        System.out.println("Addition test passed");
    }

    private static void testSubtraction(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.valueOf(5);

        BigDecimal expectedResult = num1.subtract(num2);

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '-');
        assert result.equals(expectedResult) : "Subtraction test failed";
        System.out.println("Subtraction test passed");
    }

    private static void testMultiplication(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.valueOf(5);

        BigDecimal expectedResult = num1.multiply(num2);

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '*');
        assert result.equals(expectedResult) : "Multiplication test failed";
        System.out.println("Multiplication test passed");
    }

    private static void testDivision(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.valueOf(5);

        BigDecimal expectedResult = num1.divide(num2, 10, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '/');
        assert result.equals(expectedResult) : "Division test failed";
        System.out.println("Division test passed");
    }

    private static void testPercentage(Calculator calculator) {
        BigDecimal num = BigDecimal.valueOf(100);

        BigDecimal expectedResult = num.divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);

        BigDecimal result = calculator.calculateAndDisplay(num, BigDecimal.ZERO, '%');
        assert result.equals(expectedResult) : "Percentage test failed";
        System.out.println("Percentage test passed");
    }

    private static void testSquareRoot(Calculator calculator) {
        BigDecimal num = BigDecimal.valueOf(25);

        BigDecimal expectedResult = BigDecimal.valueOf(5);

        BigDecimal result = calculator.calculateAndDisplay(num, BigDecimal.ZERO, '√');
        assert result.equals(expectedResult) : "Square Root test failed";
        System.out.println("Square Root test passed");
    }

    private static void testDecimalInput(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(3.5);
        BigDecimal num2 = BigDecimal.valueOf(2.5);

        BigDecimal expectedResult = num1.add(num2);

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '+');
        assert result.equals(expectedResult) : "Decimal Input test failed";
        System.out.println("Decimal Input test passed");
    }

    private static void testZeroDivision(Calculator calculator) {
        BigDecimal num1 = BigDecimal.valueOf(10);
        BigDecimal num2 = BigDecimal.ZERO;

        BigDecimal result = calculator.calculateAndDisplay(num1, num2, '/');
        assert result.equals(BigDecimal.ZERO) : "Zero Division test failed";
        System.out.println("Zero Division test passed");
    }
}
