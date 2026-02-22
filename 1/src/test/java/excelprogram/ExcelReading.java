package excelprogram;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Excel File -->Workbook-->Sheet-->Rows-->Cells.
public class ExcelReading {

	//@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream("D:\\Downloads_Backup\\Book1.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int totalRows =sheet.getLastRowNum();
        int totalCells =sheet.getRow(1).getLastCellNum();
        System.out.println("number of rows:" +totalRows);
        System.out.println("number of cells:" +totalCells);	
        
        for (int r=0;r<=totalRows;r++ )
        {
        	XSSFRow currentRow =sheet.getRow(r);
        	
        	 if (currentRow == null) {
                 System.out.println(); // empty row
                 continue;
             }
        	for (int c=0;c<totalCells;c++) {
        		XSSFCell cell = currentRow.getCell(c);
        		System.out.print(cell.toString()+"\t");
        	}
        	System.out.println();
        }
			
        workbook.close();
      file.close();

	}

}
