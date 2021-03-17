package de.tekup.data.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;


import de.tekup.data.dto.TicketRequest;
import de.tekup.data.dto.TicketResponse;
import de.tekup.data.models.Met;
import de.tekup.data.models.Ticket;
import de.tekup.data.services.TicketService;

@RestController
@RequestMapping(value = "/api/ticket")

public class TicketController {
  private TicketService ticketservice;
  
@Autowired
public TicketController(TicketService ticketservice) {
	super();
	this.ticketservice = ticketservice;
}
@PostMapping()
public TicketResponse creatticket(@RequestBody @Valid TicketRequest ticket) {
	return ticketservice.creatticket(ticket);
}
@GetMapping(value = "/{numero}")
public TicketResponse getTicketById(@PathVariable("numero") int numero) {
	return ticketservice.getTicketById(numero);
}
@GetMapping("/all")
public List<Ticket> getAllTicket() {
	return ticketservice.getAllTicket();
}
@PutMapping(value = "/{numero}")
public Ticket modifyTicket(@PathVariable("numero")int numero, @RequestBody Ticket newticket) {
	return ticketservice.modifyTicket(numero, newticket);
}
@DeleteMapping(value = "/{numero}")
public Ticket deleteTicket(@PathVariable("numero") int numero) {
	return ticketservice.deleteTicket(numero);
}
	
}




