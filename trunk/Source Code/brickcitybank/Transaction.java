package brickcitybank;

import java.util.Date;

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
	private char type;
	private int account1;
	private int account2;
	private Date date;
	private Date time;
	private double oldBalance1;
	private double newBalance1;
	private double oldBalance2;
	private double newBalance2;
	
	public void setIdTransaction(int _idTransation)
	{
		idTransation = _idTransation;
	}
	public void setType(char _type)
	{
		type = _type;
	}
	public void setAccount1(int _account1)
	{
		account1 = _account1;
	}
	public void setAccount2(int _account2)
	{
		account2 = _account2;
	}
	public void setDate(Date _date)
	{
		date = _date;
	}
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
	public char getType()
	{
		return type;
	}
	public int getAccount1()
	{
		return account1;
	}
	public int getAccount2()
	{
		return account2;
	}
	public Date getDate()
	{
		return date;
	}
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
