package pro.sky.hwiicoursepaper.repository;

import org.springframework.stereotype.Repository;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public abstract class QuestionRepository {

    private Set<Question> questionSet = new HashSet<>();

    public Question add(Question question) {
        questionSet.add(question);
        return questionSet.stream()
                .filter(question1 -> question1.equals(question))
                .findFirst()
                .orElse(null);
    }

    public Question remove(Question question) {
        Question tmp = new Question(question);
        questionSet.remove(question);
        return tmp;
    }

    public Set<Question> getAll() {
        return Collections.unmodifiableSet(questionSet);
    }
}
