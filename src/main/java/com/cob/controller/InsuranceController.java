package com.cob.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cob.binding.InsurancePlan;
import com.cob.entity.InsurancePlanEntity;
import com.cob.exporters.InsuranceExcelExporter;
import com.cob.exporters.InsurancePDFExporter;
import com.cob.service.IInsuranceService;
import com.lowagie.text.DocumentException;

@RestController
public class InsuranceController {

	@Autowired
	private IInsuranceService insuranceServiceImpl;

	@GetMapping(path = "/fetchAll/planName")
	public ResponseEntity<List<String>> fetchInsurancePlanName() {
		return new ResponseEntity<List<String>>(insuranceServiceImpl.getUniquePlanNames(), HttpStatus.OK);
	}

	@GetMapping(path = "/fetchAll/planStatus")
	public ResponseEntity<List<String>> fetchInsurancePlanStatus() {
		return new ResponseEntity<List<String>>(insuranceServiceImpl.getUniquePlanStatus(), HttpStatus.OK);
	}

	@PostMapping(path = "/fetch/filtered/plans")
	public ResponseEntity<List<InsurancePlanEntity>> fetchByInsuranceDetails(@RequestBody InsurancePlan insurancePlan) {
		
		List<InsurancePlanEntity> insurancePlanObjectHolder = insuranceServiceImpl.findRecordsInsuranceDetails(insurancePlan);
		
		//System.out.println(insurancePlan2);
		
		return new ResponseEntity<List<InsurancePlanEntity>>(
				insurancePlanObjectHolder, HttpStatus.OK);
	}

	@PostMapping("/insurance/export/pdf")
	public void exportToPDF(HttpServletResponse response, @RequestBody InsurancePlan insurancePlan) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<InsurancePlanEntity> listOfInsurancePlanEntity = insuranceServiceImpl.findRecordsInsuranceDetails(insurancePlan);

		InsurancePDFExporter exporter = new InsurancePDFExporter(listOfInsurancePlanEntity);
		exporter.export(response);
	}

	@PostMapping("/insurance/export/excel")
	public void exportToExcel(HttpServletResponse response, @RequestBody InsurancePlan insurancePlan) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<InsurancePlanEntity> listOfInsurancePlanEntity = insuranceServiceImpl.findRecordsInsuranceDetails(insurancePlan);

		InsuranceExcelExporter excelExporter = new InsuranceExcelExporter(listOfInsurancePlanEntity);

		excelExporter.export(response);
	}
}
