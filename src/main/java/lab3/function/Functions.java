package lab3.function;


import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

import static java.lang.Math.*;

@Getter
public enum Functions {
    f1(x -> pow(-x, 3) - 2 * pow(x, 2) + 3 * x + 23, "−x^3 − 2x^2 + 3x + 23 dx"),
    f2(x -> pow(x, 3) - x + 4, "x^3 - x + 4 dx"),
    f3(x -> {
        if (x <= 0) throw new RuntimeException("Не удовлетворяет ОДЗ");
        else return log(x);
    },
            "ln(x) dx"),
    f4(x -> sin(14 * x + 88), "sin(14x + 88) dx "),
    f5(x -> exp(x / 1.4) + 8.8, "e^(x / 1.4) + 8.8 dx");
    private Function<Double, Double> function;
    private String textView;
    private double left;
    private double right;
    @Setter
    private double epsilon;

    Functions(Function<Double, Double> function, String textView) {
        this.function = function;
        this.textView = textView;
    }

    public void setLimits(double a, double b) {
        this.left = a;
        this.right = b;
    }

}
