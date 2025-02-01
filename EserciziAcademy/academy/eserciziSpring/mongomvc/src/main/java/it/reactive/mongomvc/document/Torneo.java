package it.reactive.mongomvc.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "tornei")
public class Torneo {

    @Id
    private String id;

    private String nomeTorneo;

    private List<String> squadreId;

    public Torneo(String id, String nomeTorneo, List<String> squadreId) {
        this.id = id;
        this.nomeTorneo = nomeTorneo;
        this.squadreId = squadreId;
    }

    public Torneo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public List<String> getSquadreId() {
        return squadreId;
    }

    public void setSquadreId(List<String> squadreId) {
        this.squadreId = squadreId;
    }
}
