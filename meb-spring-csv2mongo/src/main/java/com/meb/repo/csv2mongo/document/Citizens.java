package com.meb.repo.csv2mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "citizens")
public class Citizens {

	@Id
	private Long citizenId;

	private String citizenName;

	private String city;

	private int age;
	
	public Citizens(Long citizenID, String citizenName, String city, int age) {
		this.citizenId = citizenID;
		this.citizenName = citizenName;
		this.city = city;
		this.age = age;
	}

	public Long getId() {
		return citizenId;
	}

	public void setId(Long id) {
		this.citizenId = id;
	}

	public String getName() {
		return citizenName;
	}

	public void setName(String citizenName) {
		this.citizenName = citizenName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "CitizenID [id=" + citizenId + ", CitizenName=" + citizenName + ", city=" + city + ", age=" + age + "]";
	}

}