package com.meb.repo.csv2mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "customers")
public class Client {

	@Id
	private Long customerId;

	private String customerName;

	private String city;

	private int age;
	
	public Client(Long id, String name, String address, int age) {
		this.customerId = id;
		this.customerName = name;
		this.city = address;
		this.age = age;
	}

	public Long getId() {
		return customerId;
	}

	public void setId(Long id) {
		this.customerId = id;
	}

	public String getName() {
		return customerName;
	}

	public void setName(String name) {
		this.customerName = name;
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
		return "Customer [id=" + customerId + ", name=" + customerName + ", address=" + city + ", age=" + age + "]";
	}

}