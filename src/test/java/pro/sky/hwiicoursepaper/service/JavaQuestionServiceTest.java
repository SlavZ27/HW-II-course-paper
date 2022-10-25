package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.exception.*;
import pro.sky.hwiicoursepaper.repository.JavaQuestionRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private final JavaQuestionRepository javaQuestionRepository = new JavaQuestionRepository();

    private JavaQuestionService javaQuestionService;

    Set<Question> q0;
    Set<Question> q1;
    Set<Question> q1q2;
    Set<Question> q1q2q3;
    Set<Question> q1q2q3q4;
    Set<Question> q1q2q3q4q5;
    Question question1;
    Question question2;
    Question question3;
    Question question4;
    Question question5;
    Question question5_2;
    Question question5_3;


    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService(javaQuestionRepository);
        question1 = new Question("question1", "answer1");
        question2 = new Question("question2", "answer2");
        question3 = new Question("question3", "answer3");
        question4 = new Question("question4", "answer4");
        question5 = new Question("question5", "answer5");

        question5_2 = new Question("question5", "answer5");
        question5_3 = new Question("question5", "234324");

        q0 = new HashSet<>();
        q1 = new HashSet<>();
        q1.add(question1);
        q1q2 = new HashSet<>(q1);
        q1q2.add(question2);
        q1q2q3 = new HashSet<>(q1q2);
        q1q2q3.add(question3);
        q1q2q3q4 = new HashSet<>(q1q2q3);
        q1q2q3q4.add(question4);
        q1q2q3q4q5 = new HashSet<>(q1q2q3q4);
        q1q2q3q4q5.add(question5);
        //q1 q2 q3 q4 q5
    }

    @Test
    public void addTest() {

        when(javaQuestionRepository.getAll())
                .thenReturn(q0)          //getSize
                .thenReturn(q0)          //add(question1
                .thenReturn(q1)          //add(question2
                .thenReturn(q1q2)          //add(question3
                .thenReturn(q1q2q3)          //add(question4
                .thenReturn(q1q2q3q4)          //add(question5
                .thenReturn(q1q2q3q4q5);          //other

        assertEquals(0, javaQuestionService.getSize());

        javaQuestionService.add(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
        javaQuestionService.add(question4.getQuestion(), question4.getAnswer());
        javaQuestionService.add(question5.getQuestion(), question5.getAnswer());

        assertEquals(javaQuestionService.getAll(), q1q2q3q4q5);

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
//q1 q2 q3 q4 q5
        when(javaQuestionRepository.getAll())
                .thenReturn(q1q2q3q4q5);          //other

        assertEquals(5, javaQuestionService.getSize());

        javaQuestionService.remove(question2);
//q1 q3 q4 q5

        Set<Question> q1q3q4q5 = new HashSet<>(q1q2q3q4q5);
        q1q3q4q5.remove(question2);
        //q1 q3 q4 q5
        when(javaQuestionRepository.getAll())
                .thenReturn(q1q3q4q5);        //other

        assertIterableEquals(javaQuestionService.getAll(), q1q3q4q5);

        javaQuestionService.remove(question3.getQuestion(), question3.getAnswer());
//q1 q4 q5

        Set<Question> q1q4q5 = new HashSet<>(q1q3q4q5);
        q1q4q5.remove(question3);
        //q1 q4 q5
        when(javaQuestionRepository.getAll())
                .thenReturn(q1q4q5);               //other


        assertIterableEquals(javaQuestionService.getAll(), q1q4q5);

        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(new Question("", "asd")));
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(new Question(" ", "sad")));
        assertThrows(BadQuestionObjectException.class, () -> javaQuestionService.remove(null));
    }

    @Test
    public void getAllTest() {
        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        when(javaQuestionRepository.getAll())
                .thenReturn(q1q2q3q4q5);          //other

        assertEquals(5, javaQuestionService.getSize());

        Set<Question> actual = javaQuestionService.getAll();
        assertIterableEquals(expected, actual);
    }

    @Test
    public void getRandomTest() {

        Set<Question> expected = new HashSet<>();
        expected.add(question1);
        expected.add(question2);
        expected.add(question3);
        expected.add(question4);
        expected.add(question5);

        when(javaQuestionRepository.getAll())
                .thenReturn(q1q2q3q4q5);          //other

        assertEquals(5, javaQuestionService.getSize());

        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
        assertTrue(expected.contains(javaQuestionService.getRandom()));
    }
}