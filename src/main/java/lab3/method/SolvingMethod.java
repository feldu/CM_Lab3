package lab3.method;

import lab3.function.Answer;
import lab3.function.Functions;

import java.util.function.Function;

public interface SolvingMethod {
    default Answer solve(Functions functions) {
        int n = 4;
        double a = functions.getLeft(), b = functions.getRight();
        Function<Double, Double> function = functions.getFunction();
        boolean reverse = false;
        if (a > b) {
            a = functions.getRight();
            b = functions.getLeft();
            reverse = true;
        }
        double I0, I1;
        while (true) {
            I0 = getIntegral(n, a, b, function);
            I1 = getIntegral(n * 2, a, b, function);
            if (Math.abs(I1 - I0) < functions.getEpsilon()) break;
            n *= 2;
        }
        I1 = reverse ? -I1 : I1;
        return new Answer(I1, n);
    }

    double getIntegral(int i, double a, double b, Function<Double, Double> function);
}
