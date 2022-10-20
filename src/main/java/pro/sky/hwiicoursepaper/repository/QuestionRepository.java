package pro.sky.hwiicoursepaper.repository;

import org.springframework.stereotype.Repository;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Collection;

@Repository
public interface QuestionRepository {

    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
}
