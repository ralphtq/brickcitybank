package brickcitybank;

/**
 * @author  Louis Duke
 */
public class Saving {
	
	/**
	 * @uml.property  name="idSavings"
	 */
	private int idSavings;
	/**
	 * @uml.property  name="interest"
	 */
	private double interest;
	
	/**
	 * @param accountNumber
	 * @uml.property  name="idSavings"
	 */
	public void setIdSavings(int accountNumber)
	{
		idSavings = accountNumber;
	}
	/**
	 * @param rate
	 * @uml.property  name="interest"
	 */
	public void setInterest(double rate)
	{
		interest = rate;
	}
	/**
	 * @return
	 * @uml.property  name="idSavings"
	 */
	public int getIdSavings()
	{
		return idSavings;
	}
	/**
	 * @return
	 * @uml.property  name="interest"
	 */
	public double getInterest()
	{
		return interest;
	}

}
