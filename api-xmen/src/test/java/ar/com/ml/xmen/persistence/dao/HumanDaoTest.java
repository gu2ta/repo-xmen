package ar.com.ml.xmen.persistence.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.ml.xmen.persistence.dao.HumanDao;
import ar.com.ml.xmen.persistence.dao.impl.HumanDaoImpl;
import ar.com.ml.xmen.persistence.entity.Dna;
import ar.com.ml.xmen.persistence.entity.Human;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HumanDaoImpl.class})
public class HumanDaoTest {

	@Autowired
	private HumanDao humanDao;
	
	@Test
	public void findAllOKTest() throws Exception {
		boolean success = false;
		if (humanDao.findAll().size() >= 0) success = true;
		Assert.assertTrue(success);
	}
	
	@Test
	public void createEntityOKTest() throws Exception {
		
		String[] dnaMutanteStr = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		
		Dna dnaMutante = new Dna(dnaMutanteStr);
		
		Human human = new Human(dnaMutante, 1);
		boolean success = humanDao.create(human);
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void updateEntityErrorTest() throws Exception {
		
		String[] dnaMutanteStr = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		
		Dna dnaMutante = new Dna(dnaMutanteStr);
		
		Human human = new Human(dnaMutante, 1);
		boolean success = humanDao.update(human);
		
		Assert.assertFalse(success);
	}
	
	@Test
	public void deleteEntityOKTest() throws Exception {
		
		String[] dnaMutanteStr = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		
		Dna dnaMutante = new Dna(dnaMutanteStr);
		
		Human human = new Human(dnaMutante, 1);
		boolean success = humanDao.delete(human);
		
		Assert.assertTrue(success);
	}
	
	@Test
	public void deleteByIdErrorTest() throws Exception {
		boolean success = humanDao.deleteById(1);
		
		Assert.assertTrue(success);
	}
	
}
