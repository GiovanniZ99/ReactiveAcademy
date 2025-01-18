package it.reactive.academy.springMvc.repository.entitymanagerquery;

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
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());

        SquadraTorneoEntity squadraTorneo = new SquadraTorneoEntity();
        squadraTorneo.setId(squadraTorneoId);
        squadraTorneo.setTorneo(torneo);
        squadraTorneo.setSquadra(squadra);

        String s = "insert into squadra_torneo (id_squadra, id_torneo) values (?,?)";
        Query query = entityManager.createNativeQuery(s);
        query.setParameter(1, squadra.getIdSquadra());
        query.setParameter(2, torneo.getIdTorneo());
        query.executeUpdate();

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
        Set<SquadraTorneoEntity> squadraTornei = new HashSet<>(entityManager.createQuery(
                        "select st from SquadraTorneoEntity st where st.squadra.idSquadra = :idSquadra", SquadraTorneoEntity.class)
                .setParameter("idSquadra", squadraDTOExtended.getIdSquadra())
                .getResultList());

        Set<TorneoEntity> tornei = new HashSet<>();
        squadraTornei.forEach(elem -> tornei.add(elem.getTorneo()));

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
