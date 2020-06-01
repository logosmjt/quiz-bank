package exam.blankQuizContext.infrastructure;

import lombok.Getter;
import org.springframework.stereotype.Component;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuiz;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizId;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizRepository;

import java.util.*;

@Component
@Getter

public class MemoryBlankQuizRepository implements BlankQuizRepository {

    private Map<BlankQuizId, BlankQuiz> blankQuizMap = new HashMap();

    @Override
    public BlankQuiz find(BlankQuizId blankQuizId) {
        return blankQuizMap.get(blankQuizId);
    }

    @Override
    public void save(BlankQuiz blankQuiz) {
        blankQuizMap.put(blankQuiz.getBlankQuizId(), blankQuiz);
    }

    @Override
    public BlankQuizId nextBlankQuizId() {
        return new BlankQuizId("blankQuiz-" + UUID.randomUUID().toString());
    }

    @Override
    public List<BlankQuiz> getAll() {
        return new ArrayList<>(blankQuizMap.values());
    }

    @Override
    public void delete(BlankQuizId blankQuizId) {
        blankQuizMap.remove(blankQuizId);
    }
}
