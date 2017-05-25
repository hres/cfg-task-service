package ca.gc.ip346.classification.model;

public class PseudoDouble {
	private Double value; /* = 0.0; */
	private Boolean modified = false;

	public PseudoDouble(Double value) {
		this.value = value;
	}

	public PseudoDouble() {
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
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
