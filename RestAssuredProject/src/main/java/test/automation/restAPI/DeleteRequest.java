package test.automation.restAPI;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import test.automation.restAPI.Utils;

public class DeleteRequest {
	@Test(dataProvider="Authentication")
	/**********************************************
	Function Name: deleteEmployee
	Description: Deletes the employee created
	**********************************************/
	public void deleteEmployee(String rowNumber, String id ) throws Exception {
	RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1/delete";
	Response response= null;
	try {
	response= RestAssured.given().contentType(ContentType.JSON).delete("id");
	String statusLine= response.getStatusLine();
	System.out.println("The statusLine is"+ statusLine);
	Assert.assertEquals( "HTTP/1.1 200 OK", statusLine, "DELETION_SUCCESS");
	}
	 catch (Exception e) {
	e.printStackTrace();
	 }
	System.out.println("The response is:"+ response.asString());
	System.out.println("The status code is: "+ response.getStatusCode());
	int rowNum1= Integer.parseInt(rowNumber);
	File file= new File("Emp_Details.xlsx");
	Utils.removeCell(file, "DeleteEmp",rowNum1, 0);
	Utils.removeCell(file, "DeleteEmp",rowNum1, 1);
	}
	/**********************************************
	Function Name: Authentication
	Description: Data provider provides the test 
	data for the automation test
	**********************************************/
	@DataProvider
	public Object[][] Authentication() throws Exception {
	File file= new File("Emp_Details.xlsx");
	Object[][] testObjArray= Utils.getTableArray(file, "DeleteEmp");
	return (testObjArray);
	}
}
