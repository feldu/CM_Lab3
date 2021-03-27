package lab3.method;

import lab3.function.Answer;
import lab3.function.Functions;

import java.util.function.Function;

public class TrapeziumMethod implements SolvingMethod {
    @Override
    public Answer solve(Functions functions) {
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

    private double getIntegral(int n, double a, double b, Function<Double, Double> function) {
        double h = (b - a) / n;
        double[] y = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            y[i] = function.apply(a + i * h);
        }
        double I0 = h * (y[0] + y[n]) / 2;
        for (int i = 1; i < n; i++) {
            I0 += h * y[i];
        }
        return I0;
    }
}
