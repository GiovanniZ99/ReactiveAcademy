package it.reactive.academy.springMvc.daotest;

import it.reactive.academy.springMvc.utility.Costanti;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(Costanti.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class GiocatoreDaoTestJdbc extends GiocatoreDaoTest{
}
