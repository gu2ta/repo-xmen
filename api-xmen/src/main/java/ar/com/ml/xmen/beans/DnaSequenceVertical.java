package ar.com.ml.xmen.beans;

public class DnaSequenceVertical extends DnaSequence {

	/**
	 * Busca las combinaciones verticales de la matriz.
	 * @param char[][] matrizDna
	 */
	@Override
	public void findSequences(char[][] matrizDna) {
		
		String combinacionVertical = "";
		
		for (int column=0; column < matrizDna.length; column++) {
			for (int row=0; row < matrizDna[column].length; row++) {
				combinacionVertical += matrizDna[row][column];
			}
			
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionVertical));
			
			combinacionVertical = "";
		}
		
	}

	
}
