package com.project.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.dao.MovieRepository;
import com.project.dao.TicketRepository;
import com.project.entities.Movie;
import com.project.entities.Ticket;
import lombok.Data;

@RestController
@CrossOrigin("*")
@Transactional
public class CinemaRestController {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping(path = "/movieImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
		Movie movie = movieRepository.findById(id).get();
		String photoName = movie.getPhoto();
		File file = new File(System.getProperty("user.home") + "/cinema/images/" + photoName);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);

	}

	@PostMapping(path = "/payTickets")
	public List<Ticket> payTickets(@RequestBody TicketForm ticketForm) {
		List<Ticket> listTickets = new ArrayList<Ticket>();
		ticketForm.getTickets().forEach(ticketId -> {
			System.out.println(ticketId);
			Ticket ticket = ticketRepository.findById(ticketId).get();
			ticket.setClientName(ticketForm.getClientName());
			ticket.setBooked(true);
			ticket.setPaymentCode(ticketForm.getPaymentCode());
			ticketRepository.save(ticket);
			listTickets.add(ticket);

		});
		return listTickets;
	}

}

@Data
class TicketForm {
	private String clientName;
	private List<Long> tickets = new ArrayList<>();
	private int paymentCode;

}
