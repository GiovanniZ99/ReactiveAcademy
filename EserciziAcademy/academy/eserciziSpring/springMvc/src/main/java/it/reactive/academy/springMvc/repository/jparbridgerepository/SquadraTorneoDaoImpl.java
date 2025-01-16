package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.repository.jparepository.SquadraRepository;
import it.reactive.academy.springMvc.repository.jparepository.SquadraTorneoRepository;
import it.reactive.academy.springMvc.repository.jparepository.TorneoRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final SquadraTorneoRepository squadraTorneoRepository;
    private final SquadraRepository squadraRepository;
    private final TorneoRepository torneoRepository;

    public SquadraTorneoDaoImpl(SquadraTorneoRepository squadraTorneoRepository, SquadraRepository squadraRepository, TorneoRepository torneoRepository) {
        this.squadraTorneoRepository = squadraTorneoRepository;
        this.squadraRepository = squadraRepository;
        this.torneoRepository = torneoRepository;
    }

//    @Override
//    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
//        Set<Integer> set = squadraTorneoRepository.findAllByIdTorneo(idTorneo);
//        return set;
//    }
//
//    @Override
//    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException {
//        return null;
//    }
//
//    @Override
//    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException {
//        return Collections.emptySet();
//    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        return null;
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        return Collections.emptySet();
    }



    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        return Collections.emptySet();
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        return Collections.emptySet();
    }
}
