package ca.gc.ip346.classification.model;

public enum CfgTier {
	ALL     (0),
	ONE     (1),
	TWO     (2),
	THREE   (3),
	FOUR    (4),
	MISSING (9),
	CUSTOM  (99);

	private Integer code;

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	private CfgTier(Integer code) {
		this.code = code;
	}
}
