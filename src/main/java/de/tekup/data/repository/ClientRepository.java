package de.tekup.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import de.tekup.data.models.Client;

@Repository
@EnableJpaRepositories
public interface ClientRepository extends JpaRepository<Client, Integer> {
	// exact match 
	Optional<Client> findByNom(String nom);
	// match without case
	Optional<Client> findByNomIgnoreCase(String nom);
	   @Query("select DAYNAME(t.date) as day from Ticket t "
	            + "where t.client = :clt ")
	    List<String> GetPlusReserveByClient(@Param("clt")Client client);
}
