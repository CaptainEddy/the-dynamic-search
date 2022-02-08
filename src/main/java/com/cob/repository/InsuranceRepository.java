package com.cob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cob.entity.InsurancePlanEntity;

public interface InsuranceRepository
		extends JpaRepository<InsurancePlanEntity, Integer>, JpaSpecificationExecutor<InsurancePlanEntity> {
	
	@Query(value = "SELECT DISTINCT planName FROM InsurancePlanEntity")
	public List<String> getInsurancePlanName();
	
	@Query(value = "SELECT DISTINCT planStatus FROM InsurancePlanEntity")
	public List<String> getInsurancePlanStatus();
}
