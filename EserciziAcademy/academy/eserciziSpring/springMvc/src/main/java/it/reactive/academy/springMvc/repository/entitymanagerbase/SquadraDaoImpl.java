package it.reactive.academy.springMvc.repository.entitymanagerbase;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class SquadraDaoImpl implements SquadraDao {

    @PersistenceContext
    EntityManager entityManager;

        @Override
        public SquadraDTOExtended create(SquadraDTOExtended squadraDTOExtended) throws SQLException {
            SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

            entityManager.persist(squadra);

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
            SquadraEntity squadraEntity = entityManager.find(SquadraEntity.class, idSquadra);

            return SquadraMapper.squadraEntityToDtoExtendended(squadraEntity != null ? squadraEntity : new SquadraEntity());
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
            SquadraEntity squadraEntity = entityManager.find(SquadraEntity.class, idSquadra);

            if (squadraEntity != null) {
                entityManager.remove(squadraEntity);
            }
        }

}

