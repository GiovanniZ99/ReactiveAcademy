package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraDaoImpl implements SquadraDao {

    private final PlatformTransactionManager transactionManager;

    public SquadraDaoImpl(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement psInsert = con.prepareStatement(
                "insert into squadra (nome, colori_sociali) values (?, ?)")) {
            psInsert.setString(1, squadraModel.getNome());
            psInsert.setString(2, squadraModel.getColoriSociali());
            psInsert.executeUpdate();

            try (ResultSet rs = psInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt(1));
                }
            }
        }

        SquadraDTOExtended squadraResult = SquadraMapper.squadraModelToDtoExtendended(squadraModel);
        squadraResult.setGiocatori(squadraDTOExtended.getGiocatori());
        return squadraResult;
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraDTOExtended> listaSquadra = new ArrayList<>();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement psSelect = con.prepareStatement("select * from squadra")) {
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                SquadraModel squadraModel = new SquadraModel();
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
                listaSquadra.add(SquadraMapper.squadraModelToDtoExtendended(squadraModel));
            }
        }
        return listaSquadra;
    }

    @Override
    public SquadraDTOExtended findSquadraByOd(Integer idSquadra) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();

        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement psSelect = con.prepareStatement(
                "select id, nome, colori_sociali from squadra where id = ?")) {
            psSelect.setInt(1, idSquadra);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                squadraModel.setIdSquadra(rs.getInt("id"));
                squadraModel.setNome(rs.getString("nome"));
                squadraModel.setColoriSociali(rs.getString("colori_sociali"));
            }
        }

        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement psSelect = con.prepareStatement(
                "select id from squadra where nome = ?")) {
            psSelect.setString(1, nomeSquadra);
            ResultSet rs = psSelect.executeQuery();

            return rs.next();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection con = DataSourceUtils.getConnection(Objects.requireNonNull(((DataSourceTransactionManager) transactionManager).getDataSource()));
        try (PreparedStatement psDeleteSquadraTorneo = con.prepareStatement(
                "delete from squadra_torneo where id_squadra = ?");
             PreparedStatement psDeleteTifoseria = con.prepareStatement(
                     "delete from tifoseria where id_squadra = ?");
             PreparedStatement psDeleteGiocatore = con.prepareStatement(
                     "delete from giocatore where id_squadra = ?");
             PreparedStatement psDeleteSquadra = con.prepareStatement(
                     "delete from squadra where id = ?")) {

            psDeleteSquadraTorneo.setInt(1, id);
            psDeleteSquadraTorneo.executeUpdate();

            psDeleteTifoseria.setInt(1, id);
            psDeleteTifoseria.executeUpdate();

            psDeleteGiocatore.setInt(1, id);
            psDeleteGiocatore.executeUpdate();

            psDeleteSquadra.setInt(1, id);
            psDeleteSquadra.executeUpdate();

        }
    }
}
