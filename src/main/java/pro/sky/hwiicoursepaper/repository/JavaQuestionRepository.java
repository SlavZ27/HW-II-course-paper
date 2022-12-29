package pro.sky.hwiicoursepaper.repository;

import org.springframework.stereotype.Repository;
import pro.sky.hwiicoursepaper.entity.Question;

import javax.annotation.PostConstruct;

@Repository
public class JavaQuestionRepository extends QuestionRepository {

    @PostConstruct
    private void fillAllQuestionRepository() {
        super.add(new Question("javaQuestion1", "javaAnswer1"));
        super.add(new Question("javaQuestion2", "javaAnswer2"));
        super.add(new Question("javaQuestion3", "javaAnswer3"));
        super.add(new Question("javaQuestion4", "javaAnswer4"));
        super.add(new Question("javaQuestion5", "javaAnswer5"));

    }

}
