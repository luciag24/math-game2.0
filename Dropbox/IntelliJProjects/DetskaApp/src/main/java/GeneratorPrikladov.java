import java.util.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class GeneratorPrikladov {
    private int spravnaOdpoved;
    public Rocnik rocnik;
    private final Map<Rocnik, PrikladGenerator> generators = new HashMap<>();
    private PrikladGenerator generator;
    private Random random = new Random();

    public GeneratorPrikladov(Rocnik rocnik) {
        generators.put(Rocnik.PRVY, new PrikladGeneratorPrvyRocnik());
        generators.put(Rocnik.DRUHY, new PrikladGeneratorDruhyRocnik());
        generators.put(Rocnik.TRETI, new PrikladGeneratorTretiRocnik());
        generators.put(Rocnik.STVRTY, new PrikladGeneratorStvrtyRocnik());

        this.generator = generators.get(rocnik);
        // System.out.println("Debug: Inicializovaný generator pre ročník " + rocnik + " je:" + this.generator);
        if (this.generator == null) {
            throw new IllegalArgumentException("Chyba: Generator pre rocnik " + rocnik + " nie je iniciolizovany.");
        }
    }

    public int generujPriklad() {
        if (generator == null) {
            System.out.println("Žiadny generátor nebol inicializovaný");
            return -1;
        }
        generator.generujPriklad();
        return generator.getSpravnaOdpoved();
    }
}









