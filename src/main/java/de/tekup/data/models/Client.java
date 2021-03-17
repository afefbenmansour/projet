package de.tekup.data.models;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	private String prenom;

	private LocalDate dateNaissance;

	private String couriel;
	
	private String telephone;
	
	 @OneToMany(mappedBy = "client" ,cascade = CascadeType.REMOVE)
	 @JsonIgnore
	private List<Ticket> tickets;

	
}
