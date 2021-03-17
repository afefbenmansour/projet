package de.tekup.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import de.tekup.data.models.Tablee;
@Repository
@EnableJpaRepositories
public interface TableRepository extends JpaRepository<Tablee, Integer>{

}
