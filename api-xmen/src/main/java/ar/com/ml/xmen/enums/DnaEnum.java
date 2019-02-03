package ar.com.ml.xmen.enums;

public enum DnaEnum {

	A("AAAA"), T("TTTT"), C("CCCC"), G("GGGG");

	private String sequence;
	
	DnaEnum(String sequence) {
		this.setSequence(sequence);
	}

	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public static void validateDnaMutant(String dna) throws Exception {
		if(!dna.equals(A.name()) && !dna.equals(T.name()) && !dna.equals(C.name()) && !dna.equals(G.name()))
			throw new Exception("ADN invalido, solo se admite A, T, C y G");
	}
	
}
