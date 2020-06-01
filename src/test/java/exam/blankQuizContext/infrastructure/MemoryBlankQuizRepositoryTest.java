package exam.blankQuizContext.infrastructure;

import exam.blankQuizContext.domain.model.blankQuiz.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class MemoryBlankQuizRepositoryTest {

    MemoryBlankQuizRepository memoryBlankQuizRepository = new MemoryBlankQuizRepository();

    private BlankQuizId existBlankQuizId = new BlankQuizId("blankQuiz-a4c68d5d-6c18-4707-b8c2-1fd18846ebf2");

    @Before
    public void setUp(){
        final BlankQuizId blankQuizId = existBlankQuizId;
        BlankQuiz blankQuiz = BlankQuiz.builder().question(new Question("First one?"))
                .answer(new Answer("yes"))
                .blankQuizId(blankQuizId)
                .teacherId("teacher-a4c68d5d-6c18-4707-b8c2-1fd18846ebf2")
                .score(15).build();
        memoryBlankQuizRepository.getBlankQuizMap().put(blankQuizId, blankQuiz);
    }

    @Test
    public void should_find_with_BlankQuizId(){
        BlankQuiz blankQuiz = memoryBlankQuizRepository.find(existBlankQuizId);
        assertThat(memoryBlankQuizRepository.getBlankQuizMap().get(existBlankQuizId), is(blankQuiz));
    }

    @Test
    public void should_create_blankQuiz_with_new() {
        final BlankQuizId blankQuizId = memoryBlankQuizRepository.nextBlankQuizId();
        BlankQuiz blankQuiz = BlankQuiz.builder().question(new Question("What is this repo?"))
                .answer(new Answer("BlankQuiz"))
                .blankQuizId(blankQuizId)
                .teacherId("teacher-a4c68d5d-6c18-4707-b8c2-1fd18846ebf2")
                .score(5).build();
        memoryBlankQuizRepository.save(blankQuiz);
        assertThat(memoryBlankQuizRepository.getBlankQuizMap().get(blankQuizId).getBlankQuizId(), is(blankQuizId));
    }

    @Test
    public void should_get_All_blankQuiz_List(){
        List<BlankQuiz> list = memoryBlankQuizRepository.getAll();
        assertThat(list.size(), is(1));
    }

    @Test
    public void should_delete_by_blankQuizId(){
        memoryBlankQuizRepository.delete(existBlankQuizId);
        assertThat(memoryBlankQuizRepository.getAll().size(), is(0));
    }

}