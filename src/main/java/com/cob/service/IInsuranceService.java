package com.cob.service;

import java.util.List;

import com.cob.binding.InsurancePlan;
import com.cob.entity.InsurancePlanEntity;

public interface IInsuranceService {

	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatus();

	public List<InsurancePlanEntity> getListOfAllInsurancePlans();

	public List<InsurancePlanEntity> findRecordsInsuranceDetails(InsurancePlan insurancePlan);

}
