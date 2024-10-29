package academy.esercizi.esercizio_16_1;

public class Employee {
    private final String nome;
    private double currentSalary;

    public Employee(String nome, double currentSalary) {
        this.nome = nome;
        this.currentSalary = currentSalary;
    }

    public void raiseSalary(double byPercent){
        this.currentSalary += this.currentSalary* (byPercent/100);
    }

    public String getNome() {
        return nome;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }
}
