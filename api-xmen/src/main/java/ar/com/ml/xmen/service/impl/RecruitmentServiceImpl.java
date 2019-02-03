package ar.com.ml.xmen.service.impl;

import org.springframework.stereotype.Service;

import ar.com.ml.xmen.beans.DnaSequenceHorizontal;
import ar.com.ml.xmen.beans.DnaSequenceLeftToRight;
import ar.com.ml.xmen.beans.DnaSequenceRightToLeft;
import ar.com.ml.xmen.beans.DnaSequenceVertical;
import ar.com.ml.xmen.enums.DnaEnum;
import ar.com.ml.xmen.service.RecruitmentService;

@Service("reclutamientoService")
public class RecruitmentServiceImpl implements RecruitmentService {

	@Override
	public boolean isMutant(String[] dna) throws Exception {
		
		// validar dna si es valida la estructura
		this.validateStructureAndData(dna);
		
		char[][] matriz = this.convertToMatrixAndValidateStructure(dna);
		
		// validar si es o no mutante el dna, y retornar la validacion
		return this.checkDNA(matriz);
	}
	
	private void validateStructureAndData(String[] dna) throws Exception {
		
		if (dna == null) 
			throw new Exception("ADN invalido, no puede ser nulo.");
		
		long size = dna.length;
		
		if (!(size>0))
			throw new Exception("ADN invalido, no puede estar vacio.");
		
		// validar estructura y datos
		for (int i=0; i<size; i++) {
			
			for(char letraAdn : dna[i].toCharArray()) {
				DnaEnum.validateDnaMutant(String.valueOf(letraAdn));
			}
			
			if (dna[i].length() != size) 
				throw new Exception ("ADN invalido, la estructura debe tener filas y columnas iguales, donde tiene "+ size +" fila/s "
						+ "y " + dna[i].length() + " columna/s.");
		}
	}
	
	/**
	 * Convierte el Array de Strings en una Matriz de Strings.
	 * Valida que la matriz sea cuadrada y que los datos ingresados sean A, T, C, G.
	 * @param dna
	 * @return
	 * @throws PersistenceException 
	 */
	public char[][] convertToMatrixAndValidateStructure(String[] dna) throws Exception {
		
		char[][] matrixDna = new char[dna.length][dna.length];
		
		for (int i=0; i < dna.length; i++) {
			matrixDna[i] = dna[i].toCharArray();
		}
		
		return matrixDna;
	}
	
	private boolean checkDNA(char[][] matriz) {
		
		DnaSequenceHorizontal horizontal = new DnaSequenceHorizontal();
		DnaSequenceVertical vertical = new DnaSequenceVertical();
		DnaSequenceLeftToRight diagonal1 = new DnaSequenceLeftToRight();
		DnaSequenceRightToLeft diagonal2 = new DnaSequenceRightToLeft();
		
		horizontal.findSequences(matriz);
		vertical.findSequences(matriz);
		diagonal1.findSequences(matriz);
		diagonal2.findSequences(matriz);
		
		if (horizontal.isDnaMutant()) return true;
		if (vertical.isDnaMutant()) return true;
		if (diagonal1.isDnaMutant()) return true;
		if (diagonal2.isDnaMutant()) return true;
		
		return false;
	}

}
