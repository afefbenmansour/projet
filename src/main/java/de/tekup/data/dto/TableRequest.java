package de.tekup.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableRequest {
	
	private int numero;

	private int nbCouvert;
	
	private String type;

	private float supplement;
	
	
}
