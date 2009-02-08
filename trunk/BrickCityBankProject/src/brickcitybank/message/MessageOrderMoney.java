/**
 * 
 */
package brickcitybank.message;

/**
 * @author Caroline
 *
 */
public class MessageOrderMoney extends MessageOrder{
	
	//attribute
	private double sum;
	public final int TYPE_TRANSACTION;

	/**
	 * Constructor
	 * @param id_user
	 * @param idAcount
	 * @param type
	 */
	public MessageOrderMoney(int id_user, int idAcount, int type, double sum) {
		super(id_user, idAcount);
		TYPE_TRANSACTION = type;
		this.sum = sum;
	}

	
	public int getTYPE_TRANSACTION() {
		return TYPE_TRANSACTION;
	}


	public double getSum() {
		return sum;
	}


	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	

	
}
