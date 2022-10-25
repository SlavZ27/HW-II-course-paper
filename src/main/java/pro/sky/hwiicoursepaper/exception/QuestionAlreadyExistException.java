package pro.sky.hwiicoursepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistException extends RuntimeException{
    public final static String QUESTION_ALREADY_EXIST_EXCEPTION = "Такой вопрос уже есть";

    public QuestionAlreadyExistException(String message) {
        super(message);
    }
}
