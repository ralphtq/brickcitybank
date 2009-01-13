package brickcitybank;

import java.util.Date;
public class CreditCard {
	
	private int cardNumber;
	private int pin;
	private Date expiration;
	
	public void setCardNumber(int _cardNumber)
	{
		cardNumber = _cardNumber;
	}
	public void setPin(int _pin)
	{
		pin = _pin;
	}
	public void setExpiration(Date date)
	{
		expiration = date;
	}
	public int getCardNumber()
	{
		return cardNumber;
	}
	public int getPin()
	{
		return pin;
	}
	public Date getExpiration()
	{
		return expiration;
	}
}
