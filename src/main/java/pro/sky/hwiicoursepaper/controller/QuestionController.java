package pro.sky.hwiicoursepaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwiicoursepaper.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/exam/get", params = "amount")
    public List<String> getListQuestions(@RequestParam("amount") Integer amount) {
        return questionService.getListQuestions(amount);
    }
}
