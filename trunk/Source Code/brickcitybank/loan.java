package brickcitybank;

import java.util.Date;

public class loan {
	
	private int idLoan;
	private Date term;
	private double interest;
	private int creditLine;
	
	public void setIdLoan(int _idLoan)
	{
		idLoan = _idLoan;
	}
	public void setTerm(Date _term)
	{
		term = _term;
	}
	public void setInterest(double rate)
	{
		interest = rate;
	}
	public void setCreditLine(int credit)
	{
		creditLine = credit;
	}
	public int getIdLoan()
	{
		return idLoan;
	}
	public Date getTerm()
	{
		return term;
	}
	public int getCreditLine()
	{
		return creditLine;
	}
}
