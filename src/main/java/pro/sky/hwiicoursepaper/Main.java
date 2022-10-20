package pro.sky.hwiicoursepaper;

import pro.sky.hwiicoursepaper.entity.Question;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Question question1 = new Question("question1", "ansver1");
        Question question2 = new Question("question2", "ansver2");
        Question question3 = new Question("question3", "ansver3");
        Question question4 = new Question("question4", "ansver4");
        Question question5 = new Question("question5", "ansver5");
        Question question6 = new Question("question5", "sdfsfsad");
        Set<Question> questionList = new HashSet<>();
        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question4);
        questionList.add(question5);
        questionList.add(question6);
        System.out.println(questionList);
    }
}
