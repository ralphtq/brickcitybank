/**
 * 
 */
package brickcitybank.message;

import java.io.Serializable;

/**
 * @author Caroline
 * Class which represents an orderMessage
 */
public class MessageOrder implements Serializable {
	//the attributes
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int id_user;//the id of the user who throw this message
	private int idAcount;//the account number of the order
	
	/**
	 * Constructor
	 * @param id_user
	 * @param idAcount
	 */
	public MessageOrder(int id_user, int idAcount) 
	{
		super();
		this.id_user = id_user;
		this.idAcount = idAcount;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getIdAcount() {
		return idAcount;
	}

	public void setIdAcount(int idAcount) {
		this.idAcount = idAcount;
	}
	
	
	
	
	
	
	

}
