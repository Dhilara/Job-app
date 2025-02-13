package com.dhilara.jobapp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;

	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
		return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createJob(@RequestBody Job job) {
		jobService.addJob(job);
		return new ResponseEntity<>("Job Created Successfully",HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id){
		Job job= jobService.findJobByID(id);
		if(job != null)
			return new ResponseEntity<>(job,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable Long id){
		boolean result=jobService.updateByID(job, id);
		if (result == true)
			return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id ){
		boolean result = jobService.deleteByID(id);
		if(result)
		 return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
