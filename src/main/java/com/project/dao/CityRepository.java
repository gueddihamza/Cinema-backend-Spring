package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.entities.City;


@RepositoryRestResource()
@CrossOrigin("*")
public interface CityRepository extends JpaRepository<City, Long> {

}
