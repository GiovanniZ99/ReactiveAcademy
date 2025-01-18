package it.reactive.academy.springMvc.repository.entitymanagerquery;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.TifoseriaEntity;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.TifoseriaMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class TifoseriaDaoImpl implements TifoseriaDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);

        Query query = entityManager.createNativeQuery("insert into tifoseria (nome_tifoseria, id_squadra) values (:nomeTifoseria, :id");
        query.setParameter("nomeTifoseria", tifoseriaEntity.getNomeTifoseria());
        query.setParameter("idSquadra", tifoseriaEntity.getSquadra().getIdSquadra());

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Query query = entityManager.createNamedQuery("TifoseriaEntity.findByTeam", TifoseriaEntity.class);
        query.setParameter("idSquadra", squadraDTOExtended.getIdSquadra());
        TifoseriaEntity tifoseriaEntity = (TifoseriaEntity) query.getResultList().get(0);
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        String s = "update TifoseriaEntity t set nomeTifoseria = :nomeTifoseria where t.squadra.id = :idSquadra";
        Query query = entityManager.createQuery(s);
        query.setParameter("nomeTifoseria", name);
        query.setParameter("idSquadra", idSquadra);
        query.executeUpdate();

        TifoseriaEntity tifoseriaEntity = new TifoseriaEntity();
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.setNomeTifoseria(name);
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);

        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }
}
