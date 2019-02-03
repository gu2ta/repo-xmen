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
import ar.com.ml.xmen.persistence.dao.HumanDao;
import ar.com.ml.xmen.service.RecruitmentService;
import ar.com.ml.xmen.utils.NumericUtil;

@RestController
public class RecruitmentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RecruitmentController.class);
	
	@Autowired
	private HumanDao humanDao;
	
	@Autowired
	private RecruitmentService reclutamientoService;
	
	@PostMapping(value = "/mutant")
	public ResponseEntity<String> saveMutant(@RequestBody Dna dna, HttpServletResponse response) {
		
		try {
			// validar si el dna del humano es de un mutante
			boolean esMutante = reclutamientoService.isMutant(dna.getDnaArray());
			Integer swMutante = (esMutante) ? 1 : 0;
			
			// persistir en la BD el objeto humano
			Human humano = new Human(dna, swMutante);
			
			humanDao.addCacheHuman(humano);
			
			// responder
			return (esMutante) ? new ResponseEntity<String>(HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			LOG.error("Error durante el analisis del ADN: " + e);
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@GetMapping(value = "/stats")
	public ResponseEntity<MutantStats> findStats() {
		
		try {
			
			// obtener la cantidad de humanos no mutantes y mutantes de la BD, se realiza con 1 sola transaccion a la 
			// Base de Datos para ser mas performante
			Human humano = humanDao.getCountsMutant();
			
			Long mutantCount = humano.getCountIsMutant();
			Long humanCount = humano.getCountGeneric();
			
			// calcular el ratio (regla de 3 simples, si son 10 en total y 4 son mutantes, 4/10 = 0.4)
			double ratio = (humanCount == 0) ? 0 : (double) mutantCount / humanCount;
			
			// construir el objeto Stats para responder
			MutantStats stats = new MutantStats(mutantCount, humanCount, NumericUtil.round(ratio, 2));
			return ResponseEntity.ok(stats);
			
		} catch (Exception e) {
			LOG.error("Error durante la visualizacion de las estadisticas de Mutantes: " + e);
			return new ResponseEntity<MutantStats>(HttpStatus.FORBIDDEN);
		}
		
	}
	
}
