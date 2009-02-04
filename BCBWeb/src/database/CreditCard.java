package database;

import java.util.Date;
/**
 * @author  Louis Duke
 */
public class CreditCard {
	
	/**
	 * @uml.property  name="cardNumber"
	 */
	private int cardNumber;
	/**
	 * @uml.property  name="pin"
	 */
	private int pin;
	/**
	 * @uml.property  name="expiration"
	 */
	private Date expiration;
	
	/**
	 * @param _cardNumber
	 * @uml.property  name="cardNumber"
	 */
	public void setCardNumber(int _cardNumber)
	{
		cardNumber = _cardNumber;
	}
	/**
	 * @param _pin
	 * @uml.property  name="pin"
	 */
	public void setPin(int _pin)
	{
		pin = _pin;
	}
	/**
	 * @param date
	 * @uml.property  name="expiration"
	 */
	public void setExpiration(Date date)
	{
		expiration = date;
	}
	/**
	 * @return
	 * @uml.property  name="cardNumber"
	 */
	public int getCardNumber()
	{
		return cardNumber;
	}
	/**
	 * @return
	 * @uml.property  name="pin"
	 */
	public int getPin()
	{
		return pin;
	}
	/**
	 * @return
	 * @uml.property  name="expiration"
	 */
	public Date getExpiration()
	{
		return expiration;
	}
}
