package api.utilities;

import java.io.*;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int row=xl.getRowCount("Sheet1");
		int col=xl.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[row][col];
		for(int i=1; i<=row; i++)
		{
			for(int j=0; j<col; j++)
			{
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int row=xl.getRowCount("Sheet1");
		
		String apidata[] = new String[row];
		for(int i=1; i<=row; i++)
		{
				apidata[i-1] = xl.getCellData("Sheet1", i, 1);
		}
		
		return apidata;
	}
}
