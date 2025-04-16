package org.example.controllers;

import org.example.models.MathExample;
import org.example.services.UnitConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pre generovanie náhodných príkladov na premenu jednotiek.
 * <p>
 * Voliteľná funkcionalita, ktorá nezasahuje do ostanej logiky aplikácie.
 * Slúži na precvičovanie premien jednotiek ako doplnková úloha.
 */
@RestController
@RequestMapping("/api/conversion")
public class UnitConversionController {

    private final UnitConversionService unitConversionService;

    public UnitConversionController(UnitConversionService unitConversionService) {
        this.unitConversionService = unitConversionService;
    }

    /**
     * Endpoint na získanie náhodného príkladu na premenu jednotiek.
     *
     * @return príklad typu MathExample s otázkou a správnou odpoveďou.
     */
    @GetMapping
    public MathExample getConversionExample() {
        return unitConversionService.generateUnitConversionExample();
    }
}
