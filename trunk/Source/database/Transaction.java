package database;

import java.util.Date;

/**
 * @author  Louis Duke
 */
public class Transaction {
/*
+---------------+---------+------+-----+---------+----------------+
| Field         | Type    | Null | Key | Default | Extra          |
+---------------+---------+------+-----+---------+----------------+
| idTransaction | int(11) | NO   | PRI | NULL    | auto_increment |
| type          | char(1) | NO   | MUL | NULL    |                |
| account1      | int(12) | YES  | MUL | NULL    |                |
| account2      | int(12) | YES  | MUL | NULL    |                |
| Date          | date    | NO   |     | NULL    |                |
| Time          | time    | NO   |     | NULL    |                |
| old_balance1  | double  | YES  |     | NULL    |                |
| new_balance1  | double  | YES  |     | NULL    |                |
| old_balance2  | double  | YES  |     | NULL    |                |
| new_balance2  | double  | YES  |     | NULL    |                |
+---------------+---------+------+-----+---------+----------------+
 */
	
	private int idTransation;
	/**
	 * @uml.property  name="type"
	 */
	private char type;
	/**
	 * @uml.property  name="account1"
	 */
	private int account1;
	/**
	 * @uml.property  name="account2"
	 */
	private int account2;
	/**
	 * @uml.property  name="date"
	 */
	private Date date;
	/**
	 * @uml.property  name="time"
	 */
	private Date time;
	private double oldBalance1;
	private double newBalance1;
	private double oldBalance2;
	private double newBalance2;
	
	public void setIdTransaction(int _idTransation)
	{
		idTransation = _idTransation;
	}
	/**
	 * @param _type
	 * @uml.property  name="type"
	 */
	public void setType(char _type)
	{
		type = _type;
	}
	/**
	 * @param _account1
	 * @uml.property  name="account1"
	 */
	public void setAccount1(int _account1)
	{
		account1 = _account1;
	}
	/**
	 * @param _account2
	 * @uml.property  name="account2"
	 */
	public void setAccount2(int _account2)
	{
		account2 = _account2;
	}
	/**
	 * @param _date
	 * @uml.property  name="date"
	 */
	public void setDate(Date _date)
	{
		date = _date;
	}
	/**
	 * @param _time
	 * @uml.property  name="time"
	 */
	public void setTime(Date _time)
	{
		time = _time;
	}
	public void setOldbalence1(double _oldBalence1)
	{
		oldBalance1 =  _oldBalence1;
	}
	public void setOldbalence2(double _oldBalence2)
	{
		oldBalance2 =  _oldBalence2;
	}
	public void setNewbalence1(double _newBalence1)
	{
		newBalance1 =  _newBalence1;
	}public void newNewbalence2(double _newBalence2)
	{
		newBalance2 =  _newBalence2;
	}
	//================ Mutators ======================
	public int getIdTransaction()
	{
		return idTransation;
	}
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public char getType()
	{
		return type;
	}
	/**
	 * @return
	 * @uml.property  name="account1"
	 */
	public int getAccount1()
	{
		return account1;
	}
	/**
	 * @return
	 * @uml.property  name="account2"
	 */
	public int getAccount2()
	{
		return account2;
	}
	/**
	 * @return
	 * @uml.property  name="date"
	 */
	public Date getDate()
	{
		return date;
	}
	/**
	 * @return
	 * @uml.property  name="time"
	 */
	public Date getTime()
	{
		return time;
	}
	public double getOldbalence1()
	{
		return oldBalance1;
	}
	public double getOldbalence2()
	{
		return oldBalance2;
	}
	public double getNewbalence1()
	{
		return newBalance1;
	}
	public double getNewbalence2()
	{
		return newBalance2;
	}
}
