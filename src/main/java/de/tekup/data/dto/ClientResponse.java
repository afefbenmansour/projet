package de.tekup.data.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
	private int id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String couriel;
	private String telephone;
}
