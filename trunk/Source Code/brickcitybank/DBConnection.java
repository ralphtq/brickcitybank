/**
 * @author Bill Manville
 * @version 1.0
 * 
 * This class uses JDBC to acquire a connection to a MySql database.
 * This reqires the use of the java.sql package. 
 * This also requires the use of a jdbc driver that is from the MySql website.
 * The driver has to be added to the classpath of the project. 
 */
package brickcitybank;
import java.sql.*;

public class DBConnection {
	
	//attributes
	private String address;	//address of the database server
	private String port;	//port of the database server
	private String DBName;	//the name of the database on the server
	private String username;//username of the user connecting to the server
	private String password;//password of the user
	private String connStr; //connection string that is assembled to connect to the server
	private Connection conn;//connection object that is reqired later to perform queries
	
	//--------------------Constructors--------------------
	
	/**
	 * default constructor sets values to default value.
	 * can be used if only one database server is used for the program.
	 * 
	 * will build the jdbc connection string based on the set valus and connect to the database. 
	 */
	public DBConnection()
	{
		setAddress("localhost");
		//setPort("3306");
		setDBName("test");
		setUserName("root");
		setPassword("123");
		
		buildConnString();
		connect();
	}
	/**
	 * paramiterized constructor used for MySql database servers running on the default port.
	 * will construct a connection string and connect to the database. 
	 * @param addr - server address
	 * @param database - database name
	 * @param user - username
	 * @param pass - password for the username
	 */
	public DBConnection(String addr,String database,String user,String pass)
	{
		setAddress(addr);
		setDBName(database);
		setUserName(user);
		setPassword(pass);
		
		buildConnString();
		connect();
	}
	/**
	 * used for databases not running on the default port. will create a connection string and
	 * connect to the database. 
	 * 
	 * @param addr - address of the database server
	 * @param database - name of the database
	 * @param user - user name for the user to authenticate the server
	 * @param pass - password for the user to authentacate on the database server with.
	 * @param DBport - port database runs on
	 */
	public DBConnection(String addr,String database,String user,String pass,String DBport)
	{
		setAddress(addr);
		setDBName(database);
		setPort(DBport);
		setUserName(user);
		setPassword(pass);
		buildConnString();
		connect();
	}
	//--------------------Mutators--------------------
	/**
	 * sets the address of the database server
	 * 
	 * @param addr - address of the database server
	 */
	public void setAddress(String addr)
	{
		address = addr;
	}
	/**
	 * sets the port to connect to\
	 * 
	 * @param DBport
	 */
	public void setPort(String DBport)
	{
		port = DBport;
	}
	/**
	 * sets the name of the database that is going to be used by the program
	 * @param databaseName
	 */
	public void setDBName(String databaseName)
	{
		
		DBName = databaseName;
	}
	/**
	 * sets the user name that will be connecting to the database
	 * @param user
	 */
	public void setUserName(String user)
	{
		username = user;
	}
	/**
	 * sets the password that the user uses to authenticate to the server
	 * @param pass
	 */
	public void setPassword(String pass)
	{
		password = pass;
	}
	/**
	 * sets the connection string that is used for jdbc to connect to the server
	 * @param connString
	 */
	public void setConnStr(String connString)
	{
		connStr = connString;
	}
	/**
	 * sets the connection object 
	 * @param connect
	 */
	public void setConn(Connection connect)
	{
		conn = connect;
	}
	//--------------------accessers--------------------
	/**
	 * returns the server address that is used to connect to the database
	 * @return address - server address
	 */
	public String getAddress()
	{
		return address;
	}
	/**
	 * returns the port number of the database
	 * @return port - port used to connect to the database
	 */
	public String getPort()
	{
		return port;
	}
	/**
	 * returns the database name
	 * @return name of the database
	 */
	public String getDBName()
	{
		return DBName;
	}
	/** 
	 * returns the user name that is authenticating to the database.
	 * @return the username for the database
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * returns the password of the database user
	 * @return user's password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * 
	 * @return connection string used to connect to the database
	 */
	public String getConnStr()
	{
		return connStr;
	}
	/**
	 * returns the jdbc connection object
	 * @return conn connection class object
	 */
	public Connection getConn()
	{
		return conn;
	}
	//--------------------Methods--------------------
	public void buildConnString()
	{
		
		System.out.println("port: " + port);
		
		if(port == null || port.equals(""))
		{	
			connStr = "jdbc:mysql://" + address + "/" + DBName;
		}
		else
		{
			connStr = "jdbc:mysql://" + address + ":" + port + "/" + DBName;
		}
	}
	public void connect()
	{
		try
		{
			//load driver
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Class found");
			
			//create connection
			conn = DriverManager.getConnection(connStr,username,password);
			System.out.println("connection created");
			
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println(cnf.getMessage());
		}
		catch(SQLException sql)
		{
			System.out.println(sql.getMessage());
		}
	}
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch(SQLException sql)
		{
			System.out.println(sql.getMessage());
		}
	}
}
