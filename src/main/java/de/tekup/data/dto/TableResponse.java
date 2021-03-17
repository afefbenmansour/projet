package de.tekup.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableResponse {

	private int numero;
	private int nbCouvert;
	private String type;
	private float supplement;

}
