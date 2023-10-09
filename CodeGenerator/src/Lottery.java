import javax.swing.*;
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

        String newCode = codeValidation("");

        boolean isWinning = false;
        System.out.print("Czy ten kod został już użyty? \n" +
                "Wpisz T jeśli tak lub N jeśli nie: ");
        boolean isUsed = sc.nextLine().equalsIgnoreCase("t");
        System.out.println();
        if (!isUsed) {
            System.out.print("Czy chcesz ustawić ten kod jako zwycięski?\n" +
                    "Wpisz T jeśli tak lub N jeśli nie: ");
            isWinning = sc.nextLine().equalsIgnoreCase("t");
            System.out.println();
        }
        setValuesForSameCodes(newCode, isUsed, isWinning);
        this.codes.add(new Code(newCode, isWinning, isUsed));
        System.out.println("###########################");
        System.out.println("Twój nowy kod został dodany");
        System.out.println("###########################");
    }

    private void setValuesForSameCodes(String newCode, boolean isUsed, boolean isWinning) {
        sc = new Scanner(System.in);
        for (Code code : codes) {
            if (code.getCode().equals(newCode)) {
                System.out.print("Wpisany kod znajduje się już na liście. Czy chcesz go nadpisać swoimi wartościami?\n" +
                        "Wpisz T jeśli tak lub N jeśli nie: ");
                if (sc.nextLine().equalsIgnoreCase("t")) {
                    code.setUsed(isUsed);
                    code.setWinning(isWinning);
                }
                System.out.println();
            }
        }
    }

    private String codeValidation(String newCode) {
        while (newCode.length() < 5 || newCode.length() > 10 || !(checkIfhasCorrectedChars(newCode))) {
            System.out.println("Nowy kod musi mieć od 5 do 10 znaków i składać się tylko z liter i cyfr");
            System.out.print("Wpisz kod:");
            newCode = sc.nextLine();
            System.out.println();
        }
        return newCode;
    }

    private boolean checkIfhasCorrectedChars(String newCode) {
        char[] letters = newCode.toCharArray();
        for (char letter : letters) {
            if (letter < 48 || (letter > 57 && letter < 65) || (letter > 90 && letter < 97) || letter > 122) {
                return false;
            }
        }
        return true;
    }

    public void displayCodes() {
        for (Code code : codes) {
            System.out.println(codes.indexOf(code) + 1 + ". " + code.getCode() +
                    " Użyty: " + code.getIsUsed() +
                    " Wygrany: " + code.getIsWinning());
        }
    }

    public void editCode() {
        System.out.print("Wybierz kod który chciałbyś edytować");
        int selection = new Scanner(System.in).nextInt() - 1;
        System.out.println();
        Code selectedCode = codes.get(selection);
        System.out.print("Czy chcesz zmienic jego numery? [T/N]: ");
        boolean editCodeText = new Scanner(System.in).nextLine().equalsIgnoreCase("t");
        System.out.println();
        if (editCodeText) {
            String newCode = codeValidation("");
            codes.get(selection).setCode(newCode);
            System.out.println(codes.get(selection).getCode());
        }
        System.out.print("Czy chcesz zmienić jego status użycia? [T/N]: ");
        boolean editUseStatus = new Scanner(System.in).nextLine().equalsIgnoreCase("t");
        System.out.println();
        if (editUseStatus) {
            selectedCode.setUsed(!(selectedCode.getIsUsed()));
            System.out.println("Dla wybranego kodu status uzycia został zmieniony na: " + selectedCode.getIsUsed());
        }
        System.out.print("Czy chcesz zmienić jego status zwycięstwa? [T/N]: ");
        boolean editWinStatus = new Scanner(System.in).nextLine().equalsIgnoreCase("t");
        System.out.println();
        if (editWinStatus) {
            selectedCode.setWinning(!(selectedCode.getIsWinning()));
            System.out.println("Dla wybranego kodu status uzycia został zmieniony na: " + selectedCode.getIsWinning());
        }
        setValuesForSameCodes(selectedCode.getCode(), selectedCode.isUsed, selectedCode.isWinning);
    }

    public void removeCode() {
        System.out.println("Wybierz z listy kod który chciałbyś usunąć: ");
        displayCodes();
        int selection = new Scanner(System.in).nextInt() - 1;
        System.out.print("Czy napewno chcesz usunąć kod: " + codes.get(selection).getCode() + " z listy kodów? [T/N]");
        if (sc.nextLine().equalsIgnoreCase("t")) {
            codes.remove(selection);
        }
        System.out.println();
        System.out.println("Aktualna lista: ");
        displayCodes();
        System.out.println();
    }
}
