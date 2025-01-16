package it.reactive.academy.springMvc.repository.querypsc;

import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TorneoDaoImpl implements TorneoDao {

    private final JdbcTemplate jdbcTemplate;

    public TorneoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNomeTorneo(nomeTorneo);

        String s = "insert into torneo (nome_torneo) values (?)";
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nomeTorneo);
            return ps;
        };
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        torneoEntity.setIdTorneo((Integer) Objects.requireNonNull(keyHolder.getKeys().get("id")));

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoEntity torneoEntity = new TorneoEntity();

        ResultSetExtractor<TorneoEntity> rse = rs -> {
            if (rs.next()) {
                torneoEntity.setIdTorneo(rs.getInt(1));
                torneoEntity.setNomeTorneo(rs.getString(2));

            }
            return torneoEntity;
        };
        jdbcTemplate.query("select id, nome_torneo from torneo where id = ?", rse, idTorneo);

        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String deleteSquadraTorneoSql = "delete from squadra_torneo where id_torneo = ?";
        jdbcTemplate.update(deleteSquadraTorneoSql, id);

        String deleteTorneoSql = "delete from torneo where id = ?";
        jdbcTemplate.update(deleteTorneoSql, id);
    }
}

