package exam.blankQuizContext.domain.model.blankQuiz;

import lombok.Builder;
import lombok.Data;
import exam.blankQuizContext.common.Entity;

import java.time.LocalDateTime;

@Data
@Builder
public class BlankQuiz implements Entity<BlankQuiz> {
    private BlankQuizId blankQuizId;
    private Question question;
    private Answer answer;
    private Integer score;
    private String teacherId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public boolean sameIdentityAs(BlankQuiz other) {
        return blankQuizId.sameValueAs(other.getBlankQuizId());
    }
}
