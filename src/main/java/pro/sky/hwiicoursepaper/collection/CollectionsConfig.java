package pro.sky.hwiicoursepaper.collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.sky.hwiicoursepaper.repository.JavaQuestionRepository;
import pro.sky.hwiicoursepaper.repository.QuestionRepository;
import pro.sky.hwiicoursepaper.service.JavaQuestionService;
import pro.sky.hwiicoursepaper.service.MathQuestionService;
import pro.sky.hwiicoursepaper.service.QuestionService;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CollectionsConfig {

    @Bean
    public QuestionRepository questionRepositoryBean(){
        return new JavaQuestionRepository();
    }

    @Bean
    public QuestionService javaQuestionServiceBean(){
        return new JavaQuestionService(questionRepositoryBean());
    }

    @Bean
    public QuestionService mathQuestionServiceBean(){
        return new MathQuestionService();
    }

    @Bean
    public Map<String, QuestionService> questionServicesMap() {
        Map<String, QuestionService> result = new HashMap<>();

        result.put("mathQuestionService", mathQuestionServiceBean());
        result.put("javaQuestionService", javaQuestionServiceBean());
        return result;
    }
}
