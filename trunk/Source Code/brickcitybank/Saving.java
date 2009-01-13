package brickcitybank;

public class Saving {
	
	private int idSavings;
	private double interest;
	
	public void setIdSavings(int accountNumber)
	{
		idSavings = accountNumber;
	}
	public void setInterest(double rate)
	{
		interest = rate;
	}
	public int getIdSavings()
	{
		return idSavings;
	}
	public double getInterest()
	{
		return interest;
	}

}
