package academy.esercizi.esercizio_37_8;

public class FillinQuestion extends Question<String>{

    @Override
    public boolean checkAnswer(String answer) {
        String[] rispostaSingola = answer.split(",");
        String risposta =  this.getText();
        for (int i = 0; i < rispostaSingola.length; i++) {
            risposta = risposta.replaceFirst("\\*\\*\\*\\*", rispostaSingola[i]);
        }
        return risposta.equals(this.getAnswer());
    }

@Override
    public void display() {
        System.out.println(this.getText());
    }
}
