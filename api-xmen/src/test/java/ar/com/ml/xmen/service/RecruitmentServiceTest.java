package ar.com.ml.xmen.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.ml.xmen.service.impl.RecruitmentServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RecruitmentServiceImpl.class})
public class RecruitmentServiceTest {

	@Autowired
	private RecruitmentService recruitmentService;

	@Test
	public void isMutantTrueTest() throws Exception {
		
		String[] dnaMutante = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantTrueDnaHorizontalTest() throws Exception {
		
		String[] dnaMutante = {"CCCCGA","TTTTGC","CGAGCT","CTAAGG","CGCCTA","TCACTG"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantTrueDnaVerticalTest() throws Exception {
		
		String[] dnaMutante = {"CTGCGA","CTGTGC","CTATCT","CTAAGG","CGCCTA","TCACTG"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantTrueDnaObliqueLeftToRightTest() throws Exception {
		
		String[] dnaMutante = {"CGCCGA","TCTTGT","CTCGAT","CTTCGG","CGCTTA","TCACCG"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantTrueDnaObliqueRightToLeftTest() throws Exception {
		
		String[] dnaMutante = {"AGCCGA","ACTTAC","CTCACT","CTACGG","CGCTTA","TGACCG"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantTrueTwoOfLine() throws Exception {
		
		String[] dnaMutante = { "AAAAAAAA",
								"CAGTCCTC",
								"TTATGTCC",
								"AGAAGGTT",
								"CGCCTATT",
								"TCAGCGCA",
								"TCACTGCT",
								"TCACTGTC"};
		Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantFalseTest() throws Exception {
		
		String[] dnaNoMutante = {"ATGCTA", "CAGTGC", "TTATGT", "AGAGGG", "TCCCTA", "TCACTG"};
		Assert.assertFalse(recruitmentService.isMutant(dnaNoMutante));
	}
	
	@Test
	public void isMutantFalseOneMatchTest() throws Exception {
		
		String[] dnaMutante = {"TTGCAA","CAGTGC","TTATGT","AGAAGG","ACCCTA","TCACTG"};
		Assert.assertFalse(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test
	public void isMutantFalseObliqueHorizontal() throws Exception {
		
		String[] dnaMutante = { "CAATACAA",
								"CAGTCCTC",
								"TTATGTCC",
								"AGAAGGTT",
								"CGCCTATT",
								"TCAGCGCA",
								"TCACTGCT",
								"TCACTGTC"};
		Assert.assertFalse(recruitmentService.isMutant(dnaMutante));
	}
	
	@Test(expected = Exception.class)
	public void validateExceptionNotSquareMatrixTest() throws Exception {
		
		String[] matrixNotSquare = {"GCGA","CAGC","TTATGT","G","DCCCTA","TCACTG"};
		recruitmentService.isMutant(matrixNotSquare);
	}
	
	@Test(expected = Exception.class)
	public void adnInvalidExceptionTest() throws Exception {
		
		String[] dnaMutanteConJ = {"JJGCGK","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		recruitmentService.isMutant(dnaMutanteConJ);
	}
	
	@Test
	public void isMutantMassiveTest() throws Exception {
		
		String[] dnaMutante = {"AAGCGA","CAATAC","TTGAGT","AGAGAG","CCGCTA","TCACTG"};
	
		for (int i = 0; i < 1000000; i++) {
			Assert.assertTrue(recruitmentService.isMutant(dnaMutante));
		}
	}
	
}
