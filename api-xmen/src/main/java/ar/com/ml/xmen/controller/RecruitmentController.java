package ar.com.ml.xmen.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ml.xmen.beans.MutantStats;
import ar.com.ml.xmen.persistence.entity.Dna;
import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.persistence.service.NewHumanCacheService;
import ar.com.ml.xmen.persistence.service.StatsCacheService;
import ar.com.ml.xmen.service.RecruitmentService;

@RestController
public class RecruitmentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentController.class);
	
	@Autowired
	private RecruitmentService recruitmentService;
	
	@Autowired
	private NewHumanCacheService newHumanCacheService;
	
	@Autowired
	private StatsCacheService statsCacheService;
	
	@PostMapping(value = "/mutant")
	public ResponseEntity<String> saveMutant(@RequestBody Dna dna, HttpServletResponse response) {
		
		try {
			// validar si el dna del humano es de un mutante
			boolean isMutant = recruitmentService.isMutant(dna.getDnaArray());
			Integer swMutant = (isMutant) ? 1 : 0;
			
			// persistir en la BD el objeto humano
			Human humano = new Human(dna, swMutant);
			
			newHumanCacheService.addCache(humano);
			
			// responder
			return (isMutant) ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			LOG.error("Error durante el analisis del ADN: " + e);
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@GetMapping(value = "/stats")
	public ResponseEntity<MutantStats> findStats() {
		
		try {
			// obtener la estadistica desde Cache, donde previamente esta cargado con la informacion al iniciar la API, y seran actualizadas las 
			// cantidades con cada invocacion POST del recurso mutant
			MutantStats elementStats = statsCacheService.findCache();
			
			return ResponseEntity.ok(elementStats);
			
		} catch (Exception e) {
			LOG.error("Error durante la visualizacion de las estadisticas de Mutantes: " + e);
			return new ResponseEntity<MutantStats>(HttpStatus.FORBIDDEN);
		}
		
	}
	
}
