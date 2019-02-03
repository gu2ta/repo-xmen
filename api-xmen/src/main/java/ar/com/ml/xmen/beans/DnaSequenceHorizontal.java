package ar.com.ml.xmen.beans;

public class DnaSequenceHorizontal extends DnaSequence {

	/**
	 * Busca las combinaciones horizontales de la matriz.
	 * @param char[][] matrizDna
	 */
	@Override
	public void findSequences(char[][] matrizDna) {
		
		String combinacionHorizontal = "";
		
		for (int row=0; row < matrizDna.length; row++) {
			for (int column=0; column<matrizDna[row].length; column++) {
				combinacionHorizontal += matrizDna[row][column];
			}
			
			this.setCountMatches(this.getCountMatches() + this.countSequence(combinacionHorizontal));
			
			combinacionHorizontal = "";
		}
		
	}

	
}
