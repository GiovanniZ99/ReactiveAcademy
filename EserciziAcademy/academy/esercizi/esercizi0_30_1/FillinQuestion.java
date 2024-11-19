package academy.esercizi.esercizi0_30_1;
//
public class FillinQuestion extends Question {
    @Override
    public boolean checkAnswer(String answer) {
       String[] rispostaSingola = answer.split(",");
       String risposta = this.getText();
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
