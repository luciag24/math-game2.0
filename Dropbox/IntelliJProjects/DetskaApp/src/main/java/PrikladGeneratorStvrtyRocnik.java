import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class PrikladGeneratorStvrtyRocnik implements  PrikladGenerator {

    private String priklad;
    private int spravnaOdpoved;
    private final Random random = new Random();
    private List<String> examples; //kolekcia na ulozenie prikladov
    private Set<String> vygenerovanePriklady = new HashSet<>();

    private static final int MM_IN_CM = 10;
    private static final int CM_IN_M = 100;
    private static final int M_IN_KM = 1000;
    private static final int DM_IN_M = 10;

    private static final int G_IN_KG = 1000;
    private static final int DKG_IN_KG = 100;

    private static final int ML_IN_CL = 10;
    private static final int CL_IN_L = 100;
    private static final int ML_IN_L = 1000;

    public PrikladGeneratorStvrtyRocnik() {
        examples = new ArrayList<>();
        generujPrikladPreDlzku();;
        generujPrikladPreHmotnost();
        generujPrikladPreObjem();
        generujPrikladpreScitanie();
        generujPrikladPreOdcitanie();
        generujPrikladPreNasobenie();
        generujPrikladPreDelenie();

    }

    @Override
    public boolean pridajPrikladAkNeexistuje(String priklad) {
        return vygenerovanePriklady.add(priklad);
    }

    public void generujPriklad() {
        if (examples.isEmpty()) {
            throw new IllegalArgumentException("Zoznam príkladov je prázdny. Skontrolujte inicializáciu.");
        }
        int index = random.nextInt(examples.size());
        String priklad = examples.get(index);
        System.out.println("Vygenerovaný príklad: " + priklad);
    }
    private void addExample(String priklad) {
        examples.add(priklad);
        System.out.println("Pridaný príklad: " + priklad);
    }
    private int validateBound(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Hodnota bound musí byť kladná.");
        }
        return bound;
    }

    private void generujPrikladpreScitanie() {
        int bound = validateBound(10);
        int a = random.nextInt(bound);
        int b = random.nextInt(bound);
        spravnaOdpoved = a + b;
        addExample(a + " + " + b + " = ?");
    }

    private void generujPrikladPreOdcitanie() {
        int bound = validateBound(10);
        int a = random.nextInt(bound) + 1;
        int b = random.nextInt(a);//bude menšie alebo = a
        spravnaOdpoved = a - b;
        addExample(a + " - " + b + " = ?");
    }

    private void generujPrikladPreNasobenie() {
        int bound = validateBound(10);
        int a = random.nextInt(bound) + 1;
        int b = random.nextInt(bound) + 1;
        spravnaOdpoved = a * b;
        addExample(a + " * " + b + " = ?");
    }

    private void generujPrikladPreDelenie() {
        int bound = validateBound(10);
        int b = random.nextInt(bound - 1) + 1; //delenie 0 nie je povolene
        int a = b * random.nextInt(bound);
        spravnaOdpoved = a / b;
        addExample(a + " / " + b + " = ?");
    }

    private void generujPrikladPreDlzku() {
        int smer = random.nextInt(2);
        int hodnota = random.nextInt(validateBound(900)) + 100;
        if (smer == 0) {
            spravnaOdpoved = hodnota * M_IN_KM;
            addExample(hodnota + "km je koľko metrov?");

        } else {
           spravnaOdpoved = hodnota / CM_IN_M;
           addExample(hodnota + " cm je koľko metrov?");
            }
        }

        private void generujPrikladPreHmotnost() {
        int smer = random.nextInt(2);
        int hodnota = random.nextInt(validateBound(900)) + 100;

        if (smer == 0) {
            spravnaOdpoved = hodnota * G_IN_KG;
            addExample(hodnota + " kg je koľko gramov?");
        } else {
            spravnaOdpoved = hodnota / DKG_IN_KG;
            addExample(hodnota + " dkg je koľko kilogramov?");
        }
    }

    private void generujPrikladPreObjem() {
        int smer = random.nextInt(2);
        int hodnota = random.nextInt(validateBound(900)) + 100;

        if (smer == 0) {
            spravnaOdpoved = hodnota * ML_IN_L;
            addExample(hodnota + " l je koľko mililtrov?");
        } else {
            spravnaOdpoved = hodnota / CL_IN_L;
            addExample(hodnota + " cl je koľko litrov?");
        }
    }

    @Override
    public int getSpravnaOdpoved () {
            return spravnaOdpoved;
        }
    }


