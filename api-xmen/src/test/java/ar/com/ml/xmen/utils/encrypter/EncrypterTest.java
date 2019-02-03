package ar.com.ml.xmen.utils.encrypter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EncrypterTest.class})
public class EncrypterTest {

	@Test
	public void encryptAndDecryptTest() throws Exception {
		
		String clave = "test_password1234";
		String retorno;
		
		retorno = Encrypter.encrypt(clave);
		
		System.out.println("clave encriptada: " + retorno);
		
		retorno = Encrypter.decrypt(retorno);
		
		System.out.println("clave desencriptada: " + retorno);
		
		
		Assert.assertEquals(clave, retorno);
	}
	
}
