package pro.sky.hwiicoursepaper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadAnswerException extends RuntimeException {
    public final static String TEXT_BAD_ANSWER_EXCEPTION = "Не корректное значение ответа";


    public BadAnswerException(String message) {
        super(message);
    }
}
