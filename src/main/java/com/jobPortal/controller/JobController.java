package com.jobPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.model.Job;
import com.jobPortal.service.JobService;

@RestController
@RequestMapping("job")
public class JobController {

	@Autowired
	JobService service;
	
	@GetMapping("")
	public List<Job> getJobs() {
		return service.getJobs();
	}
	
	@PostMapping("add")
	public Job addJob(@RequestBody Job job) {
		return service.addJob(job);
	}
	
	@GetMapping("{profile}")
	public List<Job> getJobByProfile(@PathVariable String profile){
		return service.getJobByProfile(profile);
	}

	@PutMapping("{id}")
	public Job updateJob(@PathVariable int id,@RequestBody Job job) {
		return service.updatejob(id,job);
	}
	
	@DeleteMapping("{id}")
	public String deleteJob(@PathVariable int id) {
		return service.deleteJob(id);
	}
}
