package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.project.entities.Movie;
import com.project.entities.Room;
import com.project.entities.Ticket;
import com.project.service.ICinemaInitService;

@SpringBootApplication
public class CinemaProjectApplication implements CommandLineRunner{
	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration configuration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		configuration.exposeIdsFor(Movie.class,Room.class,Ticket.class);
		/*cinemaInitService.initCities();
		cinemaInitService.initCinemas();
		cinemaInitService.initRooms();
		cinemaInitService.initSeats();
		cinemaInitService.initSessions();
		cinemaInitService.initGenres();
		cinemaInitService.initMovies();
		cinemaInitService.initmovieProjections();
		cinemaInitService.initTickets();*/
		//cinemaInitService.initUsers();
		
	}

}
