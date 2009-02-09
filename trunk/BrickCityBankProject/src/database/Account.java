package database;

import java.io.Serializable;

/**
 * @author  Louis Duke
 */
public class Account implements Serializable{

	public Account(int idAcount, double balance) {
		super();
		this.idAcount = idAcount;
		this.balance = balance;
	}
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
