package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.repository.jparepository.TifoseriaRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final TifoseriaRepository tifoseriaRepository;

    public TifoseriaDaoImpl(TifoseriaRepository tifoseriaRepository) {
        this.tifoseriaRepository = tifoseriaRepository;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        return null;
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        return null;
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        return null;
    }
}
