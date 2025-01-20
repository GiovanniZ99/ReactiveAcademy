package it.reactive.academy.springMvc.repository.entitymanagerquery;

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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class GiocatoreDaoImpl implements GiocatoreDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public GiocatoreDTOExtended create(GiocatoreDTOExtended giocatoreDTOExtended) throws SQLException {
        GiocatoreEntity giocatoreEntity = GiocatoreMapper.giocatoreDtoExtendedToEntity(giocatoreDTOExtended);

        String s = "insert into giocatore (nome_cognome, numero_ammonizioni, id_squadra) values (:nomeCognome, :numeroAmmonizioni, :idSquadra)" +
                "returning id";

        Query query = entityManager.createNativeQuery(s);
        query.setParameter("nomeCognome", giocatoreEntity.getNomeCognome());
        query.setParameter("numeroAmmonizioni", giocatoreEntity.getNumeroAmmonizioni());
        query.setParameter("idSquadra", giocatoreEntity.getSquadra().getIdSquadra());

        Object result = query.getSingleResult();

        Integer idGiocatore = ((Number) result).intValue();
        giocatoreEntity.setIdGiocatore(idGiocatore);


        return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatoreEntity);
    }

    @Override
    public Set<GiocatoreDTOExtended> createAll(Set<GiocatoreDTOExtended> giocatoriDTOExtended) throws SQLException {
        Set<GiocatoreEntity> giocatori = giocatoriDTOExtended.stream()
                .map(GiocatoreMapper::giocatoreDtoExtendedToEntity)
                .collect(Collectors.toSet());

        for (GiocatoreEntity giocatore : giocatori) {
            String sql = "insert into giocatore (nome_cognome, numero_ammonizioni, id_squadra) values (:nomeCognome, :numeroAmmonizioni, :idSquadra)"+
                    "returning id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("nomeCognome", giocatore.getNomeCognome());
            query.setParameter("numeroAmmonizioni", giocatore.getNumeroAmmonizioni());
            query.setParameter("idSquadra", giocatore.getSquadra().getIdSquadra());
            Object result = query.getSingleResult();

            Integer idGiocatore = ((Number) result).intValue();
            giocatore.setIdGiocatore(idGiocatore);
        }

        return giocatori.stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GiocatoreDTOExtended> readAllByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);

        Query query = entityManager.createNamedQuery("Giocatore.findByTeam", GiocatoreEntity.class);
        query.setParameter("idSquadra", squadra.getIdSquadra());
        Set<GiocatoreEntity> giocatori = new HashSet<>(query.getResultList());

        return giocatori.stream()
                .map(GiocatoreMapper::giocatoreEntityToDTOExtended)
                .collect(Collectors.toSet());
    }

    public GiocatoreDTOExtended findGiocatoreById(Integer id) throws SQLException {
        try {
            Query query = entityManager.createQuery("select g from GiocatoreEntity g where g.idGiocatore =:id");
            query.setParameter("id", id);
            GiocatoreEntity giocatore = (GiocatoreEntity) query.getSingleResult();
            return GiocatoreMapper.giocatoreEntityToDTOExtended(giocatore);
        }catch (NoResultException e){
            return new GiocatoreDTOExtended();
        }
    }

    @Override
    public boolean checkByName(String input) throws SQLException {
        Query query = entityManager.createNamedQuery("Giocatore.findByName");
        query.setParameter("input", input);
        return !query.getResultList().isEmpty();
    }

    @Override
    public void updateAmmonizioni(Integer id) throws SQLException {
        entityManager.createQuery("update GiocatoreEntity g set numeroAmmonizioni = numeroAmmonizioni +1 " +
                "where idGiocatore = :id").setParameter("id", id).executeUpdate();
    }
}
