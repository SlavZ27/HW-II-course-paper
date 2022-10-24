package pro.sky.hwiicoursepaper.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.collection.CollectionsConfig;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    Random random = new Random();


    @Override
    public List<Question> getQuestions(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Некорректный запрос");
        }

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CollectionsConfig.class);

        Map<String, QuestionService> questionServicesMap = context.getBean(
                "questionServicesMap", Map.class);

        QuestionService javaQuestionService = questionServicesMap.get("javaQuestionService");
        QuestionService mathQuestionService = questionServicesMap.get("mathQuestionService");

        List<Question> mathSet = new ArrayList<>();

        List<Question> questionList = new ArrayList<>(
                javaQuestionService.getAll());

        for (int i = 0; i < questionList.size(); i++) {
            mathSet.add(mathQuestionService.getRandom());
        }
        questionList.addAll(mathSet);


        while (questionList.size() < amount) {
            questionList.add(mathQuestionService.getRandom());
        }

        List<Question> tmpQuestionList = new ArrayList<>(questionList);
        List<Question> resultList = new ArrayList<>();
        int tmpIndex;

        while (resultList.size() < amount) {
            tmpIndex = random.nextInt(tmpQuestionList.size());
            resultList.add(tmpQuestionList.get(tmpIndex));
            tmpQuestionList.remove(tmpIndex);

        }

        context.close();
        return resultList;
    }
}
