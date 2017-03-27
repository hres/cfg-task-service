package ca.gc.ip346.classification.model;

public enum ContainsAdded {
	sodium          (1),
	sugar           (2),
	fat             (3),
	transfat        (4),
	caffeine        (5),
	freeSugars      (6),
	sugarSubstitute (7);

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
	private ContainsAdded(Integer code) {
		this.code = code;
	}
}
