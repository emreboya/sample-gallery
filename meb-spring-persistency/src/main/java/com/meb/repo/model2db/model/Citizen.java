package com.meb.repo.model2db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="citizen")
public class Citizen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long citizenId;
	
	@Column(name="citizenName")
	private String citizenName;
	
	@Column(name="citizenLastName")
	private String citizenLastName;
	
	@Column(name="age")
	private int age;


	
	public long getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(long citizenId) {
		this.citizenId = citizenId;
	}

	public String getCitizenName() {
		return citizenName;
	}

	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}

	public String getCitizenLastName() {
		return citizenLastName;
	}

	public void setCitizenLastName(String citizenLastName) {
		this.citizenLastName = citizenLastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	protected Citizen() {}
	
	public Citizen(long citizenID, String citizenName, String citizenLastName, int age) {
		this.citizenId = citizenID;
		this.citizenName = citizenName;
		this.citizenLastName = citizenLastName;
		this.age = age;
	}
	
	public String toString() {
		return String.format("citizenId=%d, firstname='%s', lastname'%s', age=%d", 
								citizenId, citizenName, citizenLastName, age);	
	}
}