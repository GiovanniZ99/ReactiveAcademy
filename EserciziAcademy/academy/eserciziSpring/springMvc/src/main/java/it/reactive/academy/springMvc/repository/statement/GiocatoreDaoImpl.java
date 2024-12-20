package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class GiocatoreDaoImpl implements GiocatoreDao {


    private final DatabaseConfig databaseConfig;

    public GiocatoreDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) {
        return null;
    }



    @Override
    public Set<GiocatoreDTOExtended> readAll(SquadraModel squadraModel) throws SQLException {
        Set<GiocatoreDTOExtended> listaGiocatori = new HashSet<>();
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        Statement statement1 = databaseConfig.getCon().createStatement();
        ResultSet resultGiocatori = statement1.executeQuery("select * from giocatore where id_squadra = "
                + squadraModel.getIdSquadra());
        while (resultGiocatori.next()) {
            giocatoreModel.setIdGiocatore(resultGiocatori.getInt("id"));
            giocatoreModel.setNomeCognome(resultGiocatori.getString("nome_cognome"));
            giocatoreModel.setSquadra(squadraModel);
        }
        statement1.close();
        listaGiocatori.add(GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel));
        return listaGiocatori;
    }


    @Override
    public GiocatoreDTOExtended update(int id, GiocatoreDTOExtended giocatoreDTOExtended) {
        return null;
    }

    @Override
    public GiocatoreDTOExtended delete(int id) {
        return null;
    }
}
