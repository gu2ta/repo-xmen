package ar.com.ml.xmen.beans;

public class DnaSequenceLeftToRight extends DnaSequence {

	/**
	 * Busca las combinaciones diagonales de izquierda a derecha de la matriz.
	 * @param char[][] matrizDna
	 */
	@Override
	public void findSequences(char[][] matrizDna) {
		
		// Combinacion Diagonal Principal y Superior
		String combinacionDiagonalSuperior = "";
		for (int column=0; column < matrizDna.length; column++) {
			for (int row=0; row < matrizDna.length-column; row++) {
	    		combinacionDiagonalSuperior += matrizDna[row][column+row];
	    	}
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionDiagonalSuperior));
			
			combinacionDiagonalSuperior = "";
	    }
		
		// Combinacion Diagonal Inferior
		String combinacionDiagonalInferior = "";
		for (int row=1; row < matrizDna.length-1; row++) {
			for (int column=0; column < matrizDna.length-row; column++) {
				combinacionDiagonalInferior += matrizDna[row+column][column];
	    	}
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionDiagonalInferior));
			
			combinacionDiagonalInferior = "";
	    }
		
	}
	
}
