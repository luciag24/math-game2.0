package org.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.models.AnswerRequest;
import org.example.models.MathExample;
import org.example.services.MathExampleService;
import org.example.repositories.MathExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RestController pre validáciu odpovedí.
 * Obsahuje endpoint na overenie správnosti odpovede.
 */
@RestController
@RequestMapping("/api")
public class ValidateController {

    private final MathExampleService mathExampleService;
    private final MathExampleRepository mathExampleRepository;

    @Autowired
    public ValidateController(MathExampleService mathExampleService, MathExampleRepository mathExampleRepository) {
        this.mathExampleService = mathExampleService;
        this.mathExampleRepository = mathExampleRepository;
    }

    /**
     * Overí, či je odpoveď správna.
     *
     * @param request Požiadavka obsahujúca odpoveď na validáciu.
     * @return ResponseEntity so správou o správnostu odpovede.
     */
    @PostMapping("/validate")
    public ResponseEntity<String> validateAnswer(@RequestBody AnswerRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().body("Invalid request: request is missing");
        }
        
        // Get the correct answer from the database
        MathExample example = mathExampleRepository.findById((long) request.getQuestionId()).orElse(null);
        if (example == null) {
            return ResponseEntity.badRequest().body("Invalid request: question not found");
        }
        
        // Compare the user's answer with the correct answer
        if (request.getUserAnswer() == example.getCorrectAnswer()) {
            return ResponseEntity.ok("Correct answer!");
        } else {
            return ResponseEntity.ok("Wrong answer! The correct answer was: " + example.getCorrectAnswer());
        }
    }
}
