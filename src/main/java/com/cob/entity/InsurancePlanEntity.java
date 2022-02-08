package com.cob.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSURNACE_PLAN")
public class InsurancePlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAN_ID")
	private int planId;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_HOLDER_NAME")
	private String planHolderName;

	@Column(name = "PLAN_HOLDER_SSN")
	private String planHolderSsn;

	@Column(name = "PLAN_STATUS")
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

}
