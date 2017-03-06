package ca.gc.ip346.classification.model;

public enum Added {
	IGNORE(0),
	YES(1),
	NO(2);
	private Integer code;

	/**
	 * Constructor
	 */
	Added(Integer code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
}
