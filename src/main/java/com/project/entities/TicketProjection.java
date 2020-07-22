package com.project.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj",types=Ticket.class)
public interface TicketProjection {
	
	
	public Long getId();
	public String getClientName();
	public double getPrice();
	public Integer getPaymentCode();
	public boolean isBooked();
	public Seat getSeat();
}
