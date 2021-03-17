package de.tekup.data.services;

import java.time.LocalDateTime;
import java.util.List;

import de.tekup.data.dto.MetResponse;
import de.tekup.data.dto.TicketRequest;
import de.tekup.data.dto.TicketResponse;
import de.tekup.data.models.Met;
import de.tekup.data.models.Ticket;

public interface TicketService {

	public TicketResponse creatticket(TicketRequest ticketreq);
	 TicketResponse getTicketById(int numero);
	 List<Ticket> getAllTicket();
	 Ticket modifyTicket(int numero ,Ticket newticket);
	 Ticket deleteTicket(int numero);
	 Ticket addRepas(int numero, Met met);
	 //le plat le plus acheté dans une date donnée
	 MetResponse getPlusAcheteByDate(LocalDateTime date);
	 //revenue par jour
	 List<String> getRevenueParDay();
	 //revenue par mois
	 List<String> getRevenueParMonth();
	 //revenue par semaine
	 List<String> getRevenueParWeek();
	 //revenue par un date donné
	 List<String> getRevenueByDate(String date);
	 
}
