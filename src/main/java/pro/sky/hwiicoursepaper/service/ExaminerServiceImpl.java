package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final Map<String, QuestionService> questionServiceMap;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        Map<String, QuestionService> result = new HashMap<>();
        result.put("mathQuestionService", mathQuestionService);
        result.put("javaQuestionService", javaQuestionService);
        this.questionServiceMap = result;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Некорректный запрос");
        }

        QuestionService javaQuestionService = questionServiceMap.get("javaQuestionService");
        QuestionService mathQuestionService = questionServiceMap.get("mathQuestionService");

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
        return resultList;
    }
}
