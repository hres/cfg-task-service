package ca.gc.ip346.classification.model;

public enum CfgType {
	BOTH(0),
	FOOD(1),
	RECIPE(2);

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
	private CfgType(Integer code) {
		this.code = code;
	}
}
