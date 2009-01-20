/**
 * @author Bill Manville
 * @version 1.0
 *
 * This class uses JDBC to acquire a connection to a MySql database.
 * This requires the use of the java.sql package. 
 * This also requires the use of a jdbc driver that is from the MySql website.
 * The driver has to be added to the classpath of the project. 
 */
package brickcitybank;
import java.sql.*;

/**
 * @author  Louis Duke
 */
public class DBConnection {
	
	//attributes
	/**
	 * @uml.property  name="address"
	 */
	private String address;	//address of the database server
	/**
	 * @uml.property  name="port"
	 */
	private String port;	//port of the database server
	/**
	 * @uml.property  name="dBName"
	 */
	private String DBName;	//the name of the database on the server
	/**
	 * @uml.property  name="username"
	 */
	private String username;//username of the user connecting to the server
	/**
	 * @uml.property  name="password"
	 */
	private String password;//password of the user
	/**
	 * @uml.property  name="connStr"
	 */
	private String connStr; //connection string that is assembled to connect to the server
	/**
	 * @uml.property  name="conn"
	 */
	private Connection conn;//connection object that is required later to perform queries
	
	//--------------------Constructors--------------------
	
	/**
	 * Default constructor sets values to default value.
	 * can be used if only one database server is used for the program.
	 * 
	 * will build the jdbc connection string based on the set values and connect to the database. 
	 */
	public DBConnection(String pass)
	{
		setAddress("localhost");
		//setPort("3306");
		setDBName("mydb");
		setUserName("root");
		setPassword(pass);
		
		buildConnString();
		connect();
	}
	
	/**
	 *  Overloaded constructor used to connect to a default server set up w/a specific password
	 *  
	 *  @param pass - Password from the user
	 *  @param DB	- Database to connect to, blank connects to the server and no database
	 *  
	 *  @author L. Duke
	 */
	public DBConnection(String db, String pass){
		setAddress("localhost");
		setPort("3306");
		setDBName(db);
		setUserName("root");
		setPassword(pass);
		buildConnString();
		connect();
	}
	
	/**
	 * parameterized constructor used for MySql database servers running on the default port.
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
	 * @param pass - password for the user to authenticate on the database server with.
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
	 * @param addr  - address of the database server
	 * @uml.property  name="address"
	 */
	public void setAddress(String addr)
	{
		address = addr;
	}
	/**
	 * sets the port to connect to\
	 * @param  DBport
	 * @uml.property  name="port"
	 */
	public void setPort(String DBport)
	{
		port = DBport;
	}
	/**
	 * sets the name of the database that is going to be used by the program
	 * @param  databaseName
	 * @uml.property  name="dBName"
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
	 * @param  pass
	 * @uml.property  name="password"
	 */
	public void setPassword(String pass)
	{
		password = pass;
	}
	/**
	 * sets the connection string that is used for jdbc to connect to the server
	 * @param  connString
	 * @uml.property  name="connStr"
	 */
	public void setConnStr(String connString)
	{
		connStr = connString;
	}
	/**
	 * sets the connection object 
	 * @param  connect
	 * @uml.property  name="conn"
	 */
	public void setConn(Connection connect)
	{
		conn = connect;
	}
	//--------------------accessers--------------------
	/**
	 * returns the server address that is used to connect to the database
	 * @return  address - server address
	 * @uml.property  name="address"
	 */
	public String getAddress()
	{
		return address;
	}
	/**
	 * returns the port number of the database
	 * @return  port - port used to connect to the database
	 * @uml.property  name="port"
	 */
	public String getPort()
	{
		return port;
	}
	/**
	 * returns the database name
	 * @return  name of the database
	 * @uml.property  name="dBName"
	 */
	public String getDBName()
	{
		return DBName;
	}
	/**
	 * returns the user name that is authenticating to the database.
	 * @return  the username for the database
	 * @uml.property  name="username"
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * returns the password of the database user
	 * @return  user's password
	 * @uml.property  name="password"
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @return  connection string used to connect to the database
	 * @uml.property  name="connStr"
	 */
	public String getConnStr()
	{
		return connStr;
	}
	/**
	 * returns the jdbc connection object
	 * @return  conn connection class object
	 * @uml.property  name="conn"
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
		System.out.println(connStr);	
	}
	public void connect()
	{
		try
		{
			//load driver
			System.out.println("Attempting to load driver!");
			Class.forName("org.gjt.mm.mysql.Driver");
			System.out.println("Driver loaded!");
			
			//create connection
			conn = DriverManager.getConnection(connStr,username,password);
			System.out.println("Connection to database established!");
			
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println("ClassNotFound!");
			System.out.println(cnf.getMessage());
		}
		catch(SQLException sql)
		{
			System.out.println("ClassNotFound!");
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
