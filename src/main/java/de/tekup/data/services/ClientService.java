package de.tekup.data.services;

import java.util.List;

import de.tekup.data.dto.ClientRequest;
import de.tekup.data.dto.ClientResponse;
import de.tekup.data.models.Client;
import de.tekup.data.models.Ticket;

public interface ClientService {

	
	public ClientResponse createClient(ClientRequest clt);
	ClientResponse getClientById(int id);
	List<Client> getAllClients();
	ClientResponse modifyClient(int id,ClientRequest newclt);
	ClientResponse deleteClient(int id);
	Client getClientByNom(String nom);
	Client addTicket(int id, Ticket ticket);
	Client getEntityById(int id);
	  //le client le plus fidèle au restaurant
	ClientResponse getPlusFideleClient();

	String getPlusReserveByClient(Client client);
}
