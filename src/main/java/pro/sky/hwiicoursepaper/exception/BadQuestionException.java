package pro.sky.hwiicoursepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadQuestionException extends RuntimeException {
    public final static String TEXT_BAD_QUESTION_EXCEPTION = "Не корректное значение вопроса";

    public BadQuestionException(String message) {
        super(message);
    }
}
