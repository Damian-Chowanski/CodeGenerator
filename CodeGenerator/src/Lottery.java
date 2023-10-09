import java.util.ArrayList;
import java.util.Scanner;

public class Lottery {
    private ArrayList<Code> codes;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Wpisz kod który mam dodać: ");
        String newCode = sc.nextLine();
        while ((newCode.length() < 5 || newCode.length() > 10)) {
            System.out.println("Nowy kod musi mieć od 5 do 10 znaków");
            newCode = sc.nextLine();
        }
    }
}
