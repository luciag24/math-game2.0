import java.util.*;

public class PrikladGeneratorPrvyRocnik  implements PrikladGenerator {
    private int spravnaOdpoved;
    private final Random random = new Random();
    private List<String> examples = new ArrayList<>();
    private final Set<String> vygenerovanePriklady = new HashSet<>();


    public PrikladGeneratorPrvyRocnik() {
        generujPrikladPreScitanie();
        generujPrikladPreOdcitanie();
    }

    private void generujPrikladPreScitanie() {
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(21);
            int b = random.nextInt(21 - a);
            spravnaOdpoved = a + b;
            String priklad = a + " + " + b + " = ?";
            pridajPrikladAkNeexistuje(priklad);
        }
    }

    private void generujPrikladPreOdcitanie() {
        for (int i = 0; i < 10; i++) {
            int a = random.nextInt(21);
            int b = random.nextInt(a + 1);
            spravnaOdpoved = a - b;
            String priklad = a + " - " + b + " = ?";
            pridajPrikladAkNeexistuje(priklad);
        }
    }
    @Override
    public void generujPriklad() {
        if (examples.isEmpty()) {
            throw new IllegalArgumentException("Zoznam príkladov je prázdny. Skontrolujte inicializáciu generatora.");
        }
        int index = random.nextInt(examples.size());
        String priklad = examples.get(index);
        System.out.println("Vygenerovany príklad: " + priklad);
    }

    @Override
    public int getSpravnaOdpoved() {
        return spravnaOdpoved;
    }

    @Override
    public boolean pridajPrikladAkNeexistuje(String priklad) {
        if (vygenerovanePriklady.add(priklad)) {
            examples.add(priklad);
            System.out.println("Pridaný nový príklad: " + priklad);
            return true;
        } else {
            System.out.println("Príklad už existuje: " + priklad);
            return false;
        }

    }
}


