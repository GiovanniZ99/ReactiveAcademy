package it.reactive.academy.springMvc.repository.statement;

import it.reactive.academy.springMvc.configuration.DatabaseConfig;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.model.SquadraModel;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class SquadraDaoImpl implements SquadraDao {

    @Autowired
    private DatabaseConfig databaseConfig;

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) {
        SquadraDTOExtended squadraDTOExtResult = new SquadraDTOExtended();
        SquadraModel squadraModel = SquadraMapper.squadraDtoExtendedToModel(squadraDTOExtended);
        Statement statement;
        try {
            statement = databaseConfig.getCon().createStatement();

            statement.executeUpdate("insert into squadra (nome, colori_sociali) values ('"
                    + squadraModel.getNome() + "', '" + squadraModel.getColoriSociali() + "')");
            databaseConfig.getCon().commit();

            ResultSet rs = statement.executeQuery("Select id, nome, colori_sociali " +
                    "from squadra" +
                    " where nome = '"
                    + squadraModel.getNome()
                    + "' and  colori_sociali =  '" + squadraModel.getColoriSociali() + "'");
            if (rs.next()) {
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public SquadraDTOExtended read(int id) {
        SquadraModel squadraModel = new SquadraModel();

        Statement statement;
        try {
            statement = databaseConfig.getCon().createStatement();
            ResultSet rs = statement.executeQuery("select * from squadra where id =" + id);
            databaseConfig.getCon().commit();

            if (rs.next()) {
                squadraModel.setIdSquadra(rs.getInt(1));
                squadraModel.setNome(rs.getString(2));
                squadraModel.setColoriSociali(rs.getString(3));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraModelToDtoExtendended(squadraModel);
    }

    @Override
    public SquadraDTOExtended update(int id, SquadraDTOExtended squadraDTOExtended) {
        return null;
    }

    @Override
    public SquadraDTOExtended delete(int id) {
        return null;
    }
}
