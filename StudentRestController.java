package com.shivila.boot.contrller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivila.boot.model.Student;
import com.shivila.boot.service.IStudentService;
@RestController
@RequestMapping("/student")
public class StudentRestController {
@Autowired
	private IStudentService service;
	
	@PostMapping("/save")
	public ResponseEntity<String>saveStudent( 
			@RequestBody Student student)
	{
		ResponseEntity<String> resp =null;
		try {
			Integer id=service.saveStudent(student);
			String body="Student'"+id+"' created";
			
					resp=new ResponseEntity<String>(body,
							HttpStatus.CREATED);
		
		}catch(Exception e) {
		resp=new ResponseEntity<String>
		("Unable to create Student", 
				HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
	}
		
		
		return resp;
	}
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() {
		ResponseEntity<?> resp=null;
		try {
		     List<Student>list= service.getAllStudents();
		     if (list !=null && !list.isEmpty()) {
				list.sort(
						(s1,s2)->s1.getName().compareTo(s2.getName()));
				resp=new ResponseEntity<List<Student>>(list,HttpStatus.OK);
			} else {
				resp=new ResponseEntity<String>("NO STUDENTS FOUND",HttpStatus.OK);
			}
}catch(Exception e) {
	resp=new ResponseEntity<String>("Unable to fetch Student",HttpStatus.INTERNAL_SERVER_ERROR);
	e.printStackTrace();
}
return resp;
	}
	@GetMapping("/one/{id}")
	public ResponseEntity<?>getOne(@PathVariable Integer id){
		ResponseEntity<?>resp=null;
	try {
		Optional<Student>opt=service.getOneStudent(id);
		if (opt.isPresent()) {
			resp=ResponseEntity.ok(opt.get());
		}
		else {
			resp=new ResponseEntity<String>("Student '"+id+"'not exist",HttpStatus.BAD_REQUEST);
		}
	}catch(Exception e) {
		resp=new ResponseEntity<String>("Unable to  process fetch student",HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
	}
	return resp;

	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String>removeStudent(@PathVariable Integer id)
	{
		ResponseEntity<String> resp=null;
		
		try {
			boolean exist=service.isStudentExist(id);
			if (exist) {
				 service.deleteStudent(id);
				resp=new ResponseEntity<String>(
						"Student '"+id+"' deleted",HttpStatus.OK);	
			}
			}
		catch(Exception e) {
			resp=new ResponseEntity<String>("Unable to delete ",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
	
	
}