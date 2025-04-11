package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entita predstavujúca matematický príklad v databáze.
 * Obsahuje text príkladu, správnu odpoveď, odpoveďpoužívateľa a informáciu o tom,
 * či je odpoveď správna.
 */
@Entity
@Table(name = "math_examples")
public class MathExample {

    /** Primárny kľúč pre entitu. */
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    /** Text matematického príkladu. */
        private String question;

    /** Správna odpoveď na príklad. */
    private int correctAnswer;

    /** Odpoveď zadaná používateľom. */
    private int userAnswer;

    /** Označuje, či je odpoveď používateľa správna. */
        private boolean isCorrect;

    /**
     * Bezparametrický konštruktor (potrebný pre JPA),
     */
    public MathExample() {
            this.isCorrect = false;
        }

    /**
     * Konštruktor pre vytvorenie matematického príkladu.
     *
     * @param question Text príkladu.
     * @param correctAnswer Správna odpoveď na príklad.
     */
    public MathExample(String question, int correctAnswer) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.userAnswer = 0;
            this.isCorrect = false;
        }

    /**
     * Získa ID príkladu.
     *
     * @return ID príkladu.
     */
    public Long getId() {
            return id;
        }

    /**
     * Nastaví ID príkladu.
     *
     * @param id Nové ID príkladu.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Získa text matematického príkladu.
     *
     * @return Text príkladu.
     */
    public String getQuestion() {
            return question;
        }

    /**
     * Nastaví text matematického príkladu.
     *
     * @param question Nový text príkladu.
     */
    public void setQuestion(String question) {
            this.question = question;
        }

    /**
     * Získa správnu odpoveď.
     *
     * @return Správna odpoveď.
     */
    public int getCorrectAnswer() {
            return correctAnswer;
        }

    /**
     * Nastaví správnu odpoveď.
     *
     * @param correctAnswer Nová správna odpoveď.
     */
    public void setCorrectAnswer(int correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

    /**
     * Získa odpoveď zadanú používateľom.
     *
     * @return odpoveď používateľa.
     */
    public int getUserAnswer() {
            return userAnswer;
        }

    /**
     * Nastaví odpoveď zadanú používateľom a automaticky vyhodnotí, či je správna.
     *
     * @param userAnswer Nová odpoveď používateľa.
     */
    public void setUserAnswer(int userAnswer) {
            this.userAnswer = userAnswer;
            this.isCorrect = (userAnswer == this.correctAnswer);
        }

    /**
     * Zistí, či je odpoveď používateľa správna.
     *
     * @return true, ak je odpoveď správna, inak false.
     */
    public boolean isCorrect() {
            return isCorrect;
        }
    }



