package exam.blankQuizContext.domain.model.blankQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import exam.blankQuizContext.common.ValueObject;

@Data
@AllArgsConstructor
public class BlankQuizId implements ValueObject<BlankQuizId> {

    private String id;

    @Override
    public boolean sameValueAs(BlankQuizId other) {
        return equals(other);
    }

    @Override
    public String toString() {
        return id;
    }
}
