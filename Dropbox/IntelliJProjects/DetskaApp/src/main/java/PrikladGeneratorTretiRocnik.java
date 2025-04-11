import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class PrikladGeneratorTretiRocnik implements PrikladGenerator {
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
        String priklad;
        do {
            int typ = random.nextInt(4);
            int cislo1, cislo2;


            switch (typ) {
                case 0 -> {
                    cislo1 = random.nextInt(1000) + 100;
                    cislo2 = random.nextInt(1000) + 100;
                    spravnaOdpoved = cislo1 + cislo2;
                    priklad = cislo1 + " + " + cislo2 + " = ?";
                }
                case 1 -> {
                    cislo1 = random.nextInt(1000) + 100;
                    cislo2 = random.nextInt(1000) + 100;
                    if (cislo2 > cislo1) {
                        int temp = cislo1;
                        cislo1 = cislo2;
                        cislo2 = temp;
                    }
                    spravnaOdpoved = cislo1 - cislo2;
                    priklad = cislo1 + " - " + cislo2 + " = ?";
                }
                case 2 -> {
                    cislo1 = random.nextInt(10) + 1;
                    cislo2 = random.nextInt(10) + 1;
                    spravnaOdpoved = cislo1 * cislo2;
                    priklad = cislo1 + " * " + cislo2 + " = ?";
                }
                case 3 -> {

                    cislo2 = random.nextInt(9) + 1;
                    spravnaOdpoved = cislo2 * (random.nextInt(10) + 1);
                    cislo1 = spravnaOdpoved;
                    priklad = cislo1 + " / " + cislo2 + " = ?";
                }
                default -> {
                    spravnaOdpoved = 0;
                    priklad = "";
                }
            }
        } while (!pridajPrikladAkNeexistuje(priklad));
            System.out.println(priklad);



            long startTime = System.currentTimeMillis();
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > 30000) {
                System.out.println("Tento príklad bol náročný, bude zaradený na opakovanie.");
            }
    }
    public int getSpravnaOdpoved() {
        return spravnaOdpoved;
    }
    }

