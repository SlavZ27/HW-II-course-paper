package pro.sky.hwiicoursepaper.service;

import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
