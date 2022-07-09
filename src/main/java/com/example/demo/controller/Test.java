package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	List<Student> studentlist = new ArrayList<Student>();
	Student s1 = new Student(1, "Arvind", "madurai");
	Student s2 = new Student(2, "san", "vdu");
	Student s3 = new Student(3, "jay", "cbe");
	Student s4 = new Student(4, "Arvind", "chennai");

	@PostConstruct
	public void init() {
		studentlist.add(s1);
		studentlist.add(s2);
		studentlist.add(s3);
		studentlist.add(s4);
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public List<Student> getallstudents() {
		System.out.println(studentlist);
		return studentlist;
	}

	@RequestMapping(value = "/students/add", method = RequestMethod.POST)
	public Student addstudents(@RequestBody Student stud) {
		System.out.println(stud);
		studentlist.add(stud);
		return stud;
	}

	@RequestMapping(value = "/students/{name}", method = RequestMethod.GET)
	public List<Student> getstudentbyname(@PathVariable("name") String name) {

		List<Student> studentlist1 = studentlist.stream().filter(su -> su.getName().equals(name))
				.collect(Collectors.toList());
		System.out.println(studentlist1);

		return studentlist1;
	}

	// post --partial
	@PostMapping(value = "/students/list")
	public Student Addstudentbyparam(@RequestParam int id, @RequestParam("Name") String name) {
		Student s1 = new Student();
		s1.setId(id);
		s1.setName(name);
		studentlist.add(s1);
		System.out.println(s1);
		return s1;

	}

	// delete by id

	@DeleteMapping(value = "/student/list/{id}")
	public List<Student> deleteStudentById(@PathVariable("id") int id) {
		studentlist.remove(--id);
		return studentlist;
	}

	// delete by name
	@DeleteMapping(value = "/students/list/{naam}")
	public List<Student> deleteStudentByName(@PathVariable("naam") String name) {
		// List<Student>
		// list=studentlist.stream().filter(info->(!info.getName().equals(name))).collect(Collectors.toList());

		Student sk = new Student();
		sk = null;
		for (Student ss : studentlist) {
			if (ss.getName().equals(name)) {
				sk = ss;
				System.out.println(sk);
			}
		}
		studentlist.remove(sk);

		return studentlist;
	}

	// update the list
	@PutMapping(value = "/students/update/{naam}")
	public List<Student> updatestudentByname(@PathVariable("naam") String name) {
		studentlist.forEach(info -> {
			if (info.getName().equals(name)) {
				info.setId(100);
				info.setLocation("kerala");
			}
		});

		System.out.println(studentlist);

		return studentlist;
	}
	
	
	@PatchMapping(value="/students/patch/{location}")
	public List<Student> patchupdatebylocation(@PathVariable("location")String location,@RequestBody Student stud ){
		
		studentlist.forEach(info->
		{if(info.getLocation().equals(location))
				{
		     	info.setName(stud.getName());
				}});
		
		
	   return studentlist;
	}
	
	
	
	@RequestMapping(value="/student/values/{name}", params = "summa")
	public List<Student> getlistusingparam(@PathVariable String name){
		System.out.println(name);
		
		return studentlist;
		
	}
	
	
	
	@RequestMapping(value="/student/values/{location}", params = "differenceku")
	public Student getlistParamdiff(@PathVariable String location){
		System.out.println(location);
		Student su=new Student();
		su=null;
		for(Student sk:studentlist) {
			if(sk.getLocation().equals(location)) {
				sk.getId();
				sk.getName();
				sk.getLocation();
				su=sk;
			}
		   
		}
		
		return su ;
		
	}
	
	
	
	//multiple query params
	@RequestMapping(value="/list/location", method = RequestMethod.GET)
	public List<Student> getlistbymultiplequeryparam(@RequestParam("place")Set<String> location,
			   @RequestParam("naam")String name){
		
		List<Student> details=new ArrayList<Student>();
		Student stud1=new Student();
		Student stud2=new Student();
		
		for(String info:location) {
			for(Student ss:studentlist) {
				if(ss.getLocation().equals(info) && ss.getName().equals(name)) {
					  stud1=ss;
				}
			}
		}
		
		System.out.print(stud1);
		details.add(stud1);
		details.add(stud2);
		
		
		return details;
		}
	
	
	

}
