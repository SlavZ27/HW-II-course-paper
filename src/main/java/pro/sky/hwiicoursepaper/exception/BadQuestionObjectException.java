package pro.sky.hwiicoursepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadQuestionObjectException extends RuntimeException{
    public final static String TEXT_BAD_QUESTION_OBJECT_EXCEPTION = "Передан не корректный объект";

    public BadQuestionObjectException(String message) {
        super(message);
    }
}
