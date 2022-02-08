package com.cob.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cob.binding.InsurancePlan;
import com.cob.entity.InsurancePlanEntity;
import com.cob.repository.InsuranceRepository;

@Service
public class InsuranceServiceImpl implements IInsuranceService {

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Override
	public List<String> getUniquePlanNames() {
		return insuranceRepository.getInsurancePlanName();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return insuranceRepository.getInsurancePlanStatus();
	}

	@Override
	public List<InsurancePlanEntity> findRecordsInsuranceDetails(InsurancePlan insurancePlan) {

		// final InsurancePlan insurancePlan2 = insurancePlan;

		return insuranceRepository.findAll(new Specification<InsurancePlanEntity>() {
			 
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<InsurancePlanEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				
				System.out.println(insurancePlan);
				
				if (insurancePlan.getPlanName() != null && !(insurancePlan.getPlanName() == "")) {
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.equal(root.get("planName"), insurancePlan.getPlanName())));
					System.out.println("Predicate plan Name");
				}

				if (insurancePlan.getPlanStatus() != null && !(insurancePlan.getPlanStatus() == "")) {
					predicates.add(criteriaBuilder
							.and(criteriaBuilder.equal(root.get("planStatus"), insurancePlan.getPlanStatus())));
					System.out.println("Predicate plan status");
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public List<InsurancePlanEntity> getListOfAllInsurancePlans() {
		return insuranceRepository.findAll(Sort.by("planHolderName").ascending());
	}
}
