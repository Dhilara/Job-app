package com.dhilara.jobapp.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhilara.jobapp.job.Job;
import com.dhilara.jobapp.job.JobRepository;
import com.dhilara.jobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepository jobRepo;
	
	@Override
	public List<Job> findAll() {	
		return jobRepo.findAll();
	}

	@Override
	public void addJob(Job job) {
		jobRepo.save(job);		
	}

	@Override
	public Job findJobByID(Long id) {
		return jobRepo.findById(id).orElseThrow(()-> new RuntimeException("Item not found for id "+ id));
	}

	@Override
	public boolean deleteByID(Long id) {
		jobRepo.deleteById(id);
		if(jobRepo.findById(id).isPresent())
			return false;
		return true;
		
	}

	@Override
	public boolean updateByID(Job updatedJob,Long id) {
		Optional<Job> job = jobRepo.findById(id);
		if(job.isEmpty()) {
			return false;
		}
		jobRepo.save(updatedJob);
		return true;		
	}
	

}
