package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Collection;

@Service
public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
    }
