package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraDaoImpl implements SquadraDao {

    private final JdbcTemplate jdbcTemplate;

    public SquadraDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        String s =  "insert into squadra (nome, colori_sociali) values (?, ?)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, squadraModel.getNome());
                ps.setString(2, squadraModel.getColoriSociali());
                return ps;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        squadraModel.setIdSquadra((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        SquadraDTOExtended squadraResult = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraModel> listaSquadra = new ArrayList<>();

        ResultSetExtractor<List<SquadraModel>> rse = new ResultSetExtractor<List<SquadraModel>>() {
            @Override
            public List<SquadraModel> extractData(ResultSet rs) throws SQLException, DataAccessException {

                while(rs.next()) {
                    SquadraModel squadraModel = new SquadraModel();
                    squadraModel.setIdSquadra(rs.getInt(1));
                    squadraModel.setNome(rs.getString(2));
                    squadraModel.setColoriSociali(rs.getString(3));
                    listaSquadra.add(squadraModel);
                }
                return listaSquadra;
            }
        };
        jdbcTemplate.query("select * from squadra", rse);
        return listaSquadra.stream()
                .map(SquadraMapper::squadraModelToDtoExtendended)
                .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        ResultSetExtractor<SquadraModel> rse = new ResultSetExtractor<SquadraModel>() {
            @Override
            public SquadraModel extractData(ResultSet rs) throws SQLException, DataAccessException {

                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt("id"));
                    squadraModel.setNome(rs.getString("nome"));
                    squadraModel.setColoriSociali(rs.getString("colori_sociali"));
                }
                return  squadraModel;
            }
        };

       jdbcTemplate.query("select * from squadra where id = "+ idSquadra, rse);
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        String s = "select id from squadra where nome = ?";

        List<Integer> ids = jdbcTemplate.query(s, new Object[]{nomeSquadra},
                (rs, rowNum) -> rs.getInt("id"));

        return !ids.isEmpty();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sqlSquadraTorneo = "delete from squadra_torneo where id_squadra = ?";
        String sqlTifoseria = "delete from tifoseria where id_squadra = ?";
        String sqlGiocatore = "delete from giocatore where id_squadra = ?";
        String sqlSquadra = "delete from squadra where id = ?";

        jdbcTemplate.update(sqlSquadraTorneo, new Object[]{id});
        jdbcTemplate.update(sqlTifoseria, new Object[]{id});
        jdbcTemplate.update(sqlGiocatore, id);
        jdbcTemplate.update(sqlSquadra, id);
    }
}
