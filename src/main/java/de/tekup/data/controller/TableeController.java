package de.tekup.data.controller;

import java.util.List;


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

import de.tekup.data.dto.TableRequest;
import de.tekup.data.dto.TableResponse;
import de.tekup.data.models.Tablee;
import de.tekup.data.services.TableService;

@RestController
@RequestMapping(value = "/api/tablee")
public class TableeController {
	
	private TableService tableeserice;
@Autowired
	public TableeController(TableService tableeserice) {
		super();
		this.tableeserice = tableeserice;
	} 
@PostMapping
public TableResponse createTable( @RequestBody @Valid TableRequest tab) {
	return tableeserice.createTable(tab);
}
@GetMapping(value = "/{id}")
public TableResponse getTableById( @PathVariable("id") int numero) {
	return tableeserice.getTableById(numero);
}
@GetMapping
public List<Tablee> getAllTable() {
	return tableeserice.getAllTable();
}
@PutMapping(value = "/tablee/{id}")
public TableResponse modifyTable(@PathVariable("id")int numero, @RequestBody @Valid TableRequest newtab) {
	return tableeserice.modifyTable(numero, newtab);
}
@DeleteMapping(value = "/{id}")
public TableResponse deleteTable(@PathVariable("id") int numero) {
	return tableeserice.deleteTable(numero);
}


}
