package common;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTemplate {
	//1. 직접 값을 지정한 경우
	/*public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}*/
	
	//2. driver.properties 파일에서 값을 읽어서 적용하는 경우
	//static 메소드 내에서는 non-static 메소드를 그냥 사용할 수 없음
	private static class ReadProperties{
		private Properties prop;
		
		public ReadProperties(){
			prop = new Properties();
			try {
				prop.load(new InputStreamReader(
						this.getClass()
						.getResourceAsStream(
								"driver.properties")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public Properties getProp(){
			return prop;
		}
	}
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {			
			Properties prop = new ReadProperties().getProp();
			
			Class.forName(prop.getProperty("driver"));
			con = DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("passwd"));
			
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return con;
	}

	/*//3. 톰켓이 제공하는 DBCP(DataBase Connetion Pool)를 이용
	//web/META-INF/context.xml 파일에 설정됨
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			//context.xml 에 설정된 <Resource> 엘리먼트의
			//DBCP 를 통해서 Connection 을 얻어옴
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)
					initContext.lookup(
							"java:comp/env/jdbc/oraDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	*/
	
	public static void close(Connection con) {
		try {
			if(!con.isClosed()){
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if(!stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if(!rset.isClosed())
				rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection con) {
		try {
			if(!con.isClosed())
				con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			if(!con.isClosed())
				con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
