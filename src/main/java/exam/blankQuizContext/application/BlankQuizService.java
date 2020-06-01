package exam.blankQuizContext.application;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuiz;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizId;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service
public class BlankQuizService {
    
    private final BlankQuizRepository blankQuizRepository;

    @Autowired
    public BlankQuizService(BlankQuizRepository blankQuizRepository) {
        this.blankQuizRepository = blankQuizRepository;
    }

    public BlankQuizId create(SaveBlankQuizCommand command) {
        BlankQuiz blankQuiz = BlankQuiz.builder().build();
        BeanUtils.copyProperties(command, blankQuiz);
        final BlankQuizId blankQuizId = blankQuizRepository.nextBlankQuizId();
        blankQuiz.setBlankQuizId(blankQuizId);
        blankQuiz.setCreateTime(LocalDateTime.now());
        blankQuizRepository.save(blankQuiz);
        return blankQuizId;
    }

    public void revise(String blankQuizId, SaveBlankQuizCommand command) {
        final BlankQuiz blankQuiz = blankQuizRepository.find(new BlankQuizId(blankQuizId));
        if (Objects.nonNull(blankQuiz)) {
            blankQuiz.setUpdateTime(LocalDateTime.now());
            BeanUtils.copyProperties(command, blankQuiz);
        }
    }

    public void remove(String blankQuizId){
        blankQuizRepository.delete(new BlankQuizId(blankQuizId));
    }

    public List<BlankQuiz> getAll() {
        return blankQuizRepository.getAll();
    }
}
