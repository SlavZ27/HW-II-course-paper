package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private JavaQuestionService javaQuestionService;
    private MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        List<Question> questionList = new ArrayList<>();
        questionList.addAll(javaQuestionService.getAll());
        questionList.addAll(mathQuestionService.getAll());

        if (amount < 0 || questionList.size() < amount) {
            throw new IllegalArgumentException("Такого количества вопросов нет в списке");
        }

        List<Question> tmpQuestionList = new ArrayList<>(questionList);
        List<Question> resultList = new ArrayList<>();
        Random random = new Random();
        int tmpIndex;

        while (resultList.size() < amount) {
            tmpIndex = random.nextInt(tmpQuestionList.size());
            resultList.add(tmpQuestionList.get(tmpIndex));
            tmpQuestionList.remove(tmpIndex);
        }
        return resultList;
    }
}
