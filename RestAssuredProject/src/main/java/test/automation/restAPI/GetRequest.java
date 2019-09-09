package test.automation.restAPI;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	@Test(dataProvider="Authentication")
	/**********************************************
	Function Name: getEmployee
	Description: Gets the employee details
	**********************************************/
	public void getEmployee(String rowNumber, String id ) throws Exception {
	RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1/employee";
	RequestSpecification httpRequest = RestAssured.given();
	Response response = httpRequest.request(Method.GET, "id");
	String responseBody = response.getBody().asString();
	System.out.println("Response Body is =>  " + responseBody);
	String statusLine= response.getStatusLine();
	System.out.println("The statusLine is"+ statusLine);
	Assert.assertEquals( "HTTP/1.1 200 OK", statusLine, "OPERATION_SUCCESS");
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
