package ar.com.ml.xmen.persistence.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Dna implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4902882163009714140L;
	
	@Column(name="DNA")
	private String dna;
	
	@Transient
	private String[] dnaArray;
	
	public Dna() {}
	
	@JsonCreator
    public Dna(@JsonProperty("dna") String[] dna) {
        this.dna = Arrays.toString(dna);
        this.dnaArray = dna;
    }

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String[] getDnaArray() {
		return dnaArray;
	}

	public void setDnaArray(String[] dnaArray) {
		this.dnaArray = dnaArray;
	}
	
	public String toStringJson() {
		String retorno = "{\"dna\":";
		String dnaRetorno = dna;
		dnaRetorno = dnaRetorno.replace(" ", "");
		dnaRetorno = dnaRetorno.replace("[", "[\"");
		dnaRetorno = dnaRetorno.replace(",", "\",\"");
		dnaRetorno = dnaRetorno.replace("]", "\"]");
		retorno += dnaRetorno + "}";
		return retorno;
//		return "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
	}
	
}
