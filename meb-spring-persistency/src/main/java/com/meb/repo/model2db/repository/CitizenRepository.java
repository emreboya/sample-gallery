package com.meb.repo.model2db.repository;

import org.springframework.data.repository.CrudRepository;

import com.meb.repo.model2db.model.Citizen;

public interface CitizenRepository extends CrudRepository<Citizen, Long>{
	
}
