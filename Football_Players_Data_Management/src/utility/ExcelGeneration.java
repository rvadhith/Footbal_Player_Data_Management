package utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Players;

public class ExcelGeneration {
	FileOutputStream out;
	public HSSFWorkbook excelGenerate(Players player, List<Players> list) throws IOException {
		try {
			//Creation of workbook
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Players");
			HSSFRow hRow = sheet.createRow(0);
			 
			
			hRow.createCell(0).setCellValue("Id");
			hRow.createCell(1).setCellValue("Name");
			hRow.createCell(2).setCellValue("Age");
			hRow.createCell(3).setCellValue("Club");
			hRow.createCell(4).setCellValue("Position");
			hRow.createCell(5).setCellValue("Overall Stats");
			hRow.createCell(6).setCellValue("Height");
			hRow.createCell(7).setCellValue("Pace");
			hRow.createCell(8).setCellValue("Strength");
			hRow.createCell(9).setCellValue("Value");
			hRow.createCell(10).setCellValue("Contract Time Left");
			
			int i = 0;
			for(Players playerObj: list){
				int j = i + 1;
				HSSFRow hrow = sheet.createRow(j);
				hrow.createCell(0).setCellValue(playerObj.getId());
				hrow.createCell(1).setCellValue(playerObj.getName());
				hrow.createCell(2).setCellValue(playerObj.getAge());
				hrow.createCell(3).setCellValue(playerObj.getClubs().getName());
				hrow.createCell(4).setCellValue(playerObj.getPosition().getPositionName());
				hrow.createCell(5).setCellValue(playerObj.getOverallStats());
				hrow.createCell(6).setCellValue(playerObj.getHeight());
				hrow.createCell(7).setCellValue(playerObj.getPace());
				hrow.createCell(8).setCellValue(playerObj.getStrength());
				hrow.createCell(9).setCellValue(playerObj.getBasePrice());
				hrow.createCell(10).setCellValue(playerObj.getContractTimeLeft());
				i++;
			 }
			
			
			 out = new FileOutputStream("/home/adhithya/ProGrad/temp/file.xls");
			 workbook.write(out);
		
			return workbook;
			}
		catch (Exception e) {
				e.printStackTrace();
			}
		finally {
			out.close();
		}
		return null;
	}
}

