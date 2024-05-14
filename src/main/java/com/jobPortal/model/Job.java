package com.jobPortal.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Job() {
		super();
	}
	public Job(int id, String profile, String experience, List<String> techStack) {
		super();
		this.id = id;
		this.profile = profile;
		this.experience = experience;
		this.techStack = techStack;
	}
	private String profile;
	private String experience;
	private List<String> techStack;
	@Override
	public String toString() {
		return "Job [id=" + id + ", profile=" + profile + ", experience=" + experience + ", techStack=" + techStack
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public List<String> getTechStack() {
		return techStack;
	}
	public void setTechStack(List<String> techStack) {
		this.techStack = techStack;
	}
}
