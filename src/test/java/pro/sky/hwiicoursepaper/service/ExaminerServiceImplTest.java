package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pro.sky.hwiicoursepaper.collection.CollectionsConfig;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@WebMvcTest
@ExtendWith(MockitoExtension.class)
//@MockBean(QuestionService.class)
//@MockBean(JavaQuestionService.class)
//@MockBean(MathQuestionService.class)
class ExaminerServiceImplTest {


    //    @MockBean(CollectionsConfig.class)
//    @MockBean
//    @Qualifier("javaQuestionServiceBean")
//    private JavaQuestionService javaQuestionServiceBean;
//    //    @MockBean(CollectionsConfig.class)
////    @Autowired
//    @Qualifier("mathQuestionServiceBean")
//    @MockBean
//    private MathQuestionService mathQuestionServiceBean;
//    @Autowired
    private ExaminerServiceImpl examinerService = new ExaminerServiceImpl();
//    AnnotationConfigApplicationContext context;


//    @BeforeEach
//    void setUp() {
//        context =
//                new AnnotationConfigApplicationContext(CollectionsConfigMock.class);
//
//        Map<String, QuestionService> questionServicesMap = context.getBean(
//                "questionServicesMapBeanMock", Map.class);
//
//        QuestionService javaQuestionServiceMock = questionServicesMap.get("javaQuestionService");
//        QuestionService mathQuestionServiceMock = questionServicesMap.get("mathQuestionService");
//        javaQuestionServiceBean = javaQuestionServiceMock;
//        mathQuestionServiceBean = mathQuestionServiceMock;
//    }
//
//    @AfterEach
//    void close() {
//        context.close();
//    }

    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")
    public void getQuestionsTest(Integer amount,
                                 boolean isException) {
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


//        given(javaQuestionServiceBean.getAll()).willReturn(questionSetGetAllJava);
//        given(mathQuestionServiceBean.getRandom())
//                .willReturn(mathQuestion1)
//                .willReturn(mathQuestion2)
//                .willReturn(mathQuestion3)
//                .willReturn(mathQuestion4)
//                .willReturn(mathQuestion5)
//                .willReturn(mathQuestion6);
//
//        when(javaQuestionServiceBean.getAll()).thenReturn(questionSetGetAllJava);
//        when(mathQuestionServiceBean.getRandom())
//                .thenReturn(mathQuestion1)
//                .thenReturn(mathQuestion2)
//                .thenReturn(mathQuestion3)
//                .thenReturn(mathQuestion4)
//                .thenReturn(mathQuestion5)
//                .thenReturn(mathQuestion6);

        if (isException) {
            assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(amount));
        } else {
            Set<Question> randomQuestionSet = new HashSet<>(examinerService.getQuestions(amount));
            assertEquals(amount, randomQuestionSet.size());
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
