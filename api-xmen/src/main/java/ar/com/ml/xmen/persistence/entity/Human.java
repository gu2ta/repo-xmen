package ar.com.ml.xmen.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "XMEN_HUMAN")
public class Human implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2974975652333013524L;

	@Id
	@Column(name="ID_HUMANO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Dna dna;
	
	@Column(name="IS_MUTANT")
	private Integer isMutant;
	
	@Column(name="TIME_STAMP")
	private Date timestamp;
	
	@Transient
	private Long countGeneric;
	
	@Transient
	private Long countIsMutant;
	
	public Human() {
		this.countGeneric = 0l;
		this.countIsMutant = 0l;
		this.setTimestamp(new Date());
	}
	
	public Human(Dna dna, Integer isMutant) {
		super();
		this.dna = dna;
		this.isMutant = isMutant;
		this.setTimestamp(new Date());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Dna getDna() {
		return dna;
	}
	public void setDna(Dna dna) {
		this.dna = dna;
	}
	public Integer getIsMutant() {
		return isMutant;
	}
	public void setIsMutant(Integer isMutant) {
		this.isMutant = isMutant;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Long getCountGeneric() {
		return countGeneric;
	}
	public void setCountGeneric(Long countGeneric) {
		this.countGeneric = countGeneric;
	}
	public Long getCountIsMutant() {
		return countIsMutant;
	}
	public void setCountIsMutant(Long countIsMutant) {
		this.countIsMutant = countIsMutant;
	}
	
}
