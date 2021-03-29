package lab3.method;

import java.util.function.Function;

public class RectangleLeftMethod implements SolvingMethod {
    @Override
    public double getIntegral(int n, double a, double b, Function<Double, Double> function) {
        double h = (b - a) / n;
        double[] y = new double[n + 1];
        for (int i = 0; i < n; i++) {
            y[i] = function.apply(a + i * h);
        }
        double I0 = 0;
        for (int i = 0; i < n; i++) {
            I0 += h * y[i];
        }
        return I0;
    }
}
