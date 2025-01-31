package it.reactive.academy.springMvc.repository.entitymanagerbase;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoId;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        torneo = entityManager.merge(torneo);

        squadra = entityManager.merge(squadra);

        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());

        SquadraTorneoEntity squadraTorneo = new SquadraTorneoEntity();
        squadraTorneo.setId(squadraTorneoId);
        squadraTorneo.setTorneo(torneo);
        squadraTorneo.setSquadra(squadra);

        entityManager.persist(squadraTorneo);
        return SquadraTorneoMapper.squadraTorneoEntityToDtoExtended(squadraTorneo);
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        Set<SquadraTorneoEntity> squadraTornei = new HashSet<>(entityManager.createQuery(
                        "select st from SquadraTorneoEntity st where st.torneo.idTorneo = :idTorneo", SquadraTorneoEntity.class)
                .setParameter("idTorneo", torneoDTOExtended.getIdTorneo())
                .getResultList());
        Set<SquadraEntity> squadre = new HashSet<>();
        squadraTornei.forEach(elem -> squadre.add(elem.getSquadra()));

        return squadre.stream().map(SquadraMapper::squadraEntityToDtoExtendended).collect(Collectors.toSet());
    }


    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<SquadraTorneoEntity> squadreTorneoEntity = new HashSet<>(entityManager.createQuery(
                        "select st from SquadraTorneoEntity st where st.squadra.idSquadra = :idSquadra", SquadraTorneoEntity.class)
                .setParameter("idSquadra", squadraDTOExtended.getIdSquadra())
                .getResultList());
        Set<TorneoEntity> tornei = new HashSet<>();
        squadreTorneoEntity.forEach(elem -> tornei.add(elem.getTorneo()));

        return tornei.stream()
                .map(TorneoMapper::torneoEntityToDtoExtended)
                .collect(Collectors.toSet());
    }

    @Override
    @Deprecated
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        return Collections.emptySet();
    }
}
