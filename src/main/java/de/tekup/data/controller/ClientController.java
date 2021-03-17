package de.tekup.data.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.data.dto.ClientRequest;
import de.tekup.data.dto.ClientResponse;
import de.tekup.data.models.Client;
import de.tekup.data.models.Ticket;
import de.tekup.data.services.ClientService;
import de.tekup.data.services.TicketService;

@RestController
@RequestMapping(value = "api/client/")
public class ClientController {
	

	ClientService cltservice;
	TicketService tickservice;
	
@Autowired
public ClientController(ClientService cltservice,TicketService tickservice) {
	super();
	this.cltservice = cltservice;
	this.tickservice=tickservice;
}
@PostMapping
 public ClientResponse createClient(@RequestBody @Valid  ClientRequest clt) {
	 return cltservice.createClient(clt);
 }
@GetMapping(value = "/{id}")
public ClientResponse getClientById(@PathVariable("id")int id) {
	return cltservice.getClientById(id);
}
@GetMapping
public List<Client> getAllClients() {
	return cltservice.getAllClients();
}
@PutMapping(value = "/{id}")
public ClientResponse modifyClient(@PathVariable("id") int id,@RequestBody @Valid ClientRequest newclt) {
	return cltservice.modifyClient(id, newclt);
}
@DeleteMapping(value = "/{id}")
public ClientResponse deleteClient(@PathVariable("id") int id) {
	return cltservice.deleteClient(id);
}
@GetMapping(value = "/clt/{nom}")
public Client getClientByNom(@PathVariable("nom") String nom) {
	return cltservice.getClientByNom(nom);
}


}
