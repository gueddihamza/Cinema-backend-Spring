package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.entities.Room;


@RepositoryRestResource
@CrossOrigin("*")
public interface RoomRepository extends JpaRepository<Room, Long> {

}
