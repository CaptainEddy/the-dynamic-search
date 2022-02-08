package com.cob.binding;

public class InsurancePlan {
	private int planId;
	private String planName;
	private String planHolderName;
	private String planHolderSsn;
	private String planStatus;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanHolderName() {
		return planHolderName;
	}

	public void setPlanHolderName(String planHolderName) {
		this.planHolderName = planHolderName;
	}

	public String getPlanHolderSsn() {
		return planHolderSsn;
	}

	public void setPlanHolderSsn(String planHolderSsn) {
		this.planHolderSsn = planHolderSsn;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	@Override
	public String toString() {
		return "InsurancePlan [planId=" + planId + ", planName=" + planName + ", planHolderName=" + planHolderName
				+ ", planHolderSsn=" + planHolderSsn + ", planStatus=" + planStatus + "]";
	}

}
