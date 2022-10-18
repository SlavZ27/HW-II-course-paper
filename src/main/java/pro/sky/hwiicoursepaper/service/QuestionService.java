package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    public List<String> getListQuestions(Integer amount) {
        ArrayList<String> qwe = new ArrayList<>();
        qwe.add("1");
        qwe.add("2");
        qwe.add("3");
        return qwe;
    }
}
