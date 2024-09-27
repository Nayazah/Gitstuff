package Utilites;

public enum ApiResources {
	
	Addplaceapi("/maps/api/place/add/json"),
	getplaceapi("/maps/api/place/get/json"),
	deletePlaceapi("/maps/api/place/delete/json");
	private String resource;
	
	  ApiResources(String resource) {
		  this.resource = resource;
		  		
	}

	  public String getResource() {
		  return resource;
		  
	  }
}
