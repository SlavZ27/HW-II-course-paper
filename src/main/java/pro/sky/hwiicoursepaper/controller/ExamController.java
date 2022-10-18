package pro.sky.hwiicoursepaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.service.ExaminerServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/get", params = "amount")
    public List<Question> getListQuestions(@RequestParam("amount") Integer amount) {
        return examinerService.getQuestions(amount);
    }

}
