package test.automation.restAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	 /**********************************************
		Function Name: getTableArray
		Description: Reading the input values from the Excel file
		**********************************************/	
	public static Object[][] getTableArray(File file, String SheetName) throws Exception {   
	String[][] tabArray = null;
	try { 		 
	FileInputStream fileInptStrm = new FileInputStream(file);
	ExcelWBook = new XSSFWorkbook(fileInptStrm);
	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	int startRow = 1;
	int startCol = 0;
	int ci,cj;
	int totalRows = ExcelWSheet.getLastRowNum();
	int totalCols= ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
	tabArray=new String[totalRows][totalCols];
	ci=0;
	for (int i=startRow;i<=totalRows;i++, ci++) {           	   
	cj=0;
	for (int j=startCol;j<=(totalCols-1);j++, cj++) {
	tabArray[ci][cj]= getCellData(i,j);
	}
	}
	fileInptStrm.close();
	}
	catch (FileNotFoundException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();
	}
	catch (IOException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();
	}
	return(tabArray);
	}
	 /**********************************************
		Function Name: getCellData
		Description: Reading the cell value from the Excel file
		**********************************************/	
	public static String getCellData(int RowNum, int ColNum) throws Exception {
	try {
	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	if(Cell==null) {
	System.out.println("Cell is null");
	}
	CellType dataType = Cell.getCellType();
	switch(dataType) {
	case STRING:{
	return Cell.getStringCellValue();
	}
	case NUMERIC: {
	int inum= (int)Cell.getNumericCellValue();
	return Integer.toString(inum);
	}
	default:
	return "";
	}
	}
	catch (Exception e) {
	System.out.println(e.getMessage());
	throw (e);
	}
	}
	 /**********************************************
		Function Name: setCellData
		Description: Writing the value to the Excel file
		**********************************************/	
	public static void setCellData(File file, String SheetName, String id) throws Exception {
	try {	   
	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	int totalRows = ExcelWSheet.getLastRowNum();
	Row=ExcelWSheet.createRow(totalRows+1);	
	Cell= Row.createCell(0);
	Cell.setCellValue(totalRows+1);
	Cell= Row.createCell(1);
	Cell.setCellValue(id);
	FileOutputStream fileOutptStrm = new FileOutputStream(file);
	ExcelWBook.write(fileOutptStrm);
	fileOutptStrm.flush();
	fileOutptStrm.close();
	}
	catch(FileNotFoundException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();
	}
	catch (IOException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();	
	}
}	
	/**********************************************
	Function Name: removeCell
	Description: This function deleted the records 
	of the created user
	**********************************************/	
	public static void removeCell(File file, String SheetName, int rowNum, int colNum) throws Exception {
	FileOutputStream fileOutptStrm=null;
	try {
	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	Row=ExcelWSheet.getRow(rowNum);
	Cell = Row.getCell(colNum);
    fileOutptStrm = new FileOutputStream(file);
	if(Cell==null) {
	System.out.println("Cell is null");
	}
	else {
	Row.removeCell(Cell);
	ExcelWBook.write(fileOutptStrm);
	fileOutptStrm.flush();
	fileOutptStrm.close();
	}
	}
	catch(FileNotFoundException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();
	fileOutptStrm.close();
	}
	catch (IOException e) {
	System.out.println("Could not read the Excel sheet");
	e.printStackTrace();	
		}
	}
}
