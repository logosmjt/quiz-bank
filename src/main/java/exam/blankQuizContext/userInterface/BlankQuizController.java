package exam.blankQuizContext.userInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import exam.blankQuizContext.application.SaveBlankQuizCommand;
import exam.blankQuizContext.application.BlankQuizService;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuiz;
import exam.blankQuizContext.domain.model.blankQuiz.BlankQuizId;

import java.util.List;

@RestController
public class BlankQuizController {

    private BlankQuizService blankQuizService;

    @Autowired
    public BlankQuizController(BlankQuizService blankQuizService) {
        this.blankQuizService = blankQuizService;
    }

    @PostMapping("/blankQuizzes")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    BlankQuizDTO create(@RequestBody @Validated SaveBlankQuizCommand command) {
        final BlankQuizId blankQuizId = blankQuizService.create(command);
        return BlankQuizDTO.from(blankQuizId);
    }

    @GetMapping("/blankQuizzes")
    List<BlankQuiz> getAll() {
        return blankQuizService.getAll();
    }

    @PutMapping("/blankQuizzes/{blankQuizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void revise(@PathVariable String blankQuizId, @RequestBody @Validated SaveBlankQuizCommand command) {
        blankQuizService.revise(blankQuizId, command);
    }

    @DeleteMapping("/blankQuizzes/{blankQuizId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remove(@PathVariable String blankQuizId) {
        blankQuizService.remove(blankQuizId);
    }
}
