package org.example.services;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.example.models.MathExample;
import org.example.models.UnitType;
import org.springframework.stereotype.Service;

/**
 * Služba na generovanie príkladov na premenu jednotiek.
 * Môže byť použitá nezávisle od ročníkových príkladkov.
 */
@Service
public class UnitConversionService {
    private final Random random = new Random();
    private final Set<String> generatedQuestions = new HashSet<>();

    public MathExample generateUnitConversionExample() {
        UnitType unitType;
        int number;
        double correctAnswer;

        String question;
        do {
            //Hľadáme iba také príklady, kde výsledok je celé číslo
            do {
                unitType = UnitType.getRandomUnitType();
                number = random.nextInt(1000) + 1; //náhodné celé číslo 1-1000
                correctAnswer = number * unitType.getConversionRate();
            } while (correctAnswer % 1 != 0); // ak nie je celé číslo, opakuj

            question = String.format("Premeň: %d %s = ? %s", 
                number, unitType.getFrom(), unitType.getTo());
        } while (generatedQuestions.contains(question)); // ak sa príklad opakuje, generuj nový

        generatedQuestions.add(question);
        return new MathExample(question, (int) correctAnswer);
    }
}