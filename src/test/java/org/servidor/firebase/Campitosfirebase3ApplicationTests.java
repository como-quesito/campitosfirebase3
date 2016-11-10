package org.servidor.firebase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class Campitosfirebase3ApplicationTests {
@Autowired
RepositorioClave  repoClave;
	@Test
	public void contextLoads() {
	}

	@Test
	public void probarEntidadMensaje(){

		Clave clave=repoClave.findOne(6L);
		assertThat(clave.getNombre()).isEqualTo("juan");
	}

}
