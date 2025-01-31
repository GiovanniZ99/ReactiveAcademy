package it.reactive.academy.springMvc.repository.entitymanagerbase;


import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.exception.TorneoNonTrovatoException;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TorneoDaoImpl implements TorneoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneo = new TorneoEntity();
        torneo.setNomeTorneo(nomeTorneo);
        entityManager.persist(torneo);
        return TorneoMapper.torneoEntityToDtoExtended(torneo);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        TorneoEntity torneoEntity = entityManager.find(TorneoEntity.class, idTorneo);
        if (torneoEntity == null) {
            throw new TorneoNonTrovatoException("Torneo non trovato");
        }
        return TorneoMapper.torneoEntityToDtoExtended(torneoEntity);
    }

    @Override
    public Set<TorneoDTOExtended> findAll() throws SQLException {
        return entityManager.createQuery("select t from TorneoEntity t", TorneoEntity.class)
                .getResultList()
                .stream()
                .map(TorneoMapper::torneoEntityToDtoExtended)
                .collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void delete(Integer id) throws SQLException {
        TorneoEntity torneo = entityManager.find(TorneoEntity.class, id);
        if (torneo != null) {
            entityManager.remove(torneo);
        } else {
            throw new SQLException();
        }
    }
}
