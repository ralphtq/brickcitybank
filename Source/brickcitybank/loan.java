package brickcitybank;

import java.util.Date;

/**
 * @author  Louis Duke
 */
public class loan {
	
	/**
	 * @uml.property  name="idLoan"
	 */
	private int idLoan;
	/**
	 * @uml.property  name="term"
	 */
	private Date term;
	private double interest;
	/**
	 * @uml.property  name="creditLine"
	 */
	private int creditLine;
	
	/**
	 * @param _idLoan
	 * @uml.property  name="idLoan"
	 */
	public void setIdLoan(int _idLoan)
	{
		idLoan = _idLoan;
	}
	/**
	 * @param _term
	 * @uml.property  name="term"
	 */
	public void setTerm(Date _term)
	{
		term = _term;
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
	 * @param credit
	 * @uml.property  name="creditLine"
	 */
	public void setCreditLine(int credit)
	{
		creditLine = credit;
	}
	/**
	 * @return
	 * @uml.property  name="idLoan"
	 */
	public int getIdLoan()
	{
		return idLoan;
	}
	/**
	 * @return
	 * @uml.property  name="term"
	 */
	public Date getTerm()
	{
		return term;
	}
	/**
	 * @return
	 * @uml.property  name="creditLine"
	 */
	public int getCreditLine()
	{
		return creditLine;
	}
}
