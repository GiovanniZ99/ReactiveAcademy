package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoJpa;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.repository.jparepository.SquadraRepository;
import it.reactive.academy.springMvc.repository.jparepository.SquadraTorneoRepository;
import it.reactive.academy.springMvc.repository.jparepository.TorneoRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
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

    @Override
    public SquadraTorneoDTOExtended create(Integer idTorneo, Integer idSquadra) throws SQLException {
        Optional<SquadraEntity> squadraOptional = squadraRepository.findById(idSquadra);
        Optional<TorneoEntity> torneoOptional = torneoRepository.findById(idTorneo);
        SquadraTorneoEntity squadraTorneoEntity = new SquadraTorneoEntity();
        SquadraEntity squadra = new SquadraEntity();
        squadra.setIdSquadra(null);
        TorneoEntity torneo = new TorneoEntity();
        torneo.setIdTorneo(null);
        squadraTorneoEntity.setIdSquadra(squadraOptional.orElse(squadra).getIdSquadra());
        squadraTorneoEntity.setIdTorneo(torneoOptional.orElse(torneo).getIdTorneo());
        SquadraTorneoJpa squadraTorneoJpa = new SquadraTorneoJpa(squadraTorneoEntity);

        squadraTorneoRepository.save(squadraTorneoJpa);
        return SquadraTorneoMapper.squadraEntityToDtoExtended(squadraTorneoEntity);
    }

    @Override
    public Set<Integer> readAllTeamsById(Integer idTorneo) throws SQLException {
        Set<Integer> set = squadraTorneoRepository.findAllByIdTorneo(idTorneo);
        return set;
    }

    @Override
    public LinkedHashMap<Integer, Set<Integer>> readAllTornei() throws SQLException {
        return null;
    }

    @Override
    public Set<Integer> readAllTorneoByIdSquadra(Integer idSquadra) throws SQLException {
        return Collections.emptySet();
    }

    @Override
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        return Collections.emptySet();
    }
}
