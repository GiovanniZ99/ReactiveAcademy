package it.reactive.academy.springMvc.repository.entitymanagerquery;


import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class TorneoDaoImpl implements TorneoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TorneoDTOExtended create(String nomeTorneo) throws SQLException {
        TorneoEntity torneo = new TorneoEntity();
        torneo.setNomeTorneo(nomeTorneo);

        String s = "insert into torneo (nome_torneo) values (:nomeTorneo) returning id";
        Query query = entityManager.createNativeQuery(s);
        query.setParameter("nomeTorneo", nomeTorneo);

        Object result = query.getSingleResult();

        Integer idTorneo = ((Number) result).intValue();
        torneo.setIdTorneo(idTorneo);

        return TorneoMapper.torneoEntityToDtoExtended(torneo);
    }

    @Override
    public TorneoDTOExtended findById(Integer idTorneo) throws SQLException {
        String s = "select t from TorneoEntity t where t.idTorneo = :idTorneo";
        TorneoEntity torneo = entityManager.createQuery(s, TorneoEntity.class)
                .setParameter("idTorneo", idTorneo)
                .getSingleResult();

        return TorneoMapper.torneoEntityToDtoExtended(torneo);
    }

    @Override
    public Set<TorneoDTOExtended> findAll() throws SQLException {
        return entityManager.createQuery("select t from TorneoEntity t", TorneoEntity.class)
                .getResultList()
                .stream()
                .map(TorneoMapper::torneoEntityToDtoExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        entityManager.createQuery("delete from SquadraTorneoEntity st where st.torneo.idTorneo = :id")
                .setParameter("id", id)
                .executeUpdate();

        entityManager.createQuery("delete from TorneoEntity t where t.idTorneo = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
