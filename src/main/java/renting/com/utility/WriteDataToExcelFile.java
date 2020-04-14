/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package renting.com.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;


/**
 *
 * @author kamal.raimi
 */
@Component
public class WriteDataToExcelFile {
    
    
    public  static void updateHeaderFile(String fileName, String[] headerTab) throws Exception{
       
        try{
            //Create the input stream from the xlsx/xls file
            FileInputStream fis = new FileInputStream(fileName);
            Workbook workbook = null;
            
            if(fileName.endsWith("xlsx")){
               // workbook = new XSSFWorkbook(fis); mise en commentaire par mory
            }else if(fileName.endsWith("xls")){
                workbook = new HSSFWorkbook(fis);
            }else{
                throw new Exception("invalid file name, should be xls or xlsx");
            }
            //Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);            
            Row row = sheet.getRow(0);            
            for(int i=0; i < headerTab.length; i++ ){
                Cell cell = row.getCell(i);
                System.out.println(cell.getStringCellValue().trim());
                cell.setCellValue(headerTab[i]);
            } 
            fis.close(); //close file input stream            
            FileOutputStream output_file =new FileOutputStream(new File(fileName));  //Open FileOutputStream to write updates
            workbook.write(output_file); //write changes
            output_file.close();  //close the stream 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
	
    }
    
    

}
