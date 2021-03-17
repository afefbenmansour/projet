package de.tekup.data.dto;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.tekup.data.models.Met;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketRequest {
	@JsonDeserialize
	@FutureOrPresent(message = "la date doit etre présente")
	private LocalDateTime date;
	@Positive(message = "ce champ doit etre positive")
	private int nbCouvert;
	@PositiveOrZero(message = "ce champ doit etre positive")
	private float addition;
	@Valid
	private ClientRequest client;
	@Valid
	private TableRequest table;
	
	private java.util.List<Met> mets;

}
