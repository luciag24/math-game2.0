package org.example.models;

import java.util.Random;

/**
 * Výpočtový typ reprezentujúci možné premeny jednotiek.
 */
public enum UnitType {
    // Dĺžkové jednotky
    KM_TO_M("km", "m", 1000.0),
    M_TO_KM("m", "km", 0.001),
    M_TO_CM("m", "cm", 100.0),
    M_TO_DM("m", "dm", 10.0),
    DM_TO_M("dm", "m", 0.1),
    CM_TO_M("cm", "m", 0.01),
    CM_TO_MM("cm", "mm", 10.0),
    MM_TO_CM("mm", "cm", 0.1),
    DM_TO_CM("dm", "cm", 10.0),
    CM_TO_DM("cm", "dm", 0.1),

    // Objemové jednotky
    M3_TO_DM3("m³", "dm³", 1000.0),
    DM3_TO_M3("dm³", "m³", 0.001),
    M3_TO_L("m³", "l", 1000.0),
    L_TO_M3("l", "m³", 0.001),
    L_TO_ML("l", "ml", 1000.0),
    ML_TO_L("ml", "l", 0.001),
    L_TO_DM3("l", "dm³", 1.0),
    DM3_TO_L("dm³", "l", 1.0),

    // Hmotnostné jednotky
    KG_TO_G("kg", "g", 1000.0),
    G_TO_KG("g", "kg", 0.001),
    G_TO_MG("g", "mg", 1000.0),
    MG_TO_G("mg", "g", 0.001),
    T_TO_KG("t", "kg", 1000.0),
    KG_TO_T("kg", "t", 0.001),
    KG_TO_DAG("kg", "dag", 100.0),
    DAG_TO_KG("dag", "kg", 0.01),
    DAG_TO_G("dag", "g", 10.0),
    G_TO_DAG("g", "dag", 0.1);

    private final String from;
    private final String to;
    private final double conversionRate;

    UnitType(String from, String to, double conversionRate) {
        this.from = from;
        this.to = to;
        this.conversionRate = conversionRate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public static UnitType getRandomUnitType() {
        UnitType[] values = UnitType.values();
        return values[new Random().nextInt(values.length)];
    }
}
