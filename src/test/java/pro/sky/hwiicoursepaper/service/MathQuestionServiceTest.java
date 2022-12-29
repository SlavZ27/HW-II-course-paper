package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.MethodNotAllowedException;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {


    private final MathQuestionService mathQuestionService = new MathQuestionService();

    @Test
    public void addTest() {
        Question q1 = new Question("Question1", "Answer1");

        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(null));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(q1));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add(q1.getQuestion(), q1.getAnswer()));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.add("q1.getQuestion()", "q1.getAnswer()"));
    }


    @Test
    public void removeTest() {
        Question q1 = new Question("Question1", "Answer1");

        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove(null));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove(q1));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove(q1.getQuestion(), q1.getAnswer()));
        assertThrows(MethodNotAllowedException.class, () -> mathQuestionService.remove("q1.getQuestion()", "q1.getAnswer()"));
    }

    @Test
    public void getAllTest() {
        assertThrows(MethodNotAllowedException.class, mathQuestionService::getAll);
    }


    @Test
    public void getRandomTest() {
//        Question mathQuestion1 = new Question("8 / 9 * 2 * 5 * 6", "53,33");

        Set<String> setCharOperation = new HashSet<>();
        while (setCharOperation.size() < 4) {

            Question rndQuestion = mathQuestionService.getRandom();

            String[] characters = rndQuestion.getQuestion().split(" ");

            StringBuilder values = new StringBuilder();
            StringBuilder operations = new StringBuilder();

            for (int i = 0; i < characters.length; i++) {
                if (i % 2 != 0) {
                    operations.append(characters[i]);
                    setCharOperation.add(characters[i]);
                } else {
                    values.append(characters[i]);
                }
            }

            assertEquals(values.length(), operations.length() + 1);
            assertThat(values.toString())
                    .isNotBlank()
                    .containsOnlyDigits();
            assertThat(operations.toString())
                    .isNotBlank()
                    .containsAnyOf("+", "-", "*", "/")
                    .doesNotContainPattern("[0-9]");
            assertThat(rndQuestion.getAnswer())
                    .isNotBlank()
                    .containsPattern("[0-9,]");
        }
    }
}