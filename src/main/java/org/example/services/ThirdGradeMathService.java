package org.example.services;

import java.util.HashSet;
import java.util.Set;

import org.example.models.MathExample;
import org.springframework.stereotype.Service;

/**
 * Služba na generovanie príkladov pre tretí ročník.
 * Obsahuje sčítanie a odčítanie do 1000, malú násobilku a delilku.
 * Zabezpečuje, že sa neobjavia záporné výsledky a neopakujúce sa príklady.
 */
@Service
public class ThirdGradeMathService implements MathExampleGenerator {

    private final Set<String> generatedQuestions = new HashSet<>();

    /**
     * Generuje príklad pre tretí ročník.
     * Obsahuje sčítanie, odčítanie, násobenie a delenie.
     * Zabraňuje vzniku záporných výsledkov a opakovaniu rovnakých príkladov.
     *
     * @return Objekt {@link MathExample} s vygenerovaným príkladom.
     */
    @Override
    public MathExample generateExample() {
        int number1, number2;
        char operator = randomOperator(new char[] {'+', '-', '*', '/'});

        if (operator == '*' || operator == '/') {
            number1 = (int) (Math.random() * 10 + 1);
            number2 = (int) (Math.random() * 10 + 1);

            if (operator == '/') {
                number1 = number1 * number2;
            }
        } else {
            number1 = (int) (Math.random() * 100 + 1);
            number2 = (int) (Math.random() * 100 + 1);
        }
        int correctAnswer = calculateAnswer(number1, number2, operator);

        if (operator == '-' && correctAnswer < 0) {
            return generateExample();
        }
        String operatorSymbol = String.valueOf(operator);
        if (operator == '/') {
            operatorSymbol = ":";
        }
        String question = number1 + " " + operatorSymbol + " " + number2;

        // Ošetrenie opakovania príkladov
        if (generatedQuestions.contains(question)) {
            return generateExample();
        }

        generatedQuestions.add(question);
        return new MathExample(question, correctAnswer);
    }

    /**
     * Vyberie náhodný operátor zo zadaného zoznamu.
     *
     * @param operators Pole dostupných operátorov.
     * @return náhodný operátor.
     */
    private char randomOperator(char[] operators) {
    return operators[(int) (Math.random() * operators.length)];
    }

    /**
     * Vypočíta správny výsledok príkladu na základe operátora.
     *
     * @param number1 Prvé číslo.
     * @param number2 Druhé číslo.
     * @param operator Operátor (+,-,*,/).
     * @return Správny výsledok príkladu.
     */
    private int calculateAnswer(int number1, int number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> throw new IllegalArgumentException("Neznámy operátor: " + operator);
        };
    }
}
