package ca.gc.ip346.classification.model;

public enum RecipeRolled {
	IGNORE(0),
	UP(1),
	DOWN(2),
	BOTH(3);

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
	private RecipeRolled(Integer code) {
		this.code = code;
	}
}
