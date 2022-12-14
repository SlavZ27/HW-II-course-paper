package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Set;

@Service
public interface QuestionService {

    Question add(String question, String answer);


    Question add(Question question);


    Question remove(String question, String answer);

    Set<Question> getAll();

    Question getRandom();
}
