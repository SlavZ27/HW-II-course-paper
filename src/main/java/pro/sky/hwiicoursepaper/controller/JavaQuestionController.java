package pro.sky.hwiicoursepaper.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.service.QuestionService;

import java.util.Set;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final QuestionService javaQuestionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Set<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping(path = "/add", params = {"question", "answer"})
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return javaQuestionService.add(question, answer);
    }

    @GetMapping(path = "/remove", params = {"question", "answer"})
    public Question removeQuestions(@RequestParam("question") String question,
                                        @RequestParam("answer") String answer) {
        return javaQuestionService.remove(question, answer);
    }
}
