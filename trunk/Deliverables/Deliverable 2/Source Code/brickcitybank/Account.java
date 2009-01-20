package brickcitybank;

/**
 * @author  Louis Duke
 */
public class Account {

	//attributes
	private int idAcount;
	/**
	 * @uml.property  name="balance"
	 */
	private double balance;
	
	public int getIdAccount()
	{
		return idAcount;
	}
	/**
	 * @return
	 * @uml.property  name="balance"
	 */
	public double getBalance()
	{
		return balance;
	}
	public void setIdAccount(int accountid)
	{
		idAcount = accountid;
	}
	/**
	 * @param _balance
	 * @uml.property  name="balance"
	 */
	public void setBalance(double _balance)
	{
		balance = _balance;
	}

}
