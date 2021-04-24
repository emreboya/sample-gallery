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
	
	public Citizens(Long id, String name, String address, int age) {
		this.citizenId = id;
		this.citizenName = name;
		this.city = address;
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

	public void setName(String name) {
		this.citizenName = name;
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
		return "CitizenID [id=" + citizenId + ", CitizenName=" + citizenName + ", address=" + city + ", age=" + age + "]";
	}

}