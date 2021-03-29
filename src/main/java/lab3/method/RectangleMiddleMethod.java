package lab3.method;

import java.util.function.Function;

public class RectangleMiddleMethod implements SolvingMethod {
    @Override
    public double getIntegral(int n, double a, double b, Function<Double, Double> function) {
        double h = (b - a) / n;
        double[] y = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            y[i] = function.apply(a + i * h - 1 / 2 * h);
        }
        double I0 = 0;
        for (int i = 1; i <= n; i++) {
            I0 += h * y[i];
        }
        return I0;
    }
}
