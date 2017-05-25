package ca.gc.ip346.classification.model;

public class PseudoString {
	private String value; /* = "10g"; */
	private Boolean modified = false;

	public PseudoString(String value) {
		this.value = value;
	}

	public PseudoString() {
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
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
