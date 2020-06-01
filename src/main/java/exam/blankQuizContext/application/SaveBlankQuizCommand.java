package exam.blankQuizContext.application;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SaveBlankQuizCommand {
    @NotNull
    private Question question;
    @NotNull
    private Answer answer;
    @NotNull
    @Size(min = 1, max = 96)
    private Integer score;
    @NotNull
    private String teacherId;

    public static class Question{
        @NotNull
        private String question;
    }

    public static class Answer{
        @NotNull
        private String answers;
    }
}
