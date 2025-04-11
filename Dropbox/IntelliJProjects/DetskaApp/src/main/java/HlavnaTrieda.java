import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HlavnaTrieda {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<Integer, Rocnik> rocnikMap = new HashMap<>();
static {
    rocnikMap.put(1, Rocnik.PRVY);
    rocnikMap.put(2, Rocnik.DRUHY);
    rocnikMap.put(3, Rocnik.TRETI);
    rocnikMap.put(4, Rocnik.STVRTY);
}
        public static void main(String[] args) {
            System.out.println("=============================================");
            System.out.println("      Matematická aplikácia pre deti!");
            System.out.println("=============================================");

            int cisloRocnika = getGradeFromUser();
            Rocnik rocnik = rocnikMap.get(cisloRocnika);
            System.out.println();
           // System.out.println("Debug: Priradený ročník: " + rocnik);

            if (rocnik == null) {
                System.out.println("Neplatný ročník. Prosím zadajte číslo medzi 1 a 4.");
                return;
            }
            System.out.println("---------------------------------------------");
            System.out.println("Nastavili ste úroveň ročníka: " + rocnik.getPopis());
            System.out.println("Generujeme príklady pre: " + rocnik.getPopis());
            System.out.println("---------------------------------------------");

            GeneratorPrikladov generator = new GeneratorPrikladov(rocnik);
            UzivatelskeRozhranie ui = new UzivatelskeRozhranie(generator);
            ui.start();

            System.out.println("---------------------------------------------");
            System.out.println("Test skončil! Ďakujme, že ste si zahrali.");
            System.out.println("Počet správnych odpovedí: " + ui.getPocetSpravnych());
            System.out.println("Počet nesprávnych odpovedí: " +ui.getPocetNespravnych());
            System.out.println("Čas: " + ui.getCas() + " sekúnd");
            System.out.println("=============================================");
        }


        private static int getGradeFromUser() {
            while (true) {
                try {
                    System.out.println("Zadaj ročník (1 - 4): ");
                    int cislo = Integer.parseInt(scanner.nextLine().trim());
                    if (cislo >= 1 && cislo <= 4) {
                        return cislo;
                    }
                    System.out.println("Neplatný vstup! Zadaj číslo medzi 1 a 4.");
                } catch (NumberFormatException e) {
                    System.out.println("Neplatný vstup! Skús znova.");
                }
            }

        }
}
