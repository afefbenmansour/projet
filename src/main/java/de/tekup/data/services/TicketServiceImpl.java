package de.tekup.data.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.data.dto.MetResponse;
import de.tekup.data.dto.TicketRequest;
import de.tekup.data.dto.TicketResponse;
import de.tekup.data.models.Met;
import de.tekup.data.models.Plat;
import de.tekup.data.models.Ticket;
import de.tekup.data.repository.MetRepository;
import de.tekup.data.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository reposTicket;
	private MetRepository reposMet;
	 private ModelMapper mapper=new ModelMapper();
	@Autowired
	public TicketServiceImpl(TicketRepository reposTicket,MetRepository reposMet) {
		super();
		this.reposTicket = reposTicket;
		this.reposMet=reposMet;
	}

	@Override
	public TicketResponse creatticket(TicketRequest ticketreq) {
		Ticket req=mapper.map(ticketreq, Ticket.class);
			 reposTicket.save(req);
			 return mapper.map(ticketreq, TicketResponse.class);
	}

	@Override
	public TicketResponse getTicketById(int numero) {
		
		Optional<Ticket> optick = reposTicket.findById(numero);
		Ticket tick;
		if (optick.isPresent())
			tick = optick.get();
		else
			throw new NoSuchElementException("Ticket with this number is not found");
		return mapper.map(tick, TicketResponse.class) ;
	}

	@Override
	public List<Ticket> getAllTicket() {
		
		return reposTicket.findAll();
	}

	@Override
	public Ticket modifyTicket(int numero, Ticket newticket) {
		
		 Ticket tickancien=this.getEntityById(numero);
	
		if(newticket.getAddition()!=0)
			tickancien.setAddition(newticket.getAddition());
		if(newticket.getNbCouvert()!=0)
			tickancien.setNbCouvert(newticket.getNbCouvert());
		if(newticket.getDate()!=null)
			tickancien.setDate(newticket.getDate());
		
		return reposTicket.save(tickancien);
	}

	@Override
	public Ticket deleteTicket(int numero) {
		Ticket tick=this.getEntityById(numero);
		reposTicket.deleteById(numero);
		return (tick);
	}

	private Ticket getEntityById(int numero) {
		Optional<Ticket> optick = reposTicket.findById(numero);
		Ticket tick;
		if (optick.isPresent())
			tick = optick.get();
		else
			throw new NoSuchElementException("Ticket with this Id is not found");
		return (tick) ;
	}
	@Override
	public Ticket addRepas(int numero, Met met) {
	 
        Ticket ticket = this.getEntityById(numero);
        ticket.getMets().add(met);
        ticket.setAddition(ticket.getAddition()+met.getPrix());
        met.getTickets().add(ticket);
        //saving the changes
        reposMet.save(met);
        return reposTicket.save(ticket);
	}

	@Override
	public MetResponse getPlusAcheteByDate(LocalDateTime date) {
	       List<Ticket> tickets = this.getAllTicket();
	        List<String> plats = new ArrayList<String>();
	        
	        int occ = 0;
	        for (Ticket ticket:tickets)
	        {
	            if (ticket.getDate()!=null)
	            {
	                if (ticket.getDate().equals(date))
	                {
	                    List<Met> mets = ticket.getMets();
	                    for (Met met:mets)
	                    {
	                        if ("plat".equals(met.getClass(met.g).toLowerCase()))
	                        {
	                            plats.add(met.getNom());
	                        }
	                    }
	                }
	            }
	        }
	        Optional<Met> opt = null;
	        for (String plat : plats)
	        {
	            if (occ < Collections.frequency(plats, plat))
	            {
	                occ = Collections.frequency(plats, plat);
	                opt = reposMet.findById(plat);
	            }
	        }
	        Met platRes;
	        if (opt.isPresent())
	        {
	            platRes = opt.get();
	        }
	        else
	        {
	            throw new NoSuchElementException("y a pas un plat avec le date donée");
	        }
	        return mapper.map(platRes, MetResponse.class);
	    }
	@Override
	public List<String> getRevenueParDay() {
		return reposTicket.getRevenueByDay();
	}

	@Override
	public List<String> getRevenueParMonth() {
		return reposTicket.getRevenueByMonth();
	}

	@Override
	public List<String> getRevenueParWeek() {
		return reposTicket.getRevenueByWeek();
	}

	@Override
	public List<String> getRevenueByDate(String date) {
		return reposTicket.getRevenueByDate(date);
	}
	
} 
