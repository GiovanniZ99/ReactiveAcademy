package it.reactive.academy.springMvc.repository.jparbridgerepository;


import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.repository.jparepository.TorneoRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class TorneoDaoImpl implements TorneoDao {

    private final TorneoRepository torneoRepository;

    public TorneoDaoImpl(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneo = new TorneoEntity();
        torneo.setNomeTorneo(nomeTorneo);
        return TorneoMapper.torneoEntityToDtoExtended(torneoRepository.save(torneo));
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        Optional<TorneoEntity> torneo = torneoRepository.findById(idTorneo);
        return TorneoMapper.torneoEntityToDtoExtended(torneo.orElse(new TorneoEntity()));
    }

    @Override
    public Set<TorneoDTOExtended> findAll() throws SQLException {
        return torneoRepository.findAll().stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        torneoRepository.deleteById(id);
    }
}
