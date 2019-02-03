package ar.com.ml.xmen.beans;

public class DnaSequenceRightToLeft extends DnaSequence {

	/**
	 * Busca las combinaciones diagonales de derecha a izquierda en la matriz.
	 * @param char[][] matrizDna
	 */
	@Override
	public void findSequences(char[][] matrizDna) {
		
		// Combinacion Diagonal Principal y Superior
		String combinacionDiagonalSuperior = "";
		for (int row=matrizDna.length-1; row > 0; row--) {
			for (int column=0; column < row+1; column++) {
				combinacionDiagonalSuperior += matrizDna[row-column][column];
	    	}
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionDiagonalSuperior));
			
			combinacionDiagonalSuperior = "";
	    }
		
		// Combinacion Diagonal Inferior
		String combinacionDiagonalInferior = "";
		for (int row=1; row < matrizDna.length; row++) {
			for (int column=matrizDna.length-1; column >= row; column--) {
				combinacionDiagonalInferior += matrizDna[row+matrizDna.length-1-column][column];
	    	}
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionDiagonalInferior));
			
			combinacionDiagonalInferior = "";
	    }
		
	}

}
