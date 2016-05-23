package com.jdog.frameworks.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;  

public class WordAndExcelExtractor {

	public static void main(String[] args){   
		  try{   
		   String wordFile = "D:\\program\\workspace\\文档\\城南在线\\11.doc";   
		   String wordText2007 = WordAndExcelExtractor.extractTextFromDOC2007(wordFile);   
		   System.out.println("wordText2007======="+wordText2007);   
		      
//		   InputStream is = new FileInputStream("D:/XXX研发中心技术岗位职位需求.xls");      
//		   String excelText = WordAndExcelExtractor.extractTextFromXLS(is);      
//		   System.out.println("text2003==========" + excelText);   
//		      
//		   String excelFile = "D:/Hello2007.xlsx";      
//		   String excelText2007 = WordAndExcelExtractor.extractTextFromXLS2007(excelFile);   
//		   System.out.println("excelText2007==========" + excelText2007);   
		  
		      
		  }catch(Exception e ){   
		   e.printStackTrace();   
		  }   
		 }   
		    
		 /**  
		  * @Method: extractTextFromDOCX  
		  * @Description: 从word 2003文档中提取纯文本  
		  *  
		  * @param   
		  * @return String  
		  * @throws  
		  */  
		    public static String extractTextFromDOC(InputStream is) throws IOException {   
		        WordExtractor ex = new WordExtractor(is); //is是WORD文件的InputStream    
		  
		        return ex.getText();   
		    }   
		    
		 /**  
		  * @Method: extractTextFromDOCX  
		  * @Description: 从word 2007文档中提取纯文本  
		  *  
		  * @param   
		  * @return String  
		  * @throws  
		  */  
		    public static String extractTextFromDOC2007(String fileName) throws IOException, OpenXML4JException, XmlException  {   
		     OPCPackage opcPackage = POIXMLDocument.openPackage(fileName);   
		     
		     POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage);
		  
		        return ex.getText();   
		    }   
		    
		 /**  
		  * @Method: extractTextFromXLS  
		  * @Description: 从excel 2003文档中提取纯文本  
		  *  
		  * @param   
		  * @return String  
		  * @throws  
		  */  
		    @SuppressWarnings("deprecation")   
		 private static String extractTextFromXLS(InputStream is)   
		        throws IOException {   
		        StringBuffer content  = new StringBuffer();   
		        HSSFWorkbook workbook = new HSSFWorkbook(is); //创建对Excel工作簿文件的引用    
		  
		        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {   
		            if (null != workbook.getSheetAt(numSheets)) {   
		                HSSFSheet aSheet = workbook.getSheetAt(numSheets); //获得一个sheet   
		  
		                for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {   
		                    if (null != aSheet.getRow(rowNumOfSheet)) {   
		                        HSSFRow aRow = aSheet.getRow(rowNumOfSheet); //获得一行   
		  
		                        for (short cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {   
		                            if (null != aRow.getCell(cellNumOfRow)) {   
		                                HSSFCell aCell = aRow.getCell(cellNumOfRow); //获得列值   
		                                                                   
		                                if(aCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){   
		                                 content.append(aCell.getNumericCellValue());   
		                                }else if(aCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){   
		                                 content.append(aCell.getBooleanCellValue());   
		                                }else {   
		                                 content.append(aCell.getStringCellValue());   
		                                }   
		                            }   
		                        }   
		                    }   
		                }   
		            }   
		        }   
		  
		        return content.toString();   
		    }   
		       
		    /**  
		     * @Method: extractTextFromXLS2007  
		     * @Description: 从excel 2007文档中提取纯文本  
		     *  
		     * @param   
		     * @return String  
		     * @throws  
		     */  
		    private static String extractTextFromXLS2007(String fileName) throws Exception{   
		     StringBuffer content = new StringBuffer();   
		        
		     //构造 XSSFWorkbook 对象，strPath 传入文件路径       
		  XSSFWorkbook xwb = new XSSFWorkbook(fileName);   
		     
		  //循环工作表Sheet   
		  for(int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++){   
		   XSSFSheet xSheet = xwb.getSheetAt(numSheet);    
		   if(xSheet == null){   
		    continue;   
		   }   
		      
		   //循环行Row   
		   for(int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++){   
		    XSSFRow xRow = xSheet.getRow(rowNum);   
		    if(xRow == null){   
		     continue;   
		    }   
		       
		    //循环列Cell   
		    for(int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++){   
		     XSSFCell xCell = xRow.getCell(cellNum);   
		     if(xCell == null){   
		      continue;   
		     }   
		        
		     if(xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN){   
		      content.append(xCell.getBooleanCellValue());   
		     }else if(xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){   
		      content.append(xCell.getNumericCellValue());   
		     }else{   
		      content.append(xCell.getStringCellValue());   
		     }   
		    }   
		   }   
		  }   
		     
		  return content.toString();   
		    }   
}
