import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class PrikladGeneratorDruhyRocnik  implements  PrikladGenerator {
    private String priklad;
    private int spravnaOdpoved;
    private final Random random = new Random();
    private Set<String> vygenerovanePriklady = new HashSet<>();

    @Override
    public boolean pridajPrikladAkNeexistuje(String priklad) {
        return vygenerovanePriklady.add(priklad);
    }

    @Override
    public void generujPriklad() {
        int cislo1, cislo2;
        do {
            cislo1 = random.nextInt(50) + 1;
            cislo2 = random.nextInt(50) + 1;
            if (random.nextBoolean()) {
                spravnaOdpoved = cislo1 + cislo2;
                priklad = cislo1 + " + " + cislo2 + " = ?";
            } else {
                if (cislo2 > cislo1) {
                    int temp = cislo1;
                    cislo1 = cislo2;
                    cislo2 = temp;

                }
                spravnaOdpoved = cislo1 - cislo2;
                priklad = cislo1 + " - " + cislo2 + " = ?";
            }
        }
        while (!pridajPrikladAkNeexistuje(priklad));
        System.out.println(priklad);

        long startTime = System.currentTimeMillis();
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime > 40000) {
            System.out.println("Tento príklad bol náročný, bude zaradený na opakovanie.");
        }
    }
        public int getSpravnaOdpoved () {
            return spravnaOdpoved;
        }
        }






