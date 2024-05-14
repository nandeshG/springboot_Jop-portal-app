package com.jobPortal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobPortal.model.Job;

@Repository
public interface jobRepo extends JpaRepository<Job, Integer>{

	public List<Job> findByProfile(String profile);
}
