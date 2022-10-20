package pro.sky.hwiicoursepaper.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;
import pro.sky.hwiicoursepaper.repository.MathQuestionRepository;

import java.util.*;

import static pro.sky.hwiicoursepaper.exception.TextException.*;

@Service
public class MathQuestionService implements QuestionService {

    private MathQuestionRepository mathQuestionRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    private String validateQuestionStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadQuestionException(TEXT_BAD_QUESTION_EXCEPTION);
        }
        return str;
    }

    private void checkContainQuestion(Question question, boolean isNeedToContain) {
        Set<Question> tmpSet = new HashSet<>(mathQuestionRepository.getAll());
        if (tmpSet.contains(question) && !isNeedToContain) {
            throw new QuestionAlreadyExistException(QUESTION_ALREADY_EXIST_EXCEPTION);
        }
        if (!tmpSet.contains(question) && isNeedToContain) {
            throw new QuestionNotFoundException(TEXT_QUESTION_NOT_FOUND_EXCEPTION);
        }
    }

    private void checkContainQuestion(String question, boolean isNeedToContain) {
        checkContainQuestion(new Question(question, "56"), isNeedToContain);
    }

    private String validateAnswerStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadAnswerException(TEXT_BAD_ANSWER_EXCEPTION);
        }
        return str;
    }

    private void validateQuestionObj(Question question) {
        if (question == null) {
            throw new BadQuestionObjectException(TEXT_BAD_QUESTION_OBJECT_EXCEPTION);
        }
    }

    @Override
    public Question add(String question, String answer) {
        validateQuestionStr(question);
        validateAnswerStr(answer);
        checkContainQuestion(question, false);

        return mathQuestionRepository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        validateQuestionObj(question);
        validateQuestionStr(question.getQuestion());
        validateAnswerStr(question.getAnswer());
        checkContainQuestion(question, false);
        return mathQuestionRepository.add(question);
    }

    public Question remove(Question question) {
        validateQuestionObj(question);
        checkContainQuestion(question, true);
        return mathQuestionRepository.remove(question);
    }

    @Override
    public Question remove(String question, String answer) {
        validateAnswerStr(question);
        validateAnswerStr(answer);
        checkContainQuestion(question, true);
        return mathQuestionRepository.remove(new Question(question, answer));
    }

    @Override
    public Set<Question> getAll() {
        return Collections.unmodifiableSet(mathQuestionRepository.getAll());
    }

    public int getSize() {
        return mathQuestionRepository.getAll().size();
    }

    @Override
    public Question getRandom() {
        Set<Question> questionSet = new HashSet<>(mathQuestionRepository.getAll());
        return new Question(Objects.requireNonNull(questionSet.stream().findAny().orElse(null)));
    }

}
