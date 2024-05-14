package com.jobPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobPortal.dao.jobRepo;
import com.jobPortal.model.Job;

@Service
public class JobService {

	@Autowired
	jobRepo repo;
	
	public List<Job> getJobs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public Job addJob(Job job) {
		// TODO Auto-generated method stub
		return repo.save(job);
	}

	public List<Job> getJobByProfile(String profile) {
		// TODO Auto-generated method stub
		return repo.findByProfile(profile);
	}

	public Job updatejob(int id, Job job) {
		// TODO Auto-generated method stub

			Job j = repo.findById(id).get();
			j.setExperience(job.getExperience());
			j.setProfile(job.getProfile());
			j.setTechStack(j.getTechStack());
		
		
		return repo.save(j);
	}

	public String deleteJob(int id) {
		// TODO Auto-generated method stub
		Job j = repo.findById(id).get();
		
		 repo.deleteById(id);
		 return "success";
	}

}
