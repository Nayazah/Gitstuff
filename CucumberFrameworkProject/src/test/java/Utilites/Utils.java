package Utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

public class Utils {

	
	public static RequestSpecification req;
	public  RequestSpecification RequestSpec() throws IOException {
		
		
		if (req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
	 RequestSpecification req =new RequestSpecBuilder().setBaseUri(Globalvalue("baseurl")).addQueryParam("key", "qaclick123")
				
	.addFilter(RequestLoggingFilter.logRequestTo(log))			
	.addFilter(ResponseLoggingFilter.logResponseTo(log))
				 .setContentType(ContentType.JSON).build();
	 			return req;
		}
		return req;
	}
	
	public static String Globalvalue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream("C:\\Users\\NayazAhmed\\eclipse-workspace\\RestAPICucumberFramework\\src\\test\\resources\\global.properties");
		prop.load(input);
		return prop.getProperty(key);
	 
	}
	
		
		public String getjsonpath(Response response, String key)
		{
			String resp = response.asString();
			JsonPath js = new JsonPath(resp);
			return js.get(key);
			
		}
		
	
}
