package it.reactive.academy.springMvc.repository.query;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.*;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SquadraTorneoDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        squadraTorneoId.setTorneoEntity(torneo);
        squadraTorneoId.setSquadraEntity(squadra);

        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (:idSquadra, :idTorneo)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadra.getIdSquadra());
        params.addValue("idTorneo", torneo.getIdTorneo());
        namedParameterJdbcTemplate.update(s, params);

        return SquadraTorneoMapper.squadraEntityToDtoExtended(squadraTorneoId);
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {

        String s = "select id_squadra from squadra_torneo where id_torneo = :idTorneo";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idTorneo", torneoDTOExtended.getIdTorneo());

        List<SquadraEntity> listaSquadre = namedParameterJdbcTemplate.query(s, params, new RowMapper<SquadraEntity>() {
            @Override
            public SquadraEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                SquadraEntity squadra = new SquadraEntity();
                squadra.setIdSquadra(rs.getInt(1));
                return squadra;
            }
        });
        return listaSquadre.stream().map(SquadraMapper::squadraEntityToDtoExtendended).collect(Collectors.toSet());

    }

    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) {
        String sql = "select id_squadra, id_torneo from squadra_torneo where id_squadra = :idSquadra";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idSquadra", squadraDTOExtended.getIdSquadra());

      return  namedParameterJdbcTemplate.query(sql, params, new RowMapper<TorneoEntity>() {
            @Override
            public TorneoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                TorneoEntity torneo = new TorneoEntity();
                torneo.setIdTorneo(rs.getInt("id_torneo"));
                return torneo;
            }
        }).stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }

    @Deprecated
    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        String s = "select * from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "join giocatore g on s.id = g.id_squadra " +
                "join tifoseria ti on s.id = ti.id_squadra " +
                "order by t.id, s.id";

        Set<TorneoDTOExtended> tornei = new HashSet<>();

        namedParameterJdbcTemplate.query(s,
                new RowMapper<TorneoDTOExtended>() {
                    private TorneoDTOExtended torneoDTOExtended;
                    private SquadraDTOExtended squadraDTOExtended;
                    @Override
                    public TorneoDTOExtended mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TorneoEntity torneoEntity = new TorneoEntity();
                        SquadraEntity squadraEntity = new SquadraEntity();
                        GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
                        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();

                        torneoEntity.setIdTorneo(rs.getInt(1));
                        torneoEntity.setNomeTorneo(rs.getString(2));

                        squadraEntity.setIdSquadra(rs.getInt(3));
                        squadraEntity.setNome(rs.getString(6));
                        squadraEntity.setColoriSociali(rs.getString(7));

                        tifoseriaEntity.setIdTifoseria(rs.getInt(12));
                        tifoseriaEntity.setNomeTifoseria(rs.getString(13));

                        giocatoreEntity.setIdGiocatore(rs.getInt(8));
                        giocatoreEntity.setNomeCognome(rs.getString(9));
                        giocatoreEntity.setNumeroAmmonizioni(rs.getInt(10));

                        if (squadraDTOExtended == null || !squadraDTOExtended.getIdSquadra().equals(squadraEntity.getIdSquadra())) {
                            squadraDTOExtended = new SquadraDTOExtended();
                            squadraDTOExtended.setGiocatori(new HashSet<>());
                        }
                        squadraDTOExtended.setIdSquadra(squadraEntity.getIdSquadra());
                        squadraDTOExtended.setNome(squadraEntity.getNome());
                        squadraDTOExtended.setColoriSociali(squadraEntity.getColoriSociali());
                        squadraDTOExtended.getGiocatori().add(GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity));
                        squadraDTOExtended.setTifoseria(TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity));

                        if (torneoDTOExtended == null || !torneoDTOExtended.getIdTorneo().equals(torneoEntity.getIdTorneo())) {
                            torneoDTOExtended = new TorneoDTOExtended();
                            torneoDTOExtended.setSquadre(new HashSet<>());
                        }

                        torneoDTOExtended.setIdTorneo(torneoEntity.getIdTorneo());
                        torneoDTOExtended.setNomeTorneo(torneoEntity.getNomeTorneo());
                        torneoDTOExtended.getSquadre().add(squadraDTOExtended);

                        tornei.add(torneoDTOExtended);

                        return torneoDTOExtended;
                    }
                });

        return tornei;
    }
}
