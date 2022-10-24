package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.List;

@Service
public interface ExaminerService {

    List<Question> getQuestions(int amount);
    }
