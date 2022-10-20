package pro.sky.hwiicoursepaper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.repository.JavaQuestionRepository;
import pro.sky.hwiicoursepaper.repository.MathQuestionRepository;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private JavaQuestionRepository javaQuestionRepository;
    @Autowired
    private MathQuestionRepository mathQuestionRepository;

    @PostConstruct
    @GetMapping("java")
    private void fillAllQuestionRepository() {
        javaQuestionRepository.add(new Question("javaQuestion1", "javaAnswer1"));
        javaQuestionRepository.add(new Question("javaQuestion2", "javaAnswer2"));
        javaQuestionRepository.add(new Question("javaQuestion3", "javaAnswer3"));
        javaQuestionRepository.add(new Question("javaQuestion4", "javaAnswer4"));
        javaQuestionRepository.add(new Question("javaQuestion5", "javaAnswer5"));

        mathQuestionRepository.add(new Question("mathQuestion1", "mathAnswer1"));
        mathQuestionRepository.add(new Question("mathQuestion2", "mathAnswer2"));
        mathQuestionRepository.add(new Question("mathQuestion3", "mathAnswer3"));
        mathQuestionRepository.add(new Question("mathQuestion4", "mathAnswer4"));
        mathQuestionRepository.add(new Question("mathQuestion5", "mathAnswer5"));

    }
}
