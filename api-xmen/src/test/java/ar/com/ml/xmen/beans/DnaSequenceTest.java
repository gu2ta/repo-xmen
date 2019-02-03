package ar.com.ml.xmen.beans;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DnaSequenceTest.class})
public class DnaSequenceTest {

	@Test
	public void isMutantHorizontalTest() throws Exception {
		
		DnaSequenceHorizontal horizontal = new DnaSequenceHorizontal();
		
		String[] dnaMutante = {
				"CCCCGA",
				"TTTTGC",
				"CGAGCT",
				"CTAAGG",
				"CGCCTA",
				"TCACTG"};
		
		char[][] matrixDna = convertToArrayChar(dnaMutante);
		
		horizontal.findSequences(matrixDna);
		
		Assert.assertNotEquals(0, horizontal.getCountMatches());
	}
	
	@Test
	public void isMutantVerticalTest() throws Exception {
		
		DnaSequenceVertical vertical = new DnaSequenceVertical();
		
		String[] dnaMutante = {
				"CTGCGA",
				"CTGTGC",
				"CTATCT",
				"CTAAGG",
				"CGCCTA",
				"TCACTG"};
		
		char[][] matrixDna = convertToArrayChar(dnaMutante);
		
		vertical.findSequences(matrixDna);
		
		Assert.assertNotEquals(0, vertical.getCountMatches());
	}
	
	@Test
	public void isMutantObliqueLeftToRightTest() throws Exception {
		
		DnaSequenceLeftToRight diagonal1 = new DnaSequenceLeftToRight();
		
		String[] dnaMutante = {
				"CGCCGA",
				"TCTTGT",
				"CTCGAT",
				"CTTCGG",
				"CGCTTA",
				"TCACCG"};
		
		char[][] matrixDna = convertToArrayChar(dnaMutante);
		
		diagonal1.findSequences(matrixDna);
		
		Assert.assertNotEquals(0, diagonal1.getCountMatches());
	}
	
	@Test
	public void isMutantObliqueRightToLeftTest() throws Exception {
		
		DnaSequenceRightToLeft diagonal2 = new DnaSequenceRightToLeft();
		
		String[] dnaMutante = {
				"AGCCGA",
				"ACTTAC",
				"CTCACT",
				"CTACGG",
				"CGCTTA",
				"TGACCG"};
		
		char[][] matrixDna = convertToArrayChar(dnaMutante);
		
		diagonal2.findSequences(matrixDna);
		
		Assert.assertNotEquals(0, diagonal2.getCountMatches());
	}
	
	public char[][] convertToArrayChar(String[] dnaMutante) {
		char[][] matrixDna = new char[dnaMutante.length][dnaMutante.length];
		
		for (int i=0; i < dnaMutante.length; i++) {
			matrixDna[i] = dnaMutante[i].toCharArray();
		}
		
		return matrixDna;
	}
	
}
