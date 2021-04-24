package com.meb.repo.csv2mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.meb.repo.csv2mongo.document.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{
}