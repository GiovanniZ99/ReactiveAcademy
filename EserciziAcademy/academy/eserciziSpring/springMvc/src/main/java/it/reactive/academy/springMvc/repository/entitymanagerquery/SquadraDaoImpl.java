package it.reactive.academy.springMvc.repository.entitymanagerquery;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class SquadraDaoImpl implements SquadraDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        Query query = entityManager.createNativeQuery("insert into squadra (nome, colori_sociali) values (:nome, :coloriSociali)" +
                "returning id");
        query.setParameter("nome", squadra.getNome());
        query.setParameter("coloriSociali", squadra.getColoriSociali());

        Object result = query.getSingleResult();

        Integer idSquadra = ((Number) result).intValue();
        squadra.setIdSquadra(idSquadra);

        return SquadraMapper.squadraEntityToDtoExtendended(squadra);
    }

    @Override
    public List<SquadraDTOExtended> readAll() throws SQLException {
        List<SquadraEntity> listaSquadre = entityManager.createQuery("select s from SquadraEntity s", SquadraEntity.class)
                .getResultList();

        return listaSquadre.stream()
                .map(SquadraMapper::squadraEntityToDtoExtendended)
                .collect(Collectors.toList());
    }

    @Override
    public SquadraDTOExtended findSquadraById(Integer idSquadra) throws SQLException {
        try {
            String s = "select s from SquadraEntity s where s.id = :idSquadra";
            SquadraEntity squadraEntity = entityManager.createQuery(s, SquadraEntity.class)
                    .setParameter("idSquadra", idSquadra).getSingleResult();
            return SquadraMapper.squadraEntityToDtoExtendended(squadraEntity);
        } catch (javax.persistence.NoResultException e) {
            return new SquadraDTOExtended();
        }
    }

    @Override
    public boolean checkSquadraByName(String nomeSquadra) throws SQLException {
        SquadraEntity squadra = entityManager.createQuery("select s from SquadraEntity s where s.nome = :nomeSquadra", SquadraEntity.class)
                .setParameter("nomeSquadra", nomeSquadra)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        return squadra != null;
    }

    @Override
    public void delete(Integer idSquadra) throws SQLException {

        String deleteSquadraTorneo = "DELETE FROM squadra_torneo WHERE id_squadra = :idSquadra";
        entityManager.createNativeQuery(deleteSquadraTorneo)
                .setParameter("idSquadra", idSquadra)
                .executeUpdate();

        String deleteGiocatori = "delete from giocatore where id_squadra = :idSquadra";
        entityManager.createNativeQuery(deleteGiocatori)
                .setParameter("idSquadra", idSquadra)
                .executeUpdate();

        String deleteTifoseria = "delete from tifoseria where id_squadra = :idSquadra";
        entityManager.createNativeQuery(deleteTifoseria)
                .setParameter("idSquadra", idSquadra)
                .executeUpdate();

        String deleteSquadra = "delete from squadra where id = :idSquadra";
        entityManager.createNativeQuery(deleteSquadra)
                .setParameter("idSquadra", idSquadra)
                .executeUpdate();
    }
}

