package org.example.services;

import org.example.models.MathExample;
import org.springframework.stereotype.Service;

/**
 * Služba na generovanie matematických príkladov pre prvý ročník.
 * Obsahuje sčítanie a odčítanie čísel do 20, pričom sa vyhýba záporným výsledkom.
 * Výsledok nikdy neprekročí 20.
 */
@Service
public class FirstGradeMathService implements MathExampleGenerator {
    @Override
    public MathExample generateExample() {
        char operator = randomOperator(new char[]{'+', '-'});
        int number1, number2;

        if (operator == '+') {
            // Pre sčítanie zabezpečíme, že súčet nebude väčší ako 20
            number1 = (int) (Math.random() * 15 + 1); // Prvé číslo max 15
            number2 = (int) (Math.random() * (20 - number1) + 1); // Druhé číslo také, aby súčet nepresiahol 20
        } else {
            // Pre odčítanie zabezpečíme, že prvé číslo je väčšie a výsledok bude kladný
            number1 = (int) (Math.random() * 20 + 1);
            number2 = (int) (Math.random() * number1); // Druhé číslo menšie ako prvé
        }

        int correctAnswer = calculateAnswer(number1, number2, operator);
        String question = number1 + " " + operator + " " + number2;
        return new MathExample(question, correctAnswer);
    }

    /**
     * Vyberie náhodný operátor zo zadného zoznamu.
     *
     * @param operators Pole dostupných operátorov.
     * @return Náhodný operátor.
     */
    private char randomOperator(char[] operators) {
        return operators[(int) (Math.random() * operators.length)];
    }

    /**
     * Vykoná matematickú operáciu na základe zadaných čísel a operátora.
     *
     * @param number1 Prvé číslo.
     * @param number2 Druhé číslo.
     * @param operator Operátor ('+', '-').
     * @return Výsledok operátora.
     */
    private int calculateAnswer(int number1, int number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            default -> 0;
        };
    }
}



