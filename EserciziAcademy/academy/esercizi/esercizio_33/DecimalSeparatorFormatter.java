package academy.esercizi.esercizio_33;

public class DecimalSeparatorFormatter implements NumberFormatter {
    @Override
    public String format(int n) {
        String numeroDaConvertire = String.valueOf(n);
        int scaglioni = (int) Math.ceil((double) numeroDaConvertire.length() / 3);
        String[] result = new String[scaglioni];
        int index = result.length;
        int count = 0;
        StringBuilder finale;

        for (int i = 0; i < result.length; i++) {
            result[i] = "";
        }

        for (int i = numeroDaConvertire.length(); i > 0; i--) {
            count++;
            result[index-1] = numeroDaConvertire.charAt(i - 1) + result[index-1];
            if (count == 3) {
                index--;
                count = 0;
            }

        }
        finale = new StringBuilder();
        for (int j = 0; j < result.length; j++) {
            if(j==0){
                finale.append(result[j]);
            }else {
                finale.append(",").append(result[j]);
            }

        }

        return finale.toString();

    }
}
