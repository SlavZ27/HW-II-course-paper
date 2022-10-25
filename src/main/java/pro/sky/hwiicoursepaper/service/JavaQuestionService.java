package pro.sky.hwiicoursepaper.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;
import pro.sky.hwiicoursepaper.repository.QuestionRepository;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository javaQuestionRepository;
    private final Random rand = new Random();


    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    private void validateQuestionStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadQuestionException(BadQuestionException.TEXT_BAD_QUESTION_EXCEPTION);
        }
    }

    private void checkContainQuestion(Question question, boolean isNeedToContain) {
        Set<Question> tmpSet = new HashSet<>(javaQuestionRepository.getAll());
        boolean isContains = tmpSet.contains(question);
        if (isContains && !isNeedToContain) {
            throw new QuestionAlreadyExistException(QuestionAlreadyExistException.QUESTION_ALREADY_EXIST_EXCEPTION);
        }
        if (!isContains && isNeedToContain) {
            throw new QuestionNotFoundException(QuestionNotFoundException.TEXT_QUESTION_NOT_FOUND_EXCEPTION);
        }
    }

    private void checkContainQuestion(String question, boolean isNeedToContain) {
        checkContainQuestion(new Question(question, "56"), isNeedToContain);
    }

    private void validateAnswerStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadAnswerException(BadAnswerException.TEXT_BAD_ANSWER_EXCEPTION);
        }
    }

    private void validateQuestionObj(Question question) {
        if (question == null) {
            throw new BadQuestionObjectException(BadQuestionObjectException.TEXT_BAD_QUESTION_OBJECT_EXCEPTION);
        }
    }

    @Override
    public Question add(String question, String answer) {
        validateQuestionStr(question);
        validateAnswerStr(answer);
        checkContainQuestion(question, false);

        return javaQuestionRepository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        validateQuestionObj(question);
        validateQuestionStr(question.getQuestion());
        validateAnswerStr(question.getAnswer());
        checkContainQuestion(question, false);
        return javaQuestionRepository.add(question);
    }

    public Question remove(Question question) {
        validateQuestionObj(question);
        checkContainQuestion(question, true);
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Question remove(String question, String answer) {
        validateAnswerStr(question);
        validateAnswerStr(answer);
        checkContainQuestion(question, true);
        return javaQuestionRepository.remove(new Question(question, answer));
    }

    @Override
    public Set<Question> getAll() {
        return Collections.unmodifiableSet(javaQuestionRepository.getAll());
    }

    public int getSize() {
        return javaQuestionRepository.getAll().size();
    }

    @Override
    public Question getRandom() {
        Set<Question> questionSet = new HashSet<>(javaQuestionRepository.getAll());
        return questionSet.stream()
                .skip(rand.nextInt(questionSet.size()-1))
                .findFirst()
                .orElse(null);
    }
}
