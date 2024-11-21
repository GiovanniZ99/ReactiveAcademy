package academy.esercizi.esercizio_36_3;

public class Test {
    public static void main(String[] args) throws ErroreVersamento {
        creaConto();
    }
    public static void creaConto() throws ErroreVersamento {
        ContoCorrente contoTest = new ContoCorrente(100, "1");
        try {
            System.out.println(contoTest.versa(2000));
        } catch (ErroreVersamento e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(contoTest.versa(500));
        } catch (ErroreVersamento e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(contoTest.preleva(200));
        } catch (ErroreVersamento e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(contoTest.preleva(900));
        } catch (ErroreVersamento e) {
            System.out.println(e.getMessage());
        }
    }
}
