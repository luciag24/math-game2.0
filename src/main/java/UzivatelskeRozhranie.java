import java.util.Scanner;
public class UzivatelskeRozhranie {
    private final GeneratorPrikladov generator;
    private final Scanner scanner;
    private int spravneOdpovede;
    private int nespravneOdpovede;
    private long startCas;
    private long endCas;

    public UzivatelskeRozhranie(GeneratorPrikladov generator) {
        this.generator = generator;
        this.scanner = new Scanner(System.in);
        this.spravneOdpovede = 0;
        this.nespravneOdpovede = 0;
    }

    public void start() {
        System.out.println("Začnime s riešením príkladov!");
        boolean pokracovat = true;

        startCas = System.currentTimeMillis();

        while (pokracovat) {
            int spravnaOdpoved = generator.generujPriklad();
            System.out.println("Vaša odpoveď: ");

            try {
                int odpoved = Integer.parseInt(scanner.nextLine());
                if (odpoved == spravnaOdpoved) {
                    System.out.println("Správne!");
                    spravneOdpovede++;
                } else {
                    System.out.println("Nesprávne, skús to znova.");
                    nespravneOdpovede++;
                }
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup! Prosím, zadajte číslo.");
                nespravneOdpovede++;
            }
            System.out.println();
        }
        }
        public int getPocetSpravnych() {
            return spravneOdpovede;
        }
        public int getPocetNespravnych() {
            return nespravneOdpovede;
        }
        public long getCas() {
            return (System.currentTimeMillis() - startCas) / 1000;
        }

    }




