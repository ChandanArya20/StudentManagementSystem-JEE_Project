package in.ineuron.persistance;

import java.io.IOException;
import java.sql.*;

import in.ineuron.dto.Student;
import in.ineuron.utils.JdbcUtils;

public class StudentDaoImpl implements IStudentDao {

	Connection connection=null;
	PreparedStatement stm=null;
	ResultSet resultSet=null;
	
	@Override
	public String addStudent(Student student) {
		
		String query="insert into student(name ,age,address)values(?,?,?)";		
		try {			
			connection=JdbcUtils.getJdbcConnection();
			
			if(connection!=null)
				stm=connection.prepareStatement(query);
			
			if(stm!=null) {			
				stm.setString(1,student.getSname());
				stm.setInt(2,student.getSage());
				stm.setString(3,student.getSaddress());
				
				int flag=stm.executeUpdate();
				
				if(flag==1)
					return "success";
			}
					
		} catch (SQLException | IOException e) {
			return "failure";
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		
		String query="select * from student where id=?";
		Student stud=null;			
		try {
			connection=JdbcUtils.getJdbcConnection();
			if(connection!=null)
				stm=connection.prepareStatement(query);	
			
			if(stm!=null) {
				stm.setInt(1, sid);
				resultSet=stm.executeQuery();
			}			
			if(resultSet.next()) {		
				stud=new Student();
				stud.setSid(resultSet.getInt(1));
				stud.setSname(resultSet.getString(2));
				stud.setSage(resultSet.getInt(3));
				stud.setSaddress(resultSet.getString(4));
				
				return stud;
			}
			
		} catch (SQLException | IOException e) {
			return stud;
		}		
		return stud;
	}

	@Override
	public String updateStudent(Student student) {
		
		String query ="update student set name=?, age=?, address=? where id=?";		
		try {		
			connection=JdbcUtils.getJdbcConnection();			
			if(connection!=null)
				stm=connection.prepareStatement(query);	
			
			if(stm!=null) {						
				stm.setString(1, student.getSname());
				stm.setInt(2, student.getSage());
				stm.setString(3, student.getSaddress());
				stm.setInt(4, student.getSid());
				
				int rowCount=stm.executeUpdate();
				
				if(rowCount==1)
					return "success";
				else
					return "not found";
			}
			
		} catch (IOException | SQLException e) {	
			return "failure";
		}
		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		
		String query ="delete from student where id=?";		
		try {		
			connection=JdbcUtils.getJdbcConnection();			
			if(connection!=null)
				stm=connection.prepareStatement(query);	
			
			if(stm!=null) {						
				stm.setInt(1, sid);
				int rowCount=stm.executeUpdate();
				
				if(rowCount==1)
					return "success";
				else
					return "not found";
			}
			
		} catch (IOException | SQLException e) {	
			return "failure";
		}
		return "failure";
	}

}
