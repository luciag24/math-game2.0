package org.example.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.example.models.MathExample;
import org.example.models.UserProgress;
import org.example.repositories.MathExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Služba na generovanie a spracovanie matematických príkladov.
 * Obsahuje metódy na generovanie príkladov, ich ukladanie a analýzu odpovedí.
 */
@Service
public class MathExampleService {
    private final MathExampleRepository mathExampleRepository;
    private Map<Integer, MathExampleGenerator> generators = new HashMap<>();

    @Autowired
    public MathExampleService(List<MathExampleGenerator> generatorList, MathExampleRepository mathExampleRepository) {
        this.mathExampleRepository = mathExampleRepository;
        this.generators = new HashMap<>();
        this.generators.put(1, generatorList.stream().filter(g -> g instanceof FirstGradeMathService).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chýba FirstGradeMathService")));
        this.generators.put(2, generatorList.stream().filter(g -> g instanceof SecondGradeMathService).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chýba SecondGradeMathService")));
        this.generators.put(3, generatorList.stream().filter(g -> g instanceof ThirdGradeMathService).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chýba ThirdGradeMathService")));
        this.generators.put(4, generatorList.stream().filter(g -> g instanceof FourthGradeMathService).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chýba FourthGradeMathService")));
    }

    public MathExample generateExampleForGrades(int grade) {
        MathExampleGenerator generator = generators.get(grade);
        if (generator == null) {
            throw new IllegalArgumentException("Neplatný ročník: " + grade);
        }
        return generator.generateExample();
    }

    public MathExample saveExample(MathExample example) {
        return mathExampleRepository.save(example);
    }

    private final UserProgress userProgress = new UserProgress();
    private final Set<String> generatedQuestions = new HashSet<>();

    private MathExample generateStandardExample() {
        int number1, number2;
        char operator = randomOperator();

        if (operator == '*' || operator == '/') {
            number1 = (int) (Math.random() * 10 + 1);
            number2 = (int) (Math.random() * 10 + 1);
        } else {
            number1 = (int) (Math.random() * 1000 + 1);
            number2 = (int) (Math.random() * 1000 + 1);
        }
        if (operator == '/') {
            number1 = number1 * number2;
        }

        int correctAnswer = calculateAnswer(number1, number2, operator);
        if (operator == '-' && correctAnswer < 0) {
            return generateStandardExample();
        }

        String operatorSymbol = String.valueOf(operator);
        if (operator == '/') {
            operatorSymbol = ":";
        }

        String question = number1 + " " + operatorSymbol + " " + number2;

        if (generatedQuestions.contains(question)) {
            return generateStandardExample();
        }

        generatedQuestions.add(question);
        return new MathExample(question, correctAnswer);
    }

    private char randomOperator() {
        char[] operators = {'+', '-', '*', '/'};
        return operators[new Random().nextInt((operators.length))];
    }

    private int calculateAnswer(int number1, int number2, char operator) {
        switch (operator) {
            case '+': return number1 + number2;
            case '-': return number1 - number2;
            case '*': return number1 * number2;
            case '/': return (number2 != 0) ? number1 / number2 : 0;
            default: throw new IllegalArgumentException("Neznámy operátor: " + operator);
        }
    }

    public MathExample generateExampleForGrade() {
        return generateStandardExample();
    }

    public MathExample generateFirstGradeExample() {
        return generators.get(1).generateExample();
    }

    public MathExample generateSecondGradeExample() {
        return generators.get(2).generateExample();
    }

    public MathExample generateThirdGradeExample() {
        return generators.get(3).generateExample();
    }

    public MathExample generateFourthGradeExample() {
        return generators.get(4).generateExample();
    }

    public MathExample analyzeAnswer(MathExample example, int userAnswer) {
        example.setUserAnswer(userAnswer);
        if (example.isCorrect()) {
            userProgress.incrementCorrectAnswers();
        } else {
            userProgress.incrementIncorrectAnswers();
        }
        return example;
    }
}