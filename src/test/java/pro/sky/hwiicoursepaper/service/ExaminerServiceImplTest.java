package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hwiicoursepaper.entity.Question;
import pro.sky.hwiicoursepaper.repository.JavaQuestionRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private final JavaQuestionRepository javaQuestionRepository = new JavaQuestionRepository();
    @Mock
    private final JavaQuestionService javaQuestionService = new JavaQuestionService(javaQuestionRepository);
    @Mock
    private final MathQuestionService mathQuestionService = new MathQuestionService();
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")
    public void getQuestionsTest(Integer amount,
                                 boolean isException) {
        if (isException) {
            assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(amount));
        } else {
            Question javaQuestion1 = new Question("javaQuestion1", "javaAnswer1");
            Question javaQuestion2 = new Question("javaQuestion2", "javaAnswer2");
            Question javaQuestion3 = new Question("javaQuestion3", "javaAnswer3");
            Question javaQuestion4 = new Question("javaQuestion4", "javaAnswer4");
            Question javaQuestion5 = new Question("javaQuestion5", "javaAnswer5");

            Question mathQuestion1 = new Question("8 / 9 * 2 * 5 * 6", "53,33");
            Question mathQuestion2 = new Question("0 * 4 * 9", "0");
            Question mathQuestion3 = new Question("4 + 3 * 3 - 3 + 6", "16");
            Question mathQuestion4 = new Question("3 - 6 - 9 * 6 + 3", "-54");
            Question mathQuestion5 = new Question("6 - 4 - 0", "2");
            Question mathQuestion6 = new Question("6 * 5 / 1 - 7 - 9", "14");


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
            questionSetGetAllMath.add(mathQuestion6);

            when(javaQuestionService.getAll()).thenReturn(questionSetGetAllJava);
            when(mathQuestionService.getRandom())
                    .thenReturn(mathQuestion1)
                    .thenReturn(mathQuestion2)
                    .thenReturn(mathQuestion3)
                    .thenReturn(mathQuestion4)
                    .thenReturn(mathQuestion5)
                    .thenReturn(mathQuestion6);

            Set<Question> expected = new HashSet<>();
            expected.addAll(questionSetGetAllJava);
            expected.addAll(questionSetGetAllMath);


            Set<Question> randomQuestionSet = new HashSet<>(examinerService.getQuestions(amount));
            assertEquals(amount, randomQuestionSet.size());

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
//                Arguments.of(5, false),
//                Arguments.of(7, false),
//                Arguments.of(9, false),
                Arguments.of(10, false),
                Arguments.of(11, false)
        );
    }

}
