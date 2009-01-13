package brickcitybank;

public class Account {

	//attributes
	private int idAcount;
	private double balance;
	
	public int getIdAccount()
	{
		return idAcount;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setIdAccount(int accountid)
	{
		idAcount = accountid;
	}
	public void setBalance(double _balance)
	{
		balance = _balance;
	}

}
