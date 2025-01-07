package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.Costanti;
import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final DatabaseConfig databaseConfig;

    public SquadraTorneoDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        SquadraTorneoModel squadraTorneoModel = new SquadraTorneoModel();
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "insert into squadra_torneo (id_squadra, id_torneo) values(" +
                        idSquadra + "," + idTorneo + ")";
                statement.executeUpdate(s);
                ResultSet rs = statement.executeQuery("select * from squadra_torneo where id_squadra = " +
                        idSquadra + " and id_torneo = " + idTorneo);
                if (rs.next()) {
                    squadraTorneoModel.setIdSquadra(rs.getInt(1));
                    squadraTorneoModel.setIdTorneo(rs.getInt(2));
                }
            }

        return SquadraTorneoMapper.squadraModelToDtoExtended(squadraTorneoModel);
    }

    @Override
    public Set<Integer> readAllTeams(Integer idTorneo) throws SQLException {
        Set<Integer> setIdSquadre = new HashSet<>();
            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select id_squadra from squadra_torneo where id_torneo = " + idTorneo;
                ResultSet rs = statement.executeQuery(s);
                while (rs.next()) {
                    setIdSquadre.add(rs.getInt(1));
                }
            }
        return setIdSquadre;
    }

    // qui avrei fatto in modo di recuperare gli id di squadra_torneo per poi
    // recuperare gli altri campi nel service chiamando i metodi degli altri dao
    // ma l'esercizio richiede di fare una join tra le tabelle
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        Set<TorneoDTOExtended> tornei = new HashSet<>();
        TorneoDTOExtended torneoDTOExtended = new TorneoDTOExtended();
        torneoDTOExtended.setSquadre(new HashSet<>());
        SquadraDTOExtended squadraDTOExtended = new SquadraDTOExtended();
        squadraDTOExtended.setGiocatori(new HashSet<>());

            try (Statement statement = databaseConfig.getCon().createStatement()) {
                String s = "select * from torneo t " +
                        "join squadra_torneo st on t.id = st.id_torneo " +
                        "join squadra s on st.id_squadra = s.id " +
                        "join giocatore g on s.id = g.id_squadra " +
                        "join tifoseria ti on s.id = ti.id_squadra";
                ResultSet rs = statement.executeQuery(s);
                while (rs.next()) {
                    TorneoModel torneoModel = new TorneoModel();
                    SquadraModel squadraModel = new SquadraModel();
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    TifoseriaModel tifoseriaModel = new TifoseriaModel();

                    torneoModel.setIdTorneo(rs.getInt(1));
                    torneoModel.setNomeTorneo(rs.getString(2));

                    squadraModel.setIdSquadra(rs.getInt(3));
                    squadraModel.setNome(rs.getString(6));
                    squadraModel.setColoriSociali(rs.getString(7));

                    tifoseriaModel.setIdTifoseria(rs.getInt(12));
                    tifoseriaModel.setNomeTifoseria(rs.getString(13));

                    giocatoreModel.setIdGiocatore(rs.getInt(8));
                    giocatoreModel.setNomeCognome(rs.getString(9));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt(10));

                    squadraDTOExtended.setIdSquadra(squadraModel.getIdSquadra());
                    squadraDTOExtended.setNome(squadraModel.getNome());
                    squadraDTOExtended.setColoriSociali(squadraModel.getColoriSociali());
                    squadraDTOExtended.getGiocatori().add((GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel)));
                    squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel));

                    torneoDTOExtended.setIdTorneo(torneoModel.getIdTorneo());
                    torneoDTOExtended.setNomeTorneo(torneoModel.getNomeTorneo());

                    torneoDTOExtended.getSquadre().add(squadraDTOExtended);
                    
                }
                tornei.add(torneoDTOExtended);
            }
        return tornei;
    }
}
