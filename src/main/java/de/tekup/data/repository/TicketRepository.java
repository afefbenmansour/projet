package de.tekup.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import de.tekup.data.models.Ticket;
@Repository
@EnableJpaRepositories
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
    @Query("select Day(DATE(t.date)) as date, sum(t.addition) as revenue "
            + "FROM Ticket t " + "group by date")
    List<String> getRevenueByDay();

    @Query("select Week(DATE(t.date)) as date, sum(t.addition) as revenue "
            + "FROM Ticket t " +"group by date")
    List<String> getRevenueByWeek();
    @Query("select Month(DATE(t.date)) as date, sum(t.addition) as revenue "
            +
            "FROM Ticket t " + "group by date")
           
    List<String> getRevenueByMonth();

    @Query("select sum(t.addition) as revenue "
            +
            "FROM Ticket t "  + "where t.date = :dr ")
    List<String> getRevenueByDate(@Param("dr")String date);
}
