package it.reactive.academy.springMvc.repository.entitymanagerbase;

import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.GiocatoreEntity;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class GiocatoreDaoImpl implements GiocatoreDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        entityManager.persist(giocatoreEntity);

        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatori = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToEntity)
                 .collect(Collectors.toSet());

        for (GiocatoreEntity giocatore : giocatori) {
            entityManager.persist(giocatore);
        }

        return giocatori.stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        Query query = entityManager.createQuery("select g from GiocatoreEntity g where g.squadra.idSquadra = :idSquadra", GiocatoreEntity.class);
        query.setParameter("idSquadra", squadra.getIdSquadra());
        Set<GiocatoreEntity> giocatori = new HashSet<GiocatoreEntity>(query.getResultList());

        return giocatori.stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended)
                .collect(Collectors.toSet());
    }

    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity = entityManager.find(GiocatoreEntity.class, id);

        return GiocatoreMapper.giocatoreEntityToDTOExtended(Optional.ofNullable(giocatoreEntity).orElse(new GiocatoreEntity()));
    }

    @Override
    public boolean checkByName(String input) throws SQLException {
        Query query = entityManager.createQuery("select g from GiocatoreEntity g where g.nomeCognome = :input");
        query.setParameter("input", input);

        return !query.getResultList().isEmpty();
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        GiocatoreEntity giocatoreEntity = entityManager.find(GiocatoreEntity.class, id);

        if (giocatoreEntity != null) {
            giocatoreEntity.setNumeroAmmonizioni(giocatoreEntity.getNumeroAmmonizioni() + 1);
            entityManager.merge(giocatoreEntity);
        }
    }
}
