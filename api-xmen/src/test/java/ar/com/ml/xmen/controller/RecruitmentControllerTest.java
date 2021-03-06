package ar.com.ml.xmen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import ar.com.ml.xmen.persistence.entity.Dna;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class RecruitmentControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@Test
    public void isMutantTest() throws Exception {
		
		String[] dnaMutanteStr = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"};
		
		Dna dnaMutante = new Dna(dnaMutanteStr);
		testMutantServiceRest(dnaMutante, HttpStatus.OK);
    }
	
	@Test
    public void isNotMutantTest() throws Exception {
		
		String[] dnaMutanteStr = {
				"ATGCAA",
				"CAGTGC",
				"TTGTGT",
				"AGAAGG",
				"TCCCTA",
				"TCACTG"};
		
		Dna dnaMutante = new Dna(dnaMutanteStr);
		testMutantServiceRest(dnaMutante, HttpStatus.FORBIDDEN);
    }
	
	@Test
	public void matrixNonSquareTest() throws Exception {
		
		String[] dnaNoMutanteStr = {
				"GC",
				"GCGA",
				"GCGA",
				"GCGA"};
		
		Dna dnaNoMutante = new Dna(dnaNoMutanteStr);
		testMutantServiceRest(dnaNoMutante, HttpStatus.FORBIDDEN);
	}
	
	@Test
    public void getIsMutantOkTest() throws Exception {

		@SuppressWarnings("unused")
		ResultActions resultAction = this.mockMvc.perform(get("/stats")).andDo(print())
												.andExpect(status().isOk())
												.andDo(document("stats-get"));
    }
	
	private String testMutantServiceRest(Dna dna, HttpStatus code) throws IOException, Exception {
		
		ResultActions resultAction = mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(dna.toStringJson()));
		
		if (HttpStatus.OK == code) {
			resultAction.andExpect(status().isOk());
		} else {
			resultAction.andExpect(status().isForbidden());
		}
		
		// Rest Docs
		resultAction.andDo(document("mutant-post"));

		MvcResult result = resultAction.andReturn();
		return result.getResponse().getContentAsString();
	}
	
}
