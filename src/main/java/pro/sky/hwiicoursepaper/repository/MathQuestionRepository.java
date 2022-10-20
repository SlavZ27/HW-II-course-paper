package pro.sky.hwiicoursepaper.repository;

import org.springframework.stereotype.Repository;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.*;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private Set<Question> questionSet = new HashSet<>();

    @Override
    public Question add(Question question) {
        questionSet.add(question);
        return questionSet.stream()
                .filter(question1 -> question1.equals(question))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Question remove(Question question) {
        Question tmp = new Question(question);
        questionSet.remove(question);
        return tmp;
    }

    @Override
    public Set<Question> getAll() {
        return Collections.unmodifiableSet(questionSet);
    }
}
