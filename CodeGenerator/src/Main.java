import java.util.*;

public class Main {
    static Random rand = new Random();
    static int numberOfCodes;
    static ArrayList<String> codes = new ArrayList<>();

    public static void main(String[] args) {

        System.out.print("Podaj ile kodów mam dla Ciebie wygenerować: ");
        getNumberOfCodes();
        generateCodes();
        displayCodes();
    }

    private static void displayCodes() {
        for (String code : codes) {
            double usedChance = 0.65;
            double winChance = 0.95;


            if (rand.nextDouble() > usedChance) {
                System.out.println("kod: " + code + " Został już użyty");
            } else if (rand.nextDouble() > winChance) {
                    System.out.println("Kod " + code + " Wygrywa!");
            } else System.out.println("Kod " + code + " Nie został jeszcze użyty i nie jest zwycięski");

        }
    }

    private static void generateCodes() {
        RandomInRanges rir = new RandomInRanges(48, 57);
        rir.addRange(65, 90);
        rir.addRange(97, 122);
        for (int i = 0; i < numberOfCodes; i++) {
            int codeLength = rand.nextInt(5, 11);
            String code = "";
            for (int j = 0; j < codeLength; j++) {
                code += (char) rir.getRandom();
            }
            codes.add(code);
        }
    }

    private static void getNumberOfCodes() {
        Scanner scanner = new Scanner(System.in);
        try {
            numberOfCodes = scanner.nextInt();
            if (numberOfCodes <= 0) {
                System.out.println("Wartość musi być równa minimum 1");
                System.out.print("Podaj prawidłową liczbę: ");
                getNumberOfCodes();
            }
        } catch (InputMismatchException IME) {
            System.out.print("Nie podałeś prawidłowej wartości" +
                    "\nPodaj prawidłową liczbę: ");
            getNumberOfCodes();
        }
    }
}