package it.reactive.academy.springMvc.repository.queryforx;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.*;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraTorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());

        SquadraTorneoEntity squadraTorneoEntity = new SquadraTorneoEntity();
        squadraTorneoEntity.setId(squadraTorneoId);
        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?,?)";

        jdbcTemplate.update(s, new Object[]{squadra.getIdSquadra(), torneo.getIdTorneo()});

        return SquadraTorneoMapper.squadraTorneoEntityToDtoExtended(squadraTorneoEntity);
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        String s = "select id_squadra from squadra_torneo where id_torneo = ?";

        List<SquadraEntity> listaSquadre = jdbcTemplate.query(s, new Object[]{torneoDTOExtended.getIdTorneo()},
                (rs, rowNum) -> {
                    SquadraEntity squadra = new SquadraEntity();
                    squadra.setIdSquadra(rs.getInt("id_squadra"));
                    return squadra;
                });

        return listaSquadre.stream()
                .map(SquadraMapper::squadraEntityToDtoExtendended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        String s = "select id_squadra, id_torneo from squadra_torneo where id_squadra = ?";

        List<TorneoEntity> listaTornei = jdbcTemplate.query(s, new Object[]{squadraDTOExtended.getIdSquadra()}, (rs, rowNum) -> {
            TorneoEntity torneo = new TorneoEntity();
            torneo.setIdTorneo(rs.getInt("id_torneo"));
            return torneo;
        });

        return listaTornei.stream()
                .map(TorneoMapper::torneoEntityToDtoExtended)
                .collect(Collectors.toSet());
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

        List<TorneoEntity> sqlListaResult = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(TorneoEntity.class));

        return sqlListaResult.stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }
}