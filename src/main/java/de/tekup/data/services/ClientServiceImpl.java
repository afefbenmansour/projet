package de.tekup.data.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.tekup.data.dto.ClientRequest;
import de.tekup.data.dto.ClientResponse;
import de.tekup.data.models.Client;
import de.tekup.data.models.Ticket;
import de.tekup.data.repository.ClientRepository;
import de.tekup.data.repository.TicketRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
 private ClientRepository cltrepos;
 private TicketRepository ticketrepos;
 private ModelMapper mapper=new ModelMapper();
 
 	@Autowired
	public ClientServiceImpl(ClientRepository cltrepos) {
	super();
	this.cltrepos = cltrepos;
}

	@Override
	public ClientResponse createClient(ClientRequest clt) {
		Client req=mapper.map(clt, Client.class);
		 cltrepos.save(req);
		 return mapper.map(req, ClientResponse.class);
	}

	@Override
	public ClientResponse getClientById(int id) {
		Optional<Client> opt = cltrepos.findById(id);
		Client clt;
		if (opt.isPresent())
			clt = opt.get();
		else
			throw new NoSuchElementException("Client with this Id is not found");
		return mapper.map(clt, ClientResponse.class);
	}

	@Override
	public List<Client> getAllClients() {
	
		return cltrepos.findAll();
	}

	@Override
	public ClientResponse modifyClient(int id, ClientRequest newclt) {
		
		Client clientreq=mapper.map(newclt, Client.class);
		Client cltancien=this.getEntityById(id);
		
		if(clientreq.getNom()!=null)
			cltancien.setNom(clientreq.getNom());
		if(clientreq.getPrenom()!=null)
			cltancien.setPrenom(clientreq.getPrenom());
		if(clientreq.getTelephone()!=null)
			cltancien.setTelephone(clientreq.getTelephone());
		if(clientreq.getDateNaissance()!=null)
			cltancien.setDateNaissance(clientreq.getDateNaissance());
		if(clientreq.getDateNaissance()!=null)
			cltancien.setDateNaissance(clientreq.getDateNaissance());
		if(clientreq.getCouriel()!=null)
			cltancien.setCouriel(clientreq.getCouriel());
		 cltrepos.save(cltancien);
		 return mapper.map(cltancien, ClientResponse.class);
	}

	@Override
	public ClientResponse deleteClient(int id) {
	Client clt=this.getEntityById(id);
		cltrepos.deleteById(id);
		return mapper.map(clt, ClientResponse.class);
	}

	@Override
	public Client getClientByNom(String nom) {
		
		return cltrepos.findByNomIgnoreCase(nom).orElseThrow(()->
		new NoSuchElementException("Client with this Name is not found"));
	}
	public  Client getEntityById(int id) {
		Optional<Client> opt = cltrepos.findById(id);
		Client clt;
		if (opt.isPresent())
			clt = opt.get();
		else
			throw new NoSuchElementException("Client with this Id is not found");
		return (clt);
	}

	@Override
	public Client addTicket(int id, Ticket ticket) {
	
        ticket.setDate(LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonth(),
        LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getHour(), LocalDateTime.now().getMinute()));
        Client client = this.getEntityById(id);
        client.getTickets().add(ticket);
        ticket.setClient(client);
       ticketrepos.save(ticket);
        return cltrepos.save(client);
	}
	@Override
	public ClientResponse getPlusFideleClient() {
		   List<Client> clients = cltrepos.findAll();
	        Client saveClient = null;
	        int max = 0;
	        for (Client client:clients)
	        {
	            if (client.getTickets().size() > max)
	            {
	                max = client.getTickets().size();
	                saveClient = client;
	            }
	        }
	        return mapper.map(saveClient,ClientResponse.class);
	    }
	@Override
	public String getPlusReserveByClient(Client client) {
		
		 List<String> jours = cltrepos.GetPlusReserveByClient(client);
	        String JourPlusReserve =
	        		jours.stream()
	                .filter(Objects::nonNull)
	                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	                .entrySet().stream().max((o1, o2) -> o1.getValue().compareTo(o2.getValue()))
	                .map(Map.Entry::getKey).orElse(null);

	        return JourPlusReserve;
	}

//le client le plus fidéle au restaurant
/*	public Client ClientPlusFidel(Instant debutperiode,Instant finperiode){
		
	       List<Ticket>tickets=ticketrepos.findAll();
	       List<Ticket>ticks=new ArrayList<>();

	      for(Ticket ticket:tickets){
	          if(ticket.getDate().isAfter(debutperiode)&&ticket.getDate().isBefore(finperiode)){
	ticks.add(ticket);
	          }
	      }
	     List<Client>cl=ticks.stream().map(tic->tic.getClient()).collect(Collectors.toList());

	      Client fidel=cl.stream().collect(Collectors.groupingBy(l->l,
	 Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
	      return fidel;
	    }*/
}
