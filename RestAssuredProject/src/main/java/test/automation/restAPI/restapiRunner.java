package test.automation.restAPI;

import org.testng.TestNG;
public class restapiRunner 
{
 static TestNG testNg;
	
  public static void main(String[] args) 
   {
		testNg = new TestNG();
		testNg.setTestClasses(new Class[] {CreateRequest.class, DeleteRequest.class});
		testNg.run();

	}

}
