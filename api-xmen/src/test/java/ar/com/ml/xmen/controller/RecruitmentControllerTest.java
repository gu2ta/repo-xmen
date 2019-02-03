package ar.com.ml.xmen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
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
import ar.com.ml.xmen.utils.JsonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class RecruitmentControllerTest {

	@Autowired
    private MockMvc mockMvc;

	@Test
    public void isMutantDnaHorizontalOKTest() throws Exception {
		
		String[] dnaMutanteStr = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Dna dnaMutante = new Dna(dnaMutanteStr);
		testMutantServiceRest(dnaMutante, HttpStatus.OK);
    }
	
	@Test
	public void isMutantTrueVerticalTest() throws Exception {
		
		String[] dnaNoMutanteStr = {"ATGCAA", "CAGTGC", "TTATGT", "AGAGGG", "TCCCTA", "TCACTG"};
		Dna dnaNoMutante = new Dna(dnaNoMutanteStr);
		testMutantServiceRest(dnaNoMutante, HttpStatus.FORBIDDEN);
	}
	
	@Test
    public void isMutantForbiddenNoEsCuadradaTest() throws Exception {

		String[] dnaMutanteStr = {"GCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Dna dnaMutante = new Dna(dnaMutanteStr);
		testMutantServiceRest(dnaMutante, HttpStatus.FORBIDDEN);
    }
	
	@Test
    public void getIsMutantOkTest() throws Exception {

		ResultActions resultAction = this.mockMvc.perform(get("/stats")).andDo(print())
												.andExpect(status().isOk())
												.andDo(document("stats-get"));
		
	    System.out.println(resultAction);
    }
	
	private String testMutantServiceRest(Dna dna, HttpStatus code) throws IOException, Exception {
		System.out.println(dna.toString());
		String[] dnaMutanteStr = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		String total = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
		
		JSONObject obj = new JSONObject();
		JSONArray dnaList = new JSONArray();
		dnaList.put("ATGCGA");
		dnaList.put("CAGTGC");

		obj.put("dna", dnaList);
		
		ResultActions resultAction = mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(dna.toString()));
		
		if(HttpStatus.OK == code) {
			resultAction.andExpect(status().isOk());
		}else {
			resultAction.andExpect(status().isForbidden());
		}
		
		// Rest Docs
		resultAction.andDo(document("mutant-post"));

		MvcResult result = resultAction.andReturn();
		return result.getResponse().getContentAsString();
	}
	
}
