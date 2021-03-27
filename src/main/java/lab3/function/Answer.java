package lab3.function;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private double answer;
    private int partition;

    public Answer(double answer, int partition) {
        this.answer = answer;
        this.partition = partition;
    }

}
