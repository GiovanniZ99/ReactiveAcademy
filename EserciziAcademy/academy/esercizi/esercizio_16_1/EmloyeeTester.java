package academy.esercizi.esercizio_16_1;
/*Realizzate la classe Employee (dipendente). Ogni dipendente ha un nome (una stringa)
e uno stipendio (di tipo double). Scrivete un costruttore con due parametri:public
Employee(String employeeName, double currentSalary) e i metodi:public String getName()
public double getSalary() public void raiseSalary(double byPercent). Tali metodi forniscono,
nell’ordine, il nome e lo stipendio del dipendente e ne aumentano il salario della percentuale
 indicata. Ecco un esempio di utilizzo:Employee harry = new Employee("Hacker, Harry", 50000);
 harry.raiseSaIary(io); // Harry ottiene un aumento di stipendio del 10%. Progettate anche la
 classe EmployeeTester che collaudi tutti i metodi.
 */
public class EmloyeeTester {
    public static void main(String[] args) {
        Employee harry = new Employee("Harry", 5000);
        harry.raiseSalary(100);
        System.out.println(harry.getCurrentSalary());
    }
}
