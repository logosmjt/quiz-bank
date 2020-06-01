package exam.blankQuizContext.domain.model.blankQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import exam.blankQuizContext.common.ValueObject;


@Data
@AllArgsConstructor
public class Question implements ValueObject<Question> {

    private String question;

    @Override
    public boolean sameValueAs(Question other) {
        return equals(other);
    }
}
