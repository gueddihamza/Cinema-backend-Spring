package com.project.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Movie implements Serializable {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(length = 75)
		private String name;
		private double duration;
		@Column(length = 50)
		private String director;
		private String description;
		private String photo;
		private Date releaseDate;
		@ManyToOne
		private Genre genre;
		@OneToMany(mappedBy = "movie")
		@JsonProperty(access = Access.WRITE_ONLY)
		private Collection<MovieProjection> projections;
		
}
