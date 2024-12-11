package it.reactive.academy.SpringMvcStep1.exception;

public class ErrorResponse {
    private String cod;
    private String des;

    public ErrorResponse(String COD, String DES) {
        this.cod = COD;
        this.des = DES;
    }

    public ErrorResponse() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
