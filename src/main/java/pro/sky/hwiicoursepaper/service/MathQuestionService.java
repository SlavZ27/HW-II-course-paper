package pro.sky.hwiicoursepaper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;
import pro.sky.hwiicoursepaper.entity.Question;

import java.text.NumberFormat;
import java.util.*;


@Service
public class MathQuestionService implements QuestionService {
    private final Random rnd = new Random();


    @Override
    public Question add(String question, String answer) {
        throw new MethodNotAllowedException("add", null);
    }

    @Override
    public Question add(Question question) {
        throw new MethodNotAllowedException("add", null);
    }

    public Question remove(Question question) {
        throw new MethodNotAllowedException("remove", null);
    }

    @Override
    public Question remove(String question, String answer) {
        throw new MethodNotAllowedException("remove", null);
    }

    @Override
    public Set<Question> getAll() {
        throw new MethodNotAllowedException("getAll", null);
    }

    @Override
    public Question getRandom() {
        int countAllOperations = rnd.nextInt(3) + 2;            //сколько всего будет операций
        int countOperations = 0;                                      //счетчик операций

        List<Float> masValue = new ArrayList<>();                       //Массив значений
        List<Integer> masOperation = new ArrayList<>();
        //массив с индексами операций 0 сложение 1 вычитание2 умножение 3 деление


        float firstValue = rnd.nextInt(10);                // Первое оператор, ограничено 10
        StringBuilder question = new StringBuilder(String.valueOf((int) firstValue));         //строка вопроса
        masValue.add(firstValue);

        //смысл в том чтобы сначала заполнить массив потому что нужно соблюдать очередность выполнения действий
        while (countOperations < countAllOperations) {  //набираем необходимое количество операций
            int indexOperation = rnd.nextInt(4);    //случайный индекс операций 0..3
            float secondValue = rnd.nextInt(10);    //второй оператор значение ограниченное 10
            if (secondValue == 0 && indexOperation == 3) {  // если значение 0 и операция деления то избавляемся от 0
                secondValue++;
            }
            masOperation.add(indexOperation);       //добавляем индекс операции в массив
            masValue.add(secondValue);              //добавляем значение в массив
            question.append(getOperationStr(indexOperation)).append(((int) secondValue));
            countOperations++;      //дополняем строчку вопроса и делаем +1 счетчику нужных опрераций
        }
//теперь приступаем к решению. действуем пока в массиве операций закончаться значения. тогда в массиве операций останется 1 значение
        while (masOperation.size() > 0) {
            int i;
            if (masOperation.contains(2) || masOperation.contains(3)) { //выполняем * / в первую очередб
                i = -1;
                for (int i1 = 0; i1 < masOperation.size(); i1++) {//ищем * /
                    if (masOperation.get(i1) == 2 || masOperation.get(i1) == 3) {
                        i = i1;
                        break;
                    }
                }
                //нужно выполнить * или /, избавиться от двух значений и записать одно, и избавиться от 1 операции
                float tmpResult = 0f;
                switch (masOperation.get(i)) {  //индекс операции
                    case 2: { //умножение
                        tmpResult = masValue.get(i) * masValue.get(i + 1);
                        break;
                    }
                    case 3: { //деление
                        tmpResult = masValue.get(i) / masValue.get(i + 1);
                        break;
                    }
                }
                masOperation.remove(i); //удаляем операцию
                masValue.set(i, tmpResult); //устанавливаем новое значение
            } else {    //если операторов * / не осталось то делаем операции по порядку пока не останится одно число
                i = 0;
                float tmpResult = 0f;
                switch (masOperation.get(i)) {
                    case 0: { //сложение
                        tmpResult = masValue.get(i) + masValue.get(i + 1);
                        break;
                    }
                    case 1: { //вычитание
                        tmpResult = masValue.get(i) - masValue.get(i + 1);
                        break;
                    }
                }
                masOperation.remove(i);
                masValue.set(i, tmpResult);
            }
            masValue.remove(i + 1);//удаляем второй оператор
        }

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);//округление float до двух знаков, если они есть

        return new Question(question.toString(), nf.format(masValue.get(0)));
    }

    private String getOperationStr(int index) {
        switch (index) {
            case 0: {
                return " + ";
            }
            case 1: {
                return " - ";
            }
            case 2: {
                return " * ";
            }
            case 3: {
                return " / ";
            }
        }
        throw new IllegalArgumentException();
    }
}
