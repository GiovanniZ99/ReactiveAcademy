package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final JdbcTemplate jdbcTemplate;

    public GiocatoreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, giocatoreEntity.getNomeCognome());
                ps.setInt(2, giocatoreEntity.getSquadra().getIdSquadra());
                return ps;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        giocatoreEntity.setIdGiocatore((Integer) keyHolder.getKeys().get("id"));

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatoriModel = giocatoriDTOExtended.stream().map(GiocatoreMapper::giocatoreDtoExtendedToEntity).collect(Collectors.toSet());
        String s = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";

        for (GiocatoreEntity giocatoreEntity : giocatoriModel) {
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, giocatoreEntity.getNomeCognome());
                    ps.setInt(2, giocatoreEntity.getSquadra().getIdSquadra());
                    return ps;
                }
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(psc, keyHolder);
            giocatoreEntity.setIdGiocatore((Integer) keyHolder.getKeys().get("id"));
        }
        return giocatoriModel.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }


    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadraEntity = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        String s = "select * from giocatore where id_squadra = (?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(s);
                ps.setInt(1, squadraEntity.getIdSquadra());
                return ps;
            }
        };
        ResultSetExtractor<Set<GiocatoreEntity>> rse = new ResultSetExtractor<Set<GiocatoreEntity>>() {
            @Override
            public Set<GiocatoreEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Set<GiocatoreEntity> giocatori = new HashSet<>();
                while (rs.next()) {
                    GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
                    giocatoreEntity.setIdGiocatore(rs.getInt(1));
                    giocatoreEntity.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreEntity.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                    giocatoreEntity.setSquadra(squadraEntity);
                    giocatori.add(giocatoreEntity);
                }
                return giocatori;
            }
        };
        return (Objects.requireNonNull(jdbcTemplate.query(psc, rse))
                .stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended))
                .collect(Collectors.toSet());
    }

    public boolean checkByName(String nomeGiocatore) throws SQLException {
        String s = "select id from giocatore where nome_cognome = ?";

        List<Integer> ids = jdbcTemplate.query(s, new Object[]{nomeGiocatore},
                (rs, rowNum) -> rs.getInt("id"));

        return !ids.isEmpty();
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity = new GiocatoreEntity();
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?";
        ResultSetExtractor<GiocatoreEntity> rse = new ResultSetExtractor<GiocatoreEntity>() {
            @Override
            public GiocatoreEntity extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    giocatoreEntity.setIdGiocatore(rs.getInt("id"));
                    giocatoreEntity.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreEntity.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                }
                return giocatoreEntity;
            }
        };

        jdbcTemplate.query(s, rse, id);

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ? ";
        jdbcTemplate.update(s, new Object[]{id});
    }
}

