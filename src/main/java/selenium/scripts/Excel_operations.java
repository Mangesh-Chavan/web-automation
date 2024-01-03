package selenium.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_operations {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String path;
	private static XSSFCellStyle style;

	public Excel_operations(String path) {
		Excel_operations.path = path;
	}

	public static void setCellData(String sheetname, int rownum, int colnum, String data) {
		File f = new File(path);
		try {
			if (!f.exists()) {
				workbook = new XSSFWorkbook();
				fo = new FileOutputStream(f);
				workbook.write(fo);

			}
			fi = new FileInputStream(f);
			workbook = new XSSFWorkbook(fi);
		} catch (IOException e) {
		}

		if (workbook.getSheetIndex(sheetname) == -1)
			workbook.createSheet(sheetname);

		sheet = workbook.getSheet(sheetname);

		if (sheet.getRow(rownum) == null)
			sheet.createRow(rownum);

		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		try {
			fo = new FileOutputStream(f);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		} catch (IOException e) {
		}

	}

	public static void fillGreenColor(String sheetname, int rownum, int colnum) {
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetname);

			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);

			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			cell.setCellStyle(style);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fillREDColor(String sheetname, int rownum, int colnum) {
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetname);

			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);

			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			cell.setCellStyle(style);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getlastrow() {
		int rowcount = sheet.getLastRowNum();
		return rowcount;
	}

}
