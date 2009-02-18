/**
 * 
 */
package brickcitybank.message;

/**
 * @author Caroline
 *
 */
public class MessageTransfer extends MessageOrder
{
	

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	private double sumToTransfer;//the sum of money to transfer
	
	private int toIdAccount;//the acount id to tranfer the money


	/**
	 * Constructor
	 * @param id_user
	 * @param idAcount
	 */
	public MessageTransfer(int id_user, int idAcount) 
	{
		super(id_user, idAcount);
	}
	
	
	/**
	 * @return the sumToTransfer
	 */
	public double getSumToTransfer() 
	{
		return sumToTransfer;
	}


	/**
	 * @param sumToTransfer the sumToTransfer to set
	 */
	public void setSumToTransfer(double sumToTransfer) 
	{
		this.sumToTransfer = sumToTransfer;
	}


	/**
	 * @return the toIdAccount
	 */
	public int getToIdAccount() 
	{
		return toIdAccount;
	}


	/**
	 * @param toIdAccount the toIdAccount to set
	 */
	public void setToIdAccount(int toIdAccount) {
		this.toIdAccount = toIdAccount;
	}


	/**
	 * Constructor
	 * @param id_user
	 * @param idAcount
	 * @param sumToTransfer
	 * @param toIdAccount
	 */
	public MessageTransfer(int id_user, int idAcount, double sumToTransfer, int toIdAccount) 
	{
		super(id_user, idAcount);
		System.out.println("MessageTransfer - constructor");
		this.sumToTransfer = sumToTransfer;
		this.toIdAccount = toIdAccount;
	}

}
