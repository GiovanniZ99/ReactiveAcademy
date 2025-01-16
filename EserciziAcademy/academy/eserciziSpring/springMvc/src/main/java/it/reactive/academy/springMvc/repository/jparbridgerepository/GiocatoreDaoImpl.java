package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.repository.jparepository.GiocatoreRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class GiocatoreDaoImpl implements GiocatoreDao {

    private final GiocatoreRepository giocatoreRepository;

    public GiocatoreDaoImpl(GiocatoreRepository giocatoreRepository) {
        this.giocatoreRepository = giocatoreRepository;
    }

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);
        giocatoreEntity = giocatoreRepository.save(giocatoreEntity);

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatori = giocatoriDTOExtended.stream().map(GiocatoreMapper::giocatoreDtoExtendedToEntity).collect(Collectors.toSet());
        giocatori = new HashSet<>(giocatoreRepository.saveAll(giocatori));

        return giocatori.stream().map(GiocatoreMapper::giocatoreEntityToDTOExtended).collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        Set<Optional<GiocatoreEntity>> giocatoriOptional = giocatoreRepository.findAllBySquadra(squadra);
        return giocatoriOptional.stream()
                .map(elem -> GiocatoreMapper
                        .giocatoreEntityToDTOExtended(elem.orElse(new GiocatoreEntity())))
                .collect(Collectors.toSet());
    }

    @Override
    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        Optional<GiocatoreEntity> giocatoreOptional = giocatoreRepository.findById(id);
        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreOptional.orElse(new GiocatoreEntity()));
    }

    @Override
    public boolean checkByName(String input) throws SQLException {
        Optional<GiocatoreEntity> giocatoreOptional = giocatoreRepository.findByNomeCognome(input);
        return giocatoreOptional.isPresent();
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        Optional<GiocatoreEntity> giocatoreOptional = giocatoreRepository.findById(id);
        giocatoreOptional.orElse(new GiocatoreEntity()).setNumeroAmmonizioni(+1);
    }
}
