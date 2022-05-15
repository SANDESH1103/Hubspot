package com.bank.Utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.naming.ldap.SortControl;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xlutils {
	static File srcFile;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlfile, String sheetname) throws IOException {
		/*
		 * srcFile=new File(System.getProperty("user.dir")+
		 * "/Testdata/fb.xlsx");
		 */
		try {
			fi = new FileInputStream(xlfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			wb = new XSSFWorkbook(fi);
		} catch (IOException e) {

			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetname);
		// int rowCount=sheet.getLastRowNum();
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();

		return rowCount;
	}

	public static int getCellCount(String xlfile, String sheetname, int rownum)
			throws IOException {

		try {
			fi = new FileInputStream(xlfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fi);
		} catch (IOException e) {

			e.printStackTrace();
		}
		sheet = wb.getSheet(sheetname);
		// int rowCount=sheet.getLastRowNum();
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public static String getData(String xlfile, String sheetname, int rownum,
			int cellnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
	
		// here we are not fetching the value of cell because if we do that it may give exception for value other than that type 
		// so we directly give this cell to DataFormatter class and converted into String type
		String data;
		try {
			DataFormatter dataFormatter = new DataFormatter();
			String dataCell = dataFormatter.formatCellValue(cell);
			return dataCell;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setData(String xlfile, String sheetname, int rownum,
			int cellnum, String data) throws IOException {

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.createCell(cellnum);
		//here we know that value given as input is string so we take setCellValue of String type
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}
}
