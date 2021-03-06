package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.entities.Seat;


@RepositoryRestResource
@CrossOrigin("*")
public interface SeatRepository extends JpaRepository<Seat, Long> {

}
