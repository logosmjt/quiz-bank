package exam.blankQuizContext.userInterface;

import lombok.AllArgsConstructor;
import lombok.Getter;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizId;

@Getter
@AllArgsConstructor
public class BlankQuizDTO {
    private String uri;

    public static BlankQuizDTO from(BlankQuizId blankQuizId) {
        return new BlankQuizDTO("blank_quiz/" + blankQuizId);
    };
}
