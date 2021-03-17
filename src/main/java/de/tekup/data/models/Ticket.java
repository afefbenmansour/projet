package de.tekup.data.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"client","table","mets"})
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;

	private LocalDateTime date;

	private int nbCouvert;

	private float addition;
	
	@ManyToOne
/*	@JoinColumn(name = "table_id")*/
	
	private Tablee table;
	
	@ManyToOne
	
	/*@JoinColumn(name = "client_id")*/
	private Client client;
	
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	    @JoinTable(name = "Ticket_Met",
	            joinColumns = @JoinColumn(name = "numero"),
	            inverseJoinColumns = @JoinColumn(name = "nom"))
	private List<Met>mets;
/*	
	private String getFullClient() {
		return client.getNom()+" "+client.getPrenom()+" ";
	}
	private void setCltReq(ClientRequest cltreq) {
		
		ModelMapper mapper=new ModelMapper();
		this.client=mapper.map(cltreq, Client.class);
	}*/
}
