package com.project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CinemaRepository;
import com.project.dao.CityRepository;
import com.project.dao.GenreRepository;
import com.project.dao.MovieProjectionRepository;
import com.project.dao.MovieRepository;
import com.project.dao.RoomRepository;
import com.project.dao.SeatRepository;
import com.project.dao.SessionRepository;
import com.project.dao.TicketRepository;
import com.project.dao.UserRepository;
import com.project.entities.Cinema;
import com.project.entities.City;
import com.project.entities.Genre;
import com.project.entities.Movie;
import com.project.entities.MovieProjection;
import com.project.entities.Room;
import com.project.entities.Seat;
import com.project.entities.Session;
import com.project.entities.Ticket;
import com.project.entities.User;


@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieProjectionRepository movieProjectionRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initCities() {
			Stream.of("Casablanca","Fes","Tanger","Marrakech","Salé","Meknes","Rabat","Oujda","Kenitra","Agadir").forEach(cityName->{
			City city = new City();
			city.setName(cityName);
			cityRepository.save(city);	
			});
		
	}

	@Override
	public void initCinemas() {
		cityRepository.findAll().forEach(city->{
			Stream.of("MegaRama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ").forEach(cinemaName->{
				Cinema cinema=new Cinema();
				cinema.setName(cinemaName);
				cinema.setCity(city);
				cinema.setNumberOfRooms(2+(int)(Math.random()*5));
				cinemaRepository.save(cinema);	
			});
			
			
		});
		
	}

	@Override
	public void initRooms() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNumberOfRooms();i++) {
				Room room=new Room();
				room.setCinema(cinema);
				room.setName("Room n°"+(i+1));
				room.setNumberOfSeats(20+(int)(Math.random()*20));
				roomRepository.save(room);
			}
			
		});
		
	}

	@Override
	public void initSeats() {
		roomRepository.findAll().forEach(room->{
				for(int i=0;i<room.getNumberOfSeats();i++) {
					Seat seat = new Seat();
					seat.setRoom(room);
					seat.setNumber(i+1);
					seatRepository.save(seat);
					
					
				}

		});
		
	}

	@Override
	public void initSessions() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("09:00","12:00","15:00","18:00","21:00").forEach(time->{
			Session session = new Session();
			try {
				session.setStartTime(dateFormat.parse(time));
				sessionRepository.save(session);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		});
		
		
	}

	@Override
	public void initGenres() {
		Stream.of("Action","Horror","Science Fiction","Comedy","Crime").forEach(genreName->{
			Genre genre=new Genre();
			genre.setName(genreName);
			genreRepository.save(genre);
			
		});
		
	}

	@Override
	public void initMovies() {
		List<Genre> genres = genreRepository.findAll();
		double[] durations=new double[] {178,142,152,103,186,142,115};
		int i=0;
		Stream.of("The godfather","The Shawshank Redemption","The Dark Knight","SAW","The good The bad and the ugly","Forrest Gump","War for the Planet of the Apes")
		.forEach(movieName->{
			Movie movie=new Movie();
			movie.setName(movieName);
			movie.setPhoto(movieName.replaceAll(" ", "")+".jpg");
			movie.setDuration(durations[new Random().nextInt(durations.length)]);
			movie.setGenre(genres.get(new Random().nextInt(genres.size())));
			movieRepository.save(movie);
		});
		
	}

	@Override
	public void initmovieProjections() {
		double[]prices = new double[] {30,40,50,60,70,80,90,100};
		List<Movie> movies = movieRepository.findAll();
		roomRepository.findAll().forEach(room->{
			int index = new Random().nextInt(movies.size());
			Movie movie = movies.get(index);
			//movieRepository.findAll().forEach(movie->{
				sessionRepository.findAll().forEach(session->{
					MovieProjection projection = new MovieProjection();
					projection.setDateOfProjection(new Date());
					projection.setPrice(prices[new Random().nextInt(prices.length)]);
					projection.setSession(session);
					projection.setMovie(movie);
					projection.setRoom(room);
					movieProjectionRepository.save(projection);
				});
			});
		
		
	}

	@Override
	public void initTickets() {
		movieProjectionRepository.findAll().forEach(projection->{
			projection.getRoom().getSeats().forEach(seat->{
				Ticket ticket=new Ticket();
				ticket.setPrice(projection.getPrice());
				ticket.setMovieProjection(projection);
				ticket.setSeat(seat);
				ticket.setBooked(false);
				ticketRepository.save(ticket);
				
				
			});
			
			
		});
		
	}
	/*
	@Override
	@PostConstruct
	public void initUsers() {
		/*List<User> users = Stream.of(
					new User(101L,"hamzagueddi","7_5inoz5_","hamzaa.gueddi@gmail.com"),
					new User(102L,"superuser","superpassword","testadmin@gmail.com")
					).collect(Collectors.toList());
		userRepository.saveAll(users);
		*/
//	}


}
