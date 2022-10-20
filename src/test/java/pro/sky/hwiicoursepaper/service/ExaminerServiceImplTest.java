package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }


    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")
    public void getQuestionsTest(Integer amount,
                                 boolean isException) {
        Question javaQuestion1 = new Question("javaQuestion1", "javaAnswer1");
        Question javaQuestion2 = new Question("javaQuestion2", "javaAnswer2");
        Question javaQuestion3 = new Question("javaQuestion3", "javaAnswer3");
        Question javaQuestion4 = new Question("javaQuestion4", "javaAnswer4");
        Question javaQuestion5 = new Question("javaQuestion5", "javaAnswer5");

        Question mathQuestion1 = new Question("mathQuestion1", "mathAnswer1");
        Question mathQuestion2 = new Question("mathQuestion2", "mathAnswer2");
        Question mathQuestion3 = new Question("mathQuestion3", "mathAnswer3");
        Question mathQuestion4 = new Question("mathQuestion4", "mathAnswer4");
        Question mathQuestion5 = new Question("mathQuestion5", "mathAnswer5");

        Set<Question> questionSetGetAllJava = new HashSet<>();
        Set<Question> questionSetGetAllMath = new HashSet<>();

        questionSetGetAllJava.add(javaQuestion1);
        questionSetGetAllJava.add(javaQuestion2);
        questionSetGetAllJava.add(javaQuestion3);
        questionSetGetAllJava.add(javaQuestion4);
        questionSetGetAllJava.add(javaQuestion5);

        questionSetGetAllMath.add(mathQuestion1);
        questionSetGetAllMath.add(mathQuestion2);
        questionSetGetAllMath.add(mathQuestion3);
        questionSetGetAllMath.add(mathQuestion4);
        questionSetGetAllMath.add(mathQuestion5);

        javaQuestionService.add(javaQuestion1);
        javaQuestionService.add(javaQuestion2);
        javaQuestionService.add(javaQuestion3);
        javaQuestionService.add(javaQuestion4);
        javaQuestionService.add(javaQuestion5);

        mathQuestionService.add(mathQuestion1);
        mathQuestionService.add(mathQuestion2);
        mathQuestionService.add(mathQuestion3);
        mathQuestionService.add(mathQuestion4);
        mathQuestionService.add(mathQuestion5);

        when(javaQuestionService.getAll()).thenReturn(questionSetGetAllJava);
        when(mathQuestionService.getAll()).thenReturn(questionSetGetAllMath);

        Set<Question> expected = new HashSet<>();
        expected.addAll(questionSetGetAllJava);
        expected.addAll(questionSetGetAllMath);

        if (isException) {
            assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(amount));
        } else {
            Set<Question> randomQuestionSet = new HashSet<>(examinerService.getQuestions(amount));
            for (Question question : randomQuestionSet) {
                assertTrue(expected.contains(question));
            }
        }
    }

    public static Stream<Arguments> getQuestionsTestParams() {
        return Stream.of(
                Arguments.of(-1, true),
                Arguments.of(0, false),
                Arguments.of(1, false),
                Arguments.of(2, false),
                Arguments.of(5, false),
                Arguments.of(7, false),
                Arguments.of(9, false),
                Arguments.of(10, false),
                Arguments.of(11, true)
        );
    }

}