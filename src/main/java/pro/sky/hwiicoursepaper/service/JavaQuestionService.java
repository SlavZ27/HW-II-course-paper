package pro.sky.hwiicoursepaper.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;
import pro.sky.hwiicoursepaper.repository.QuestionRepository;

import java.util.*;

import static pro.sky.hwiicoursepaper.exception.TextException.*;

@Service
public class JavaQuestionService implements QuestionService {

    @Qualifier("javaQuestionService")
    private QuestionRepository javaQuestionRepository;

    @Autowired
    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    private String validateQuestionStr(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BadQuestionException(TEXT_BAD_QUESTION_EXCEPTION);
        }
        return str;
    }

    private void checkContainQuestion(Question question, boolean isNeedToContain) {
        Set<Question> tmpSet = new HashSet<>(javaQuestionRepository.getAll());
        boolean isContains = tmpSet.contains(question);
        if (isContains && !isNeedToContain) {
            throw new QuestionAlreadyExistException(QUESTION_ALREADY_EXIST_EXCEPTION);
        }
        if (!isContains && isNeedToContain) {
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
        Random rand = new Random();
        return questionSet.stream()
                .skip(rand.nextInt(questionSet.size()-1))
                .findFirst()
                .get();
    }
}
