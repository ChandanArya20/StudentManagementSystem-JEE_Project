package in.ineuron.serviceFactory;

import in.ineuron.service.*;

public class StudentServiceFactory {
	
	private StudentServiceFactory() {
		
	}
	
	private static IStudentService studentService=null;
	public static IStudentService getStudentService() {
		
		if(studentService==null)
			studentService=new StudentServiceImpl();
		
		return studentService;
			
	}
}
