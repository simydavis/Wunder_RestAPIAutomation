package test.automation.restAPI;
import java.io.File;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class CreateRequest {
	@SuppressWarnings("unchecked")
	@Test(dataProvider="Authentication")
	/**********************************************
	Function Name: createEmployee
	Description: Creates the employee using the inputs
	 received from the Excel file	
	**********************************************/
	public void createEmployee(String empName, String empSalary, String emPage) throws Exception {
	RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
	RequestSpecification request= RestAssured.given();
	JSONObject requestParams= new JSONObject();
	requestParams.put("name", empName);
	requestParams.put("salary", empSalary);
	requestParams.put("age", emPage);
	request.header("Content-Type", "application/json");
	request.body(requestParams.toJSONString());
	Response resp= request.post("/create");
	int statusCode= resp.getStatusCode();
	System.out.println("The status of statuscode is"+ statusCode);
	String statusLine= resp.getStatusLine();
	System.out.println("The statusLine is"+ statusLine);
	String respBody= resp.getBody().asString();
	System.out.println("The resp body contains:"+ respBody );
	Assert.assertEquals( "HTTP/1.1 200 OK", statusLine, "OPERATION_SUCCESS");
	JsonPath jpath= resp.jsonPath();
	String id= jpath.getString("id");
	System.out.println("The emp id is:"+ id);
	File file= new File("Emp_Details.xlsx");
	Utils.setCellData(file,"DeleteEmp", id);
	}
	/**********************************************
	Function Name: Authentication
	Description: Data provider provides the test 
	data for the automation test
	**********************************************/	
	@DataProvider
	public Object[][] Authentication() throws Exception {
	File file= new File("Emp_Details.xlsx");
	Object[][] testObjArray = Utils.getTableArray(file,"CreateEmp");
	return (testObjArray);
	}
	
	
	}

