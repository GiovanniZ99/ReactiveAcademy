package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.GiocatoreModel;
import it.reactive.academy.springMvc.model.SquadraModel;
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
        GiocatoreModel giocatoreModel = GiocatoreMapper.giocatoreDtoExtendedToModel(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, giocatoreModel.getNomeCognome());
                ps.setInt(2, giocatoreModel.getSquadra().getIdSquadra());
                return ps;
            }
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        giocatoreModel.setIdGiocatore((Integer) keyHolder.getKeys().get("id"));

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {

        Set<GiocatoreDTOExtended> giocatoriResult = new HashSet<>();

        for (GiocatoreDTOExtended giocatoreDTOExtended : giocatoriDTOExtended) {
            GiocatoreDTOExtended giocatoreDTO = create(giocatoreDTOExtended);
            giocatoriResult.add(giocatoreDTO);
        }
        return giocatoriResult;
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s = "select * from giocatore where id_squadra = (?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(s);
                ps.setInt(1, squadraModel.getIdSquadra());
                return ps;
            }
        };
        ResultSetExtractor<Set<GiocatoreModel>> rse = new ResultSetExtractor<Set<GiocatoreModel>>() {
            @Override
            public Set<GiocatoreModel> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Set<GiocatoreModel> giocatori = new HashSet<>();
                while (rs.next()) {
                    GiocatoreModel giocatoreModel = new GiocatoreModel();
                    giocatoreModel.setIdGiocatore(rs.getInt(1));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                    giocatoreModel.setSquadra(squadraModel);
                    giocatori.add(giocatoreModel);
                }
                return giocatori;
            }
        };
        return (Objects.requireNonNull(jdbcTemplate.query(psc, rse))
                .stream()
                .map(GiocatoreMapper::giocatoreModelToDTOExtended))
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
        GiocatoreModel giocatoreModel = new GiocatoreModel();
        String s = "select id, nome_cognome, numero_ammonizioni from giocatore where id = ?";
        ResultSetExtractor<GiocatoreModel> rse = new ResultSetExtractor<GiocatoreModel>() {
            @Override
            public GiocatoreModel extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    giocatoreModel.setIdGiocatore(rs.getInt("id"));
                    giocatoreModel.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                }
                return giocatoreModel;
            }
        };

        jdbcTemplate.query(s, rse, id);

        return GiocatoreMapper.giocatoreModelToDTOExtended(giocatoreModel);
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        String s = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ? ";
        jdbcTemplate.update(s, new Object[]{id});
    }
}

