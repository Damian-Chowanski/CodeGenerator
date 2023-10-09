import java.util.ArrayList;
import java.util.Scanner;

public class Lottery {
    private ArrayList<Code> codes;
    private Scanner sc = new Scanner(System.in);

    public Lottery() {
        this.codes = new ArrayList<>();
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }

    public void setCodes(ArrayList<Code> codes) {
        this.codes = codes;
    }

    public void addNewCode() {

        System.out.println("Wpisz kod który mam dodać: ");
        String newCode = sc.nextLine();

        newCode = codeValidation(newCode);

        boolean isWinning = false;
        System.out.println("Czy ten kod został już użyty? \n" +
                "Wpisz T jeśli tak lub N jeśli nie: ");
        boolean isUsed = sc.nextLine().equalsIgnoreCase("t");
        if (!isUsed){
            System.out.println("Czy chcesz ustawić ten kod jako zwycięski?\n" +
                    "Wpisz T jeśli tak lub N jeśli nie: ");
             isWinning = sc.nextLine().equalsIgnoreCase("t");
        }
        setValuesForSameCodes(newCode,isUsed,isWinning);
        this.codes.add(new Code(newCode,isWinning,isUsed));
    }

    private void setValuesForSameCodes(String newCode, boolean isUsed, boolean isWinning) {
        sc = new Scanner(System.in);
        for (Code code:codes){
            if(code.getCode().equals(newCode)){
                System.out.println("Wpisany kod znajduje się już na liście. Czy chcesz go nadpisać swoimi wartościami?\n" +
                        "Wpisz T jeśli tak lub N jeśli nie: ");
                if (sc.nextLine().equalsIgnoreCase("t")) {
                    code.setUsed(isUsed);
                    code.setWinning(isWinning);
                }
            }
        }
    }

    private String codeValidation(String newCode) {
        while (newCode.length() < 5 || newCode.length() > 10 || !(checkIfhasCorrectedChars(newCode))) {
            System.out.println("Nowy kod musi mieć od 5 do 10 znaków i składać się tylko z liter i cyfr");
            System.out.println("Podaj właściwy kod:");
            newCode = sc.nextLine();
        }
        return newCode;
    }

    private boolean checkIfhasCorrectedChars(String newCode) {
        char[] letters = newCode.toCharArray();
        for (char letter : letters) {
            if (letter < 48 || (letter > 57 && letter < 65) || (letter > 90 && letter <97) || letter >122) {
                return false;
            }
        }
        return true;
    }
    public void displayCodes() {
        for (Code code: codes){
            System.out.println(codes.indexOf(code)+1 + ". " + code.getCode());
        }
    }
}
