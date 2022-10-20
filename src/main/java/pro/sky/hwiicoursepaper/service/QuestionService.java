package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Collection;

@Service
public interface QuestionService {

    Question add(String question, String answer);


    Question add(Question question);


    Question remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandom();
}
