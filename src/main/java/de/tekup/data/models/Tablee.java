package de.tekup.data.models;


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
@javax.persistence.Table(name = "Tablee")
public class Tablee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private int nbCouvert;
	private String type;
	private float supplement;
	
	@OneToMany(mappedBy = "table",cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Ticket> tickets;
	
}
