package pro.sky.hwiicoursepaper.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hwiicoursepaper.entity.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService = new JavaQuestionService();

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService);
    }


    @ParameterizedTest
    @MethodSource("getQuestionsTestParams")
    public void getQuestionsTest(Integer amount,
                                           boolean isException) {
        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");
        Question question4 = new Question("question4", "answer4");
        Question question5 = new Question("question5", "answer5");

        List<Question> questionListGetAll = new ArrayList<>();

        questionListGetAll.add(question1);
        questionListGetAll.add(question2);
        questionListGetAll.add(question3);
        questionListGetAll.add(question4);
        questionListGetAll.add(question5);

        when(javaQuestionService.getAll()).thenReturn(questionListGetAll);

        if (isException) {
            assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(amount));
        }else {
            Set<Question> randomQuestionList = new HashSet<>(examinerService.getQuestions(amount));
            for (Question question: randomQuestionList){
                assertTrue(questionListGetAll.contains(question));
            }
        }
    }

    public static Stream<Arguments> getQuestionsTestParams(){
        return Stream.of(
                Arguments.of(-1,true),
                Arguments.of(0,false),
                Arguments.of(1,false),
                Arguments.of(5,false),
                Arguments.of(6,true)
        );
    }

}