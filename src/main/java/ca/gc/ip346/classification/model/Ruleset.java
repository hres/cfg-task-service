package ca.gc.ip346.classification.model;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

public class Ruleset {
	private FormDataContentDisposition fopZ;
	private InputStream fop;
	private FormDataContentDisposition initZ;
	private InputStream init;
	private FormDataContentDisposition refamtZ;
	private InputStream refamt;
	private FormDataContentDisposition shortcutZ;
	private InputStream shortcut;
	private FormDataContentDisposition thresholdsZ;
	private InputStream thresholds;
	private FormDataContentDisposition tierZ;
	private InputStream tier;

	public FormDataContentDisposition getFopZ() {
		return fopZ;
	}

	@FormDataParam("fop")
	public void setFopZ(FormDataContentDisposition fopZ) {
		this.fopZ = fopZ;
	}

	public InputStream getFop() {
		return fop;
	}

	@FormDataParam("fop")
	public void setFop(InputStream fop) {
		this.fop = fop;
	}

	public FormDataContentDisposition getInitZ() {
		return initZ;
	}

	@FormDataParam("init")
	public void setInitZ(FormDataContentDisposition initZ) {
		this.initZ = initZ;
	}

	public InputStream getInit() {
		return init;
	}

	@FormDataParam("init")
	public void setInit(InputStream init) {
		this.init = init;
	}

	public FormDataContentDisposition getRefamtZ() {
		return refamtZ;
	}

	@FormDataParam("refamt")
	public void setRefamtZ(FormDataContentDisposition refamtZ) {
		this.refamtZ = refamtZ;
	}

	public InputStream getRefamt() {
		return refamt;
	}

	@FormDataParam("refamt")
	public void setRefamt(InputStream refamt) {
		this.refamt = refamt;
	}

	public FormDataContentDisposition getShortcutZ() {
		return shortcutZ;
	}

	@FormDataParam("shortcut")
	public void setShortcutZ(FormDataContentDisposition shortcutZ) {
		this.shortcutZ = shortcutZ;
	}

	public InputStream getShortcut() {
		return shortcut;
	}

	@FormDataParam("shortcut")
	public void setShortcut(InputStream shortcut) {
		this.shortcut = shortcut;
	}

	public FormDataContentDisposition getThresholdsZ() {
		return thresholdsZ;
	}

	@FormDataParam("thresholds")
	public void setThresholdsZ(FormDataContentDisposition thresholdsZ) {
		this.thresholdsZ = thresholdsZ;
	}

	public InputStream getThresholds() {
		return thresholds;
	}

	@FormDataParam("thresholds")
	public void setThresholds(InputStream thresholds) {
		this.thresholds = thresholds;
	}

	public FormDataContentDisposition getTierZ() {
		return tierZ;
	}

	@FormDataParam("tier")
	public void setTierZ(FormDataContentDisposition tierZ) {
		this.tierZ = tierZ;
	}

	public InputStream getTier() {
		return tier;
	}

	@FormDataParam("tier")
	public void setTier(InputStream tier) {
		this.tier = tier;
	}

	public Boolean isFopEmpty() {
		return fopZ        .getFileName().isEmpty();
	}

	public Boolean isInitEmpty() {
		return initZ       .getFileName().isEmpty();
	}

	public Boolean isRefamtEmpty() {
		return refamtZ     .getFileName().isEmpty();
	}

	public Boolean isShortcutEmpty() {
		return shortcutZ   .getFileName().isEmpty();
	}

	public Boolean isThresholdsEmpty() {
		return thresholdsZ .getFileName().isEmpty();
	}

	public Boolean isTierEmpty() {
		return tierZ       .getFileName().isEmpty();
	}
}
