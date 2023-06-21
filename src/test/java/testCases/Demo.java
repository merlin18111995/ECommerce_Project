package testCases;

import java.io.IOException;


import utilities.ExcelUtility;

public class Demo {

	public static void main(String[] args) throws IOException {
		//String file=System.getProperty("user.dir")+"//TestData//Opencart_data.xlsx";
		String path=".\\testData\\Opencart_data.xlsx";
		ExcelUtility ul=new ExcelUtility(path);
		
		int rowcount=ul.getRowCount("Sheet1");
		int colcount=ul.getCellCount("Sheet1", rowcount);
	   
		System.out.println(rowcount);
		System.out.println(colcount);
		
		String logindata[][]=new String[rowcount][colcount];
		
		for (int r=1;r<=rowcount;r++) {
			for(int c=0;c<colcount;c++) {		
//			String data=ul.getCellData("Sheet1",r, c);
		//	System.out.println(data);
			logindata[r-1][c]= ul.getCellData("Sheet1",r, c);
			} 
			
		//	System.out.println();
		}
		
		for (int i=0;i<logindata.length;i++) {
			for(int j=0;j<logindata[i].length;j++) {
				System.out.println(logindata[i][j]+"	");	
			}
		}
		
	}

}
