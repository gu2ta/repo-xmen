package ar.com.ml.xmen.beans;

import ar.com.ml.xmen.utils.NumericUtil;

public class MutantStats {
	
	private Long count_mutant_dna;
	private Long count_human_dna;
	private double ratio;
	
	public MutantStats(Long count_mutant_dna, Long count_human_dna) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.recalculateRatio();
	}
	
	public void recalculateRatio() {
		// calcular el ratio (regla de 3 simples, si son 10 en total y 4 son mutantes, 4/10 = 0.4)
		double ratio = (this.getCount_human_dna() == 0) ? 0 : (double) this.getCount_mutant_dna() / this.getCount_human_dna();
		this.ratio = NumericUtil.round(ratio, 2);
	}
	public Long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(Long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public Long getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(Long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
}
