package pro.sky.hwiicoursepaper.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final static String TEXT_BAD_QUESTION_EXCEPTION = "Не корректное значение вопроса";
    private final static String TEXT_BAD_ANSWER_EXCEPTION = "Не корректное значение ответа";
    private final static String TEXT_BAD_QUESTION_OBJECT_EXCEPTION = "Передан не корректный объект";
    private final static String TEXT_QUESTION_NOT_FOUND_EXCEPTION = "Не найден вопрос";
    private final static String QUESTION_ALREADY_EXIST_EXCEPTION = "Такой вопрос уже есть";

    private List<Question> questionList = new ArrayList<>();

    public List<Question> fillList(){
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        add(question1.getQuestion(), question1.getAnswer());
        add(question2);
        add(question3);
        add(question4.getQuestion(), question4.getAnswer());
        add(question5.getQuestion(), question5.getAnswer());
        return Collections.unmodifiableList(questionList);
    }

    private String validateQuestionStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadQuestionException(TEXT_BAD_QUESTION_EXCEPTION);
        }
        return str;
    }

    private boolean checkNotContainQuestion(Question question) {
        if (questionList.contains(question)) {
            throw new QuestionAlreadyExistException(QUESTION_ALREADY_EXIST_EXCEPTION);
        }
        return false;
    }

    private boolean checkNotContainQuestion(String question) {
        if (questionList.contains(new Question(question, ""))) {
            throw new QuestionAlreadyExistException(QUESTION_ALREADY_EXIST_EXCEPTION);
        }
        return false;
    }

    private String validateAnswerStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadAnswerException(TEXT_BAD_ANSWER_EXCEPTION);
        }
        return str;
    }

    private int validateIndex(int index) {
        if (0 <= index && index < questionList.size()) {
            return index;
        }
        throw new IllegalArgumentException("Неверный индекс");

    }

    private Question validateQuestionObj(Question question) {
        if (question == null) {
            throw new BadQuestionObjectException(TEXT_BAD_QUESTION_OBJECT_EXCEPTION);
        }
        return question;
    }

    @Override
    public Question add(String question, String answer) {
        validateQuestionStr(question);
        validateAnswerStr(answer);
        checkNotContainQuestion(question);

        questionList.add(new Question(question, answer));

        return new Question(questionList.get(questionList.size() - 1));
    }

    @Override
    public Question add(Question question) {
        validateQuestionObj(question);
        validateQuestionStr(question.getQuestion());
        validateAnswerStr(question.getAnswer());
        checkNotContainQuestion(question);

        questionList.add(question);
        return new Question(questionList.get(questionList.size() - 1));
    }

    private int indexOf(Question question) {
        validateQuestionObj(question);
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).equals(question)) {
                return i;
            }
        }
        throw new QuestionNotFoundException(TEXT_QUESTION_NOT_FOUND_EXCEPTION);
    }

    @Override
    public Question remove(Question question) {
        validateQuestionObj(question);
        Question tmpQuestion = new Question(question);
        questionList.remove(indexOf(question));
        return tmpQuestion;
    }

    public Question remove(String question, String answer) {
        validateAnswerStr(question);
        validateAnswerStr(answer);
        Question tmpQuestion = new Question(question, answer);
        questionList.remove(indexOf(tmpQuestion));
        return tmpQuestion;
    }

    public Question remove(int index) {
        validateIndex(index);
        Question tmpQuestion = new Question(questionList.get(index).getQuestion(), questionList.get(index).getAnswer());
        questionList.remove(index);
        return tmpQuestion;
    }

    @Override
    public List<Question> getAll() {
        return Collections.unmodifiableList(questionList);
    }

    public int getSize() {
        return questionList.size();
    }

    public Question get(int index) {
        validateIndex(index);
        return new Question(questionList.get(index));
    }

    @Override
    public Question getRandom() {
        Random random = new Random();
        return new Question(questionList.get(random.nextInt(questionList.size())));
    }

}
