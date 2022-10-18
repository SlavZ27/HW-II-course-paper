package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();

    }

    @Test
    public void addTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5_1 = new Question("question5", "answer5");

        Question question5_2 = new Question("question5", "answer5");
        Question question5_3 = new Question("question5", "234324");

        assertEquals(0, javaQuestionService.getSize());
        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5_1.getQuestion(), question5_1.getAnswer());
        assertEquals(5, javaQuestionService.getSize());

        assertEquals(question1, javaQuestionService.get(0));
        assertEquals(question2, javaQuestionService.get(1));
        assertEquals(question3, javaQuestionService.get(2));
        assertEquals(question4, javaQuestionService.get(3));
        assertEquals(question5_1, javaQuestionService.get(4));

        assertThrows(QuestionAlreadyExistException.class, () -> javaQuestionService.add(question5_2));
        assertThrows(QuestionAlreadyExistException.class, () -> javaQuestionService.add(question5_3.getQuestion(), question5_3.getAnswer()));

        assertThrows(BadQuestionException.class, () -> javaQuestionService.add(null, "asd"));
        assertThrows(BadQuestionException.class, () -> javaQuestionService.add("", "asd"));
        assertThrows(BadQuestionException.class, () -> javaQuestionService.add(" ", "sad"));

        assertThrows(BadAnswerException.class, () -> javaQuestionService.add("asd", null));
        assertThrows(BadAnswerException.class, () -> javaQuestionService.add("asd", ""));
        assertThrows(BadAnswerException.class, () -> javaQuestionService.add("asd", " "));

        assertThrows(BadQuestionObjectException.class, () -> javaQuestionService.add(null));
    }


    @Test
    public void removeTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        assertEquals(0, javaQuestionService.getSize());

        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5.getQuestion(), question5.getAnswer());

        assertEquals(5, javaQuestionService.getSize());

        javaQuestionService.remove(question2);

        assertEquals(4, javaQuestionService.getSize());

        assertEquals(question1, javaQuestionService.get(0));
        assertNotEquals(question2, javaQuestionService.get(1));
        assertEquals(question3, javaQuestionService.get(1));
        assertEquals(question4, javaQuestionService.get(2));
        assertEquals(question5, javaQuestionService.get(3));

        javaQuestionService.remove(question3);
        javaQuestionService.remove(2);

        assertEquals(2, javaQuestionService.getSize());

        assertEquals(question1, javaQuestionService.get(0));
        assertNotEquals(question2, javaQuestionService.get(1));
        assertNotEquals(question3, javaQuestionService.get(1));
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.get(2));
        assertEquals(question4, javaQuestionService.get(1));

        javaQuestionService.remove(question1.getQuestion(),question1.getAnswer());
        assertEquals(1, javaQuestionService.getSize());
        javaQuestionService.remove(question4.getQuestion(),question4.getAnswer());
        assertEquals(0, javaQuestionService.getSize());


        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(new Question("", "asd")));
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(new Question(" ", "sad")));
        assertThrows(BadQuestionObjectException.class, () -> javaQuestionService.remove((Question) null));
    }

    @Test
    public void getAllTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        List<Question> expected = new ArrayList<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        assertEquals(0, javaQuestionService.getSize());

        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5.getQuestion(), question5.getAnswer());

        assertEquals(5, javaQuestionService.getSize());

        List<Question> actual = javaQuestionService.getAll();
        assertIterableEquals(expected, actual);

    }

    @Test
    public void getRandomTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        assertEquals(0, javaQuestionService.getSize());

        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5.getQuestion(), question5.getAnswer());

        assertEquals(5, javaQuestionService.getSize());

        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
    }

    @Test
    public void getTest() {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        assertEquals(0, javaQuestionService.getSize());
        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5.getQuestion(), question5.getAnswer());
        assertEquals(5, javaQuestionService.getSize());

        assertEquals(question1, javaQuestionService.get(0));
        assertEquals(question2, javaQuestionService.get(1));
        assertEquals(question3, javaQuestionService.get(2));
        assertEquals(question4, javaQuestionService.get(3));
        assertEquals(question5, javaQuestionService.get(4));

        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.get(-1));
        assertThrows(IllegalArgumentException.class, () -> javaQuestionService.get(5));

    }
}