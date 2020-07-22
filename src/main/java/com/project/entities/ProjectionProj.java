package com.project.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1" , types= {com.project.entities.MovieProjection.class})
public interface ProjectionProj {
	public Long getId();
	public double getPrice();
	public Date getDateOfProjection();
	public Room getRoom();
	public Movie getMovie();
	public Session getSession();
	public Collection<Ticket> getTickets();
	
}
