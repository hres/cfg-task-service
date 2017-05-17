package ca.gc.ip346.classification.model;

public class PseudoBoolean {
	private Boolean value;
	private Boolean modified = false;

	public PseudoBoolean(Boolean value) {
		this.value = value;
	}

	public PseudoBoolean() {
	}

	/**
	 * @return the value
	 */
	public Boolean getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Boolean value) {
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
