package ass.question5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JdbcDao {
	private static String url="jdbc:mysql://localhost:3306/jdbcAssignment";
	private static String username="root";
	private static String password="password";
	private static Connection con=null;
	private static Statement s=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	private static String createTable="create table employee(id int primary key,name varchar(30) not null,department varchar(20) not null,salary double(8,2))";
	private static String insertDetails="insert into employee values(?,?,?,?)";
	private static String updateDetails="update employee set name=?, department=?,salary=? where id=?";
	private static String delete="delete from employee where id=?";
	private static String select="select * from employee";
	public static void createEmpTable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			s=con.createStatement();
			s.executeUpdate(createTable);
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				s.close();
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertEmp(int id,String name,String department,double salary) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			ps=con.prepareStatement(insertDetails);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, department);
			ps.setDouble(4, salary);
			int rowsInserted=ps.executeUpdate();
			if(rowsInserted>0) {
				System.out.println("Employee Details inserted successfully");
			}
			else {
				System.out.println("Employee Details not inserted successfully");
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateEmpDetails(int id,String name,String department,double salary) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			ps=con.prepareStatement(updateDetails);
			ps.setString(1, name);
			ps.setString(2, department);
			ps.setDouble(3, salary);
			ps.setInt(4, id);
			int rowsInserted=ps.executeUpdate();
			if(rowsInserted>0) {
				System.out.println("Employee Details updated successfully");
			}
			else {
				System.out.println("Employee Details not updated successfully");
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}	

	public static void deleteEmp(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			ps=con.prepareStatement(delete);
			ps.setInt(1, id);
			int rowsInserted=ps.executeUpdate();
			if(rowsInserted>0) {
				System.out.println("Employee Details deleted successfully");
			}
			else {
				System.out.println("Employee Details not deleted successfully");
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<Emp> display(){
		ArrayList<Emp> e1=new ArrayList<Emp>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
			s=con.createStatement();
			rs=s.executeQuery(select);
			while(rs.next()) {
				int id=rs.getInt("id");
				String name=rs.getString("name");
				String department=rs.getString("department");
				double salary=rs.getDouble("salary");
				Emp em=new Emp(id,name,department,salary);
				e1.add(em);
			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				s.close();
				con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		return e1;
	}
}