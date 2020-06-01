package exam.blankQuizContext.domain.model.blankQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import exam.blankQuizContext.common.ValueObject;

@Data
@AllArgsConstructor
public class Answer implements ValueObject<Answer> {

    private String answer;

    @Override
    public boolean sameValueAs(Answer other) {
        return equals(other);
    }
}
