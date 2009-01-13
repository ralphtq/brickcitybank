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
}
