package exam.blankQuizContext.application;

import exam.blankQuizContext.domain.model.blankQuiz.Answer;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuiz;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizId;
import exam.blankQuizContext.domain.model.blankQuiz.Question;
import exam.blankQuizContext.infrastructure.MemoryBlankQuizRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class BlankQuizServiceTest {

    MemoryBlankQuizRepository memoryBlankQuizRepository = new MemoryBlankQuizRepository();

    BlankQuizService blankQuizService = new BlankQuizService(memoryBlankQuizRepository);

    private BlankQuizId existBlankQuizId = new BlankQuizId("blankQuiz-a4c68d5d-6c18-4707-b8c2-1fd18846ebf2");

    private String teacherId = "teacher-a4c68d5d-6c18-4707-b8c2-1fd18846ebf2";

    private SaveBlankQuizCommand createCommand;

    private SaveBlankQuizCommand reviseCommand;

    @Before
    public void setUp(){
        final BlankQuizId blankQuizId = existBlankQuizId;
        BlankQuiz blankQuiz = BlankQuiz.builder().question(new Question("First one?"))
                .answer(new Answer("yes"))
                .blankQuizId(blankQuizId)
                .teacherId(teacherId)
                .score(15).build();
        memoryBlankQuizRepository.getBlankQuizMap().put(blankQuizId, blankQuiz);

        createCommand = new SaveBlankQuizCommand(
                new exam.blankQuizContext.application.SaveBlankQuizCommand.Question("What is this repo?"),
                new exam.blankQuizContext.application.SaveBlankQuizCommand.Answer("BlankQuiz"),
                15, teacherId);
        reviseCommand = new SaveBlankQuizCommand(
                new exam.blankQuizContext.application.SaveBlankQuizCommand.Question("What is this repo?"),
                new exam.blankQuizContext.application.SaveBlankQuizCommand.Answer("BlankQuiz"),
                25, teacherId);
    }

    @Test
    public void should_create_with_command() {
        BlankQuizId blankQuizId = blankQuizService.create(createCommand);
        assertThat(memoryBlankQuizRepository.find(blankQuizId).getBlankQuizId(), is(blankQuizId));
    }

    @Test
    public void should_revise_with_blankQuizId_and_command() {
        blankQuizService.revise(existBlankQuizId.getId(), reviseCommand);
        assertThat(memoryBlankQuizRepository.find(existBlankQuizId).getScore(), is(25));
    }

    @Test
    public void should_remove_with_blankQuizId() {
        blankQuizService.remove(existBlankQuizId.getId());
        assertThat(blankQuizService.getAll().size(), is(0));
    }

    @Test
    public void should_getAll() {
        List<BlankQuiz> list = memoryBlankQuizRepository.getAll();
        assertThat(list.size(), is(1));
    }
}