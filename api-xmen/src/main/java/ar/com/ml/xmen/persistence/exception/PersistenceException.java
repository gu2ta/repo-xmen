package ar.com.ml.xmen.persistence.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1783568439051775481L;
	private Integer codError;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Integer codError) {
		super(message);
		this.codError = codError;
	}

	public PersistenceException(String message, Exception e) {
		super(message, e);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCodError() {
		return codError;
	}

	public void setCodError(Integer codError) {
		this.codError = codError;
	}
}