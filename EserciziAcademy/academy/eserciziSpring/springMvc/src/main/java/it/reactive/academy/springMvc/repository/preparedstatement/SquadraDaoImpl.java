package it.reactive.academy.springMvc.repository.preparedstatement;

import it.reactive.academy.springMvc.configuration.Costanti;
import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraDaoImpl implements SquadraDao {

    private final DatabaseConfig databaseConfig;

    public SquadraDaoImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);

        try (PreparedStatement psInsert = databaseConfig.getCon().prepareStatement(
                "insert into squadra (nome, colori_sociali) values (?, ?)")) {
            psInsert.setString(1, squadraModel.getNome());
            psInsert.setString(2, squadraModel.getColoriSociali());
            psInsert.executeUpdate();

            try (ResultSet rs = psInsert.getGeneratedKeys()) {
                if (rs.next()) {
                    squadraModel.setIdSquadra(rs.getInt(1));
                    squadraModel.setNome(rs.getString(2));
                    squadraModel.setColoriSociali(rs.getString(3));
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

        try (PreparedStatement psSelect = databaseConfig.getCon().prepareStatement("select * from squadra")) {
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

        try (PreparedStatement psSelect = databaseConfig.getCon().prepareStatement(
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
        try (PreparedStatement psSelect = databaseConfig.getCon().prepareStatement(
                "select id from squadra where nome = ?")) {
            psSelect.setString(1, nomeSquadra);
            ResultSet rs = psSelect.executeQuery();

            return rs.next();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement psDeleteSquadraTorneo = databaseConfig.getCon().prepareStatement(
                "delete from squadra_torneo where id_squadra = ?");
             PreparedStatement psDeleteTifoseria = databaseConfig.getCon().prepareStatement(
                     "delete from tifoseria where id_squadra = ?");
             PreparedStatement psDeleteGiocatore = databaseConfig.getCon().prepareStatement(
                     "delete from giocatore where id_squadra = ?");
             PreparedStatement psDeleteSquadra = databaseConfig.getCon().prepareStatement(
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
