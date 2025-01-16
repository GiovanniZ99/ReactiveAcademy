package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.repository.jparepository.SquadraRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class SquadraDaoImpl implements SquadraDao {

    private final SquadraRepository squadraRepository;

    public SquadraDaoImpl(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {

        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        squadra = squadraRepository.save(squadra);

        return SquadraMapper.squadraEntityToDtoExtendended(squadra);
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
      return squadraRepository.findAll()
              .stream()
              .map(SquadraMapper::squadraEntityToDtoExtendended)
              .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
      Optional<SquadraEntity> squadraOptional = squadraRepository.findById(idSquadra);
     return SquadraMapper.squadraEntityToDtoExtendended(squadraOptional.orElse(new SquadraEntity()));
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        Optional<SquadraEntity> squadra = squadraRepository.findByNome(nomeSquadra);
        return squadra.isPresent();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        squadraRepository.deleteById(id);
    }
}

