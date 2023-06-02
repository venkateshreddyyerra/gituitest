package com.Test.ResponseEntityTest.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Test.ResponseEntityTest.entity.Student;
import com.Test.ResponseEntityTest.repo.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	private StudentRepo repo;
	
	@PostMapping("savestd")
	public ResponseEntity<Student> savestd(@RequestBody Student std){
		repo.save(std);
		return new ResponseEntity<Student>(std,HttpStatus.OK);
	}
	
	@GetMapping("getstudets")
	public ResponseEntity<List<Student>> getstudents(){
		List<Student> l = repo.findAll();
		ResponseEntity<List<Student>> re = null ;
		if(l.isEmpty()) {
			re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			re = new ResponseEntity<List<Student>>(l,HttpStatus.OK);
		}
		return re;
	}
	
	@GetMapping("getbyid/{sid}")
	public ResponseEntity<Student> getbyid(@PathVariable("sid") Integer id)
	{
	Optional<Student> ot =	repo.findById(id);
	 ResponseEntity<Student> re = null;
	if(ot.isPresent()) {
	Student s =	ot.get();
	re = new ResponseEntity<Student>(s,HttpStatus.OK);
	}
	else
	{
		re = new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
	}
		return re;
	}
	
	
}
