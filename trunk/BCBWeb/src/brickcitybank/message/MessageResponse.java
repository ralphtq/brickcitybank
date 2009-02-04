/**
 * 
 */
package brickcitybank.message;

import java.io.Serializable;

/**
 * @author Caroline
 *
 */
public class MessageResponse implements Serializable {
	
	private String response ;
	
	/**
	 * Default Constructor
	 */
	public MessageResponse() {
		super();
		response = "";
	}
	
	
	/**
	 * Constructor
	 * @param response
	 */
	public MessageResponse(String response) {
		super();
		this.response = response;
	}



	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
	

}
