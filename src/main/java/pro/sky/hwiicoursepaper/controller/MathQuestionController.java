package pro.sky.hwiicoursepaper.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.service.MathQuestionService;

import java.util.Set;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    @Qualifier("mathQuestionService")
    private MathQuestionService mathQuestionService;

    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping
    public Set<Question> getAllQuestions() {
        return mathQuestionService.getAll();
    }

    @GetMapping(path = "/add", params = {"question", "answer"})
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return mathQuestionService.add(question, answer);
    }

    @GetMapping(path = "/remove", params = {"question", "answer"})
    public Question removeQuestions(@RequestParam("question") String question,
                                    @RequestParam("answer") String answer) {
        return mathQuestionService.remove(question, answer);
    }
}
