package com.dhilara.jobapp.job;

import java.util.List;

public interface JobService {

	 List<Job> findAll();
	
	 void addJob(Job job);
	
	 Job findJobByID(Long id);
	
	 boolean deleteByID(Long id);
	
	 boolean updateByID(Job updatedJob,Long id);
}
