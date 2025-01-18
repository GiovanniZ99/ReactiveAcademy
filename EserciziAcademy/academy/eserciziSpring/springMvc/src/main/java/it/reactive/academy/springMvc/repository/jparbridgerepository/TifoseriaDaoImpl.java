package it.reactive.academy.springMvc.repository.jparbridgerepository;

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

import java.sql.SQLException;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class TifoseriaDaoImpl implements TifoseriaDao {

    private final TifoseriaRepository tifoseriaRepository;

    public TifoseriaDaoImpl(TifoseriaRepository tifoseriaRepository) {
        this.tifoseriaRepository = tifoseriaRepository;
    }

    @Override
    public TifoseriaDTOExtended createWithTeam(TifoseriaDTOExtended tifoseriaDTOExtended, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = TifoseriaMapper.tifoseriaDtoExtendedToEntity(tifoseriaDTOExtended);
        tifoseriaEntity.setSquadra(new SquadraEntity());
        tifoseriaEntity.getSquadra().setIdSquadra(idSquadra);
        return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaRepository.save(tifoseriaEntity));
    }

    @Override
    public TifoseriaDTOExtended readByTeam(SquadraDTOExtended squadraDTOExtended) throws SQLException {
      TifoseriaEntity tifoseriaEntity =  tifoseriaRepository.findBySquadraIdSquadra(squadraDTOExtended.getIdSquadra());
      if(tifoseriaEntity == null){
          tifoseriaEntity = new TifoseriaEntity();
      }
      return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaEntity);
    }

    @Override
    public TifoseriaDTOExtended updateName(String name, Integer idSquadra) throws SQLException {
        TifoseriaEntity tifoseriaEntity = tifoseriaRepository.findBySquadraIdSquadra(idSquadra);

        if (tifoseriaEntity != null) {
            tifoseriaEntity.setNomeTifoseria(name);
            return TifoseriaMapper.tifoseriaEntityToDtoExtended(tifoseriaRepository.save(tifoseriaEntity));
        } else {
            throw new SQLException();
        }
    }
}
