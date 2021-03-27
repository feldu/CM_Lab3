package lab3;

import lab3.function.Answer;
import lab3.function.Functions;
import lab3.method.SolvingMethod;
import lab3.method.TrapeziumMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class Main {
    private static SolvingMethod method = new TrapeziumMethod();
    private static Functions function;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            chooseFunction(scanner);
            chooseLimits(scanner);
            System.out.println("Введите эпсилон:");
            function.setEpsilon(scanner.nextDouble());
            Answer answer = method.solve(function);
            System.out.println("Интеграл: " + answer.getAnswer());
            System.out.println("Конечное значение разбиения: " + answer.getPartition());
        } catch (InputMismatchException e) {
            log.error("Incorrect input type");
            System.err.println("Введённые данные некоректны");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void chooseFunction(Scanner scanner) {
        StringBuilder message = new StringBuilder("Выберите функцию:\n");
        for (int i = 0; i < Functions.values().length - 1; i++) {
            message.append(i + 1).append("). ").append(Functions.values()[i].getTextView()).append("\n");
        }
        //fuck \n after last line
        message.append(Functions.values().length).append("). ").append(Functions.values()[Functions.values().length - 1].getTextView());
        try {
            System.out.println(message.toString());
            function = Functions.values()[scanner.nextInt() - 1];
            log.info("Chosen function is: {}", function.getTextView());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Номер функции вне диапазона");
        }
    }

    private static void chooseLimits(Scanner scanner) {
        System.out.println("Введите левую границу: ");
        double a = scanner.nextDouble();
        System.out.println("Введите правую границу: ");
        double b = scanner.nextDouble();
        function.setLimits(a, b);
    }
}
