package pe.com.sedmail.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/java//pe/com/sedmail/core/resources/applicationContext.xml" ,
									"file:src/main/java//pe/com/sedmail/core/resources/infrastructure.xml"})
public class UsuarioTest {
		
	@Test
	public void probarMetodo(){
		try {
			System.out.println("Inyecto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
