package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.List;

@Service
public interface QuestionService {

    Question add(String question, String answer);


    Question add(Question question);


    Question remove(Question question);

    List<Question> getAll();

    Question getRandom();
}
