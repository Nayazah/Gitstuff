Feature:Validation place API

Scenario Outline:Verify if place is successfull added using addplace api
   Given Add place payload with "<name>" "<language>" "<address>"
   When user call "Addplaceapi" with "POST" http request
   Then the api call is success with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_id created maps to "<name>" using "getplaceapi"  
   
 Examples:
 
|name	|language	|address|
|Ahmed|French	  |World trade centre|
 
 
 Scenario:Verify if Delete place functionality is working

		Given Deleteplace payload 
		When user call "deletePlaceapi" with "POST" http request
		Then the api call is success with status code 200
		And "status" in response body is "OK"