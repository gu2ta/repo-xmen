package ar.com.ml.xmen.beans;

import org.apache.commons.lang3.StringUtils;

import ar.com.ml.xmen.enums.DnaEnum;

public abstract class DnaSequence {
	
	private long countMatches;
	
	// Getters & Setters
	public long getCountMatches() {
		return countMatches;
	}
	public void setCountMatches(long countMatches) {
		this.countMatches = countMatches;
	}
	
	public abstract void findSequences(char[][] matrizDna);
	
	public boolean isDnaMutant() {
		
		if (this.countMatches != 0) {
			return true;
		}
		return false;
		
	}
	
	public long countSequence(String fila) {
		long countA = StringUtils.countMatches(fila, DnaEnum.A.getSequence());
		long countC = StringUtils.countMatches(fila, DnaEnum.C.getSequence());
		long countG = StringUtils.countMatches(fila, DnaEnum.G.getSequence());
		long countT = StringUtils.countMatches(fila, DnaEnum.T.getSequence());
		
		return countA + countC + countG + countT;
	}
	
}
