package de.tekup.data.dto;

import java.time.LocalDateTime;
import java.util.List;

import de.tekup.data.models.Client;
import de.tekup.data.models.Met;
import de.tekup.data.models.Tablee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
	
  private LocalDateTime date;
  private int nbCouvert;
  private float addition;
  private Tablee table;
  private Client client;
  private List<Met> mets;
}
