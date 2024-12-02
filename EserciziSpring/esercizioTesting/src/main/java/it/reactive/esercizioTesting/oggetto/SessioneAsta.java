package it.reactive.esercizioTesting.oggetto;

import java.util.List;

public class SessioneAsta {
    private int valore;
    private String vincitore;
    private String oggettoBandito;
    private List<String> partecipanti;
    private List<String> partecipantiPassati;
    private int progressivoTurnoCorrente;
    private boolean inCorso;

    public SessioneAsta(List<String> partecipanti) {
        super();
        setProgressivoTurnoCorrente(0);
        valore = 1;
        vincitore = partecipanti.get(0);
        this.partecipanti = partecipanti;
    }

    public String getOggettoBandito() {
        return oggettoBandito;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }

    public String getVincitore() {
        return vincitore;
    }

    public void setVincitore(String vincitore) {
        this.vincitore = vincitore;
    }

    public void setOggettoBandito(String oggettoBandito) {
        this.oggettoBandito = oggettoBandito;
    }

    public List<String> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<String> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public int getProgressivoTurnoCorrente() {
        return progressivoTurnoCorrente;
    }

    public boolean isInCorso() {
        return inCorso;
    }

    public void setInCorso(boolean inCorso) {
        this.inCorso = inCorso;
    }

    public void setProgressivoTurnoCorrente(int progressivoTurnoCorrente) {
        this.progressivoTurnoCorrente = progressivoTurnoCorrente;
    }

    public List<String> getPartecipantiPassati() {
        return partecipantiPassati;
    }

    public void setPartecipantiPassati(List<String> partecipantiPassati) {
        this.partecipantiPassati = partecipantiPassati;
    }

    @Override
    public String toString() {
        return "SessioneAsta [valore=" + valore + ", vincitore=" + vincitore + ", oggettoBandito=" + oggettoBandito
                + ", partecipanti=" + partecipanti + ", partecipantiPassati=" + partecipantiPassati
                + ", progressivoTurnoCorrente=" + progressivoTurnoCorrente + ", inCorso=" + inCorso + "]";
    }


}
