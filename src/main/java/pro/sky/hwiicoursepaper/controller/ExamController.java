package pro.sky.hwiicoursepaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.service.ExaminerService;

import java.util.List;

@RestController
@RequestMapping()
public class ExamController {
    private final ExaminerService examinerServiceImpl;

    public ExamController(ExaminerService examinerServiceImpl) {
        this.examinerServiceImpl = examinerServiceImpl;
    }

    @GetMapping("/get")
    public List<Question> getListQuestions(@RequestParam("amount") Integer amount) {
        return examinerServiceImpl.getQuestions(amount);
    }
}
