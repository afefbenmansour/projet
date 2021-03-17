package de.tekup.data.services;

import java.util.List;

import de.tekup.data.dto.TableRequest;
import de.tekup.data.dto.TableResponse;
import de.tekup.data.models.Tablee;

public interface TableService {
  
	TableResponse createTable(TableRequest tab);
	TableResponse getTableById(int numero);
	List<Tablee> getAllTable();
	TableResponse modifyTable(int numero ,TableRequest newtab);
	TableResponse deleteTable(int numero);
	
}
