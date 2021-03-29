package lab3;

import lab3.function.Answer;
import lab3.function.Functions;
import lab3.method.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Slf4j
public class Main {
    private static final String commandFormat = "CM_Lab3 [-lrmts] [-i file_path] [-o file_path]\n" +
            "-l Метод прямоугольников (левый)\n" +
            "-r Метод прямоугольников (правый)\n" +
            "-m Метод прямоугольников (средний)\n" +
            "-t Метод трапеций\n" +
            "-s Метод Симпсона";
    private static SolvingMethod method;
    private static Functions function;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            configure(args);
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

    @SneakyThrows
    private static void configure(String[] args) {
        if (args.length != 1)
            throw new RuntimeException("Неверное количество аргументов\n" + commandFormat);

        switch (args[0]) {
            case "-l":
                method = new RectangleLeftMethod();
                break;
            case "-m":
                method = new RectangleMiddleMethod();
                break;
            case "-r":
                method = new RectangleRightMethod();
                break;
            case "-t":
                method = new TrapeziumMethod();
                break;
            case "-s":
                method = new SimpsonMethod();
                break;
            default:
                throw new RuntimeException("Неверный формат команды\n" + commandFormat);
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
