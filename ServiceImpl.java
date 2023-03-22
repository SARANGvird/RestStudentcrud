package com.shivila.boot.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivila.boot.model.Student;
import com.shivila.boot.repo.StudentRepository;
import com.shivila.boot.service.IStudentService;

@Service
public class ServiceImpl implements IStudentService {
@Autowired
	private StudentRepository repo;
	@Override
	public Integer saveStudent(Student s) {
 s=repo.save(s);
 return s.getId();
	}
	
	@Override
	public void updateStudent(Student s) {
		repo.save(s);
		
	}

	@Override
	public void deleteStudent(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public Optional<Student>getOneStudent(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean isStudentExist(Integer id) {
		
		return repo.existsById(id);
	}

}
