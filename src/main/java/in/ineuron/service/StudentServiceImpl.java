package in.ineuron.service;

import in.ineuron.daofactoy.StudentDaoFactory;
import in.ineuron.dto.Student;
import in.ineuron.persistance.IStudentDao;

public class StudentServiceImpl implements IStudentService{

	IStudentDao studentDao=null;
	
	@Override
	public String addStudent(Student student) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.addStudent(student);
	}

	@Override
	public Student searchStudent(Integer sid) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao=StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(sid);
	}
}
