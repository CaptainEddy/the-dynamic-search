package com.cob.exporters;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import com.cob.entity.InsurancePlanEntity;

public class InsuranceExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<InsurancePlanEntity> listOfInsurancePlans;

	@Autowired
	public InsuranceExcelExporter(List<InsurancePlanEntity> listOfInsurancePlans) {
		this.listOfInsurancePlans = listOfInsurancePlans;
		workbook = new XSSFWorkbook();
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Insurance Plans Details");

		XSSFRow row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Plan ID", style);
		createCell(row, 1, "Plan Holder Name", style);
		createCell(row, 2, "Plan Holder SSN", style);
		createCell(row, 3, "Plan Name", style);
		createCell(row, 4, "Plan Status", style);
	}
	
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (InsurancePlanEntity insurancePlanEntity : listOfInsurancePlans) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, insurancePlanEntity.getPlanId(), style);
            createCell(row, columnCount++, insurancePlanEntity.getPlanHolderName(), style);
            createCell(row, columnCount++, insurancePlanEntity.getPlanHolderSsn(), style);
            createCell(row, columnCount++, insurancePlanEntity.getPlanName(), style);
            createCell(row, columnCount++, insurancePlanEntity.getPlanStatus(), style);
             
        }
    }
	
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();    
    }
}
