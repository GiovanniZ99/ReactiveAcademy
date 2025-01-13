package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.model.TifoseriaModel;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.*;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final PlatformTransactionManager transactionManager;

    public TifoseriaDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = TifoseriaMapper.tifoseriaDtoExtendedToModel(tifoseriaDTOExtended);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "insert into tifoseria (nome_tifoseria, id_squadra) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tifoseriaModel.getNomeTifoseria());
            ps.setInt(2, idSquadra);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt(1));
                }
            }
        }
        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "select id, nome_tifoseria from tifoseria where id_squadra = ?")) {
            ps.setInt(1, squadraModel.getIdSquadra());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tifoseriaModel.setIdTifoseria(rs.getInt("id"));
                    tifoseriaModel.setNomeTifoseria(rs.getString("nome_tifoseria"));
                }
            }
        }

        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

    @Override
    public TifoseriaDTOExtended updateName(String nomeTifoseria, Integer idSquadra) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setSquadra(new SquadraModel());
        tifoseriaModel.getSquadra().setIdSquadra(idSquadra);
        tifoseriaModel.setNomeTifoseria(nomeTifoseria);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement ps = con.prepareStatement(
                "update tifoseria set nome_tifoseria = ? where id_squadra = ?")){
            ps.setString(1, nomeTifoseria);
            ps.setInt(2, idSquadra);
            ps.executeUpdate();
        }
        DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());

        return TifoseriaMapper.tifoseriaModelToDtoExtended(tifoseriaModel);
    }

}
