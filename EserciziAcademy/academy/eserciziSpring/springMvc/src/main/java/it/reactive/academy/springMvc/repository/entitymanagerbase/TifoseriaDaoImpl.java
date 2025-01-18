package it.reactive.academy.springMvc.repository.entitymanagerbase;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.repository.jparepository.TifoseriaRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TifoseriaDaoImpl implements TifoseriaDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);
        entityManager.persist(tifoseriaEntity);
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TifoseriaEntity tifoseriaEntity = entityManager.createQuery(
                        "select t from TifoseriaEntity t where t.squadra.idSquadra = :idSquadra", TifoseriaEntity.class)
                .setParameter("idSquadra", squadraDTOExtended.getIdSquadra())
                .getSingleResult();

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = entityManager.find(TifoseriaEntity.class, idSquadra);

        if (tifoseriaEntity != null) {
            tifoseriaEntity.setNomeTifoseria(name);
            entityManager.merge(tifoseriaEntity);
            return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
        } else {
            throw new SQLException();
        }
    }
}
