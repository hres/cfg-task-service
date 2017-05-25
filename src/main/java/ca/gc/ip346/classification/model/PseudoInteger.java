package ca.gc.ip346.classification.model;

public class PseudoInteger {
	private Integer value = 123;
	private Boolean modified = false;

	public PseudoInteger(Integer value) {
		this.value = value;
	}

	public PseudoInteger() {
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the modified
	 */
	public Boolean getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(Boolean modified) {
		this.modified = modified;
	}
}
