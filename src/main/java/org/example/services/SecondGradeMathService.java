package org.example.services;

import org.example.models.MathExample;
import org.springframework.stereotype.Service;

@Service
public class SecondGradeMathService implements MathExampleGenerator {

    /**
     * Generuje príklad na sčítanie alebo odčítanie pre druhý ročník.
     * Používa čísla v rozsahu od 1 do 100 a zabraňuje vytvoreniu záporných výsledkov.
     *
     * @return Nový matematický príklad.
     */
    @Override
    public MathExample generateExample() {
        int number1, number2;
        char operator = randomOperator(new char[]{'+', '-'});

        if (operator == '+') {
            number1 = (int) (Math.random() * 100);
            number2 = (int) (Math.random() * (100 - number1));
        } else {
            number1 = (int) (Math.random() * 100);
            number2 = (int) (Math.random() * number1 + 1);

        }
        int correctAnswer = calculateAnswer(number1, number2, operator);
        String question = number1 + " " + operator + " " + number2;
        return new MathExample(question, correctAnswer);
    }

    /**
     * Vyberie náhodný operátor z daného zoznamu.
     * @param operators Pole dostupných operátorov.
     * @return Náhodný operátor.
     */
    private char randomOperator(char[] operators) {
        return operators[(int) (Math.random() * operators.length)];
    }

    private int calculateAnswer(int number1, int number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            default -> throw new IllegalArgumentException("Neznámy operátor: " + operator);
        };
    }
}
