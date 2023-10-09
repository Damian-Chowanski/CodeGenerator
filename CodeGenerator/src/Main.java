import java.util.*;

public class Main {
    static Random rand = new Random();
    static int numberOfCodes;
    static Lottery lottery = new Lottery();
    static Scanner sc;


    public static void main(String[] args) {
        saveCodesForCurrentLottery(generateCodes());
        menu();
    }

    private static void menu() {
        sc = new Scanner(System.in);
        System.out.print("""
                Wybierz opcje, która cię interesuje:\s
                1. Dodaj kolejny kupon do listy
                2. Edytuj wybrany kupon
                3. Usuń wybrany kupon
                4. Wyświetl listę kodów
                5. Wygeneruj nową listę kodów
                0. Zamknij program.

                Twój wybór [1-3]:\s""");
        String selection = sc.nextLine();
        System.out.println();
        switch(selection) {
            case "1":
                lottery.addNewCode();
                menu();
            case "2":
                System.out.println("Edytuj");
                System.out.println();
                menu();
            case "3":
                System.out.println("Usuń");
                System.out.println();
                menu();
            case "4":
                lottery.displayCodes();
                menu();
            case "5":
                saveCodesForCurrentLottery(generateCodes());
                menu();
            case "0":
                break;
            default:
                System.out.println("Podałeś nieprawidłową wartość");
        }

    }

    private static void saveCodesForCurrentLottery(ArrayList<String> generatedCodes) {
        lottery.setCodes(new ArrayList<Code>());
        for (String code : generatedCodes) {
            double usedChance = 0.65;
            double winChance = 0.95;

            if (rand.nextDouble() > usedChance) {
                lottery.getCodes().add(new Code(code,false,true));
            } else if (rand.nextDouble() > winChance) {
                lottery.getCodes().add(new Code(code,true,false));
            } else lottery.getCodes().add(new Code(code,false,false));
        }
    }

    private static ArrayList<String> generateCodes() {
        ArrayList<String> generatedCodes = new ArrayList<>();
        System.out.print("Podaj ile kodów mam dla Ciebie wygenerować: ");
        getNumberOfCodes();
        RandomInRanges rir = new RandomInRanges(48, 57);
        rir.addRange(65, 90);
        rir.addRange(97, 122);

        for (int i = 0; i < numberOfCodes; i++) {
            int codeLength = rand.nextInt(5, 11);
            String code = "";
            for (int j = 0; j < codeLength; j++) {
                code += (char) rir.getRandom();
            }
            if (generatedCodes.contains(code)){
                i--;
            } else generatedCodes.add(code);
        }
        return generatedCodes;
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