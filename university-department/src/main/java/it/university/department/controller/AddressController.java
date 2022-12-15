package it.university.department.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Address;
import it.university.department.entity.Faculty;
import it.university.department.entity.builder.DepartmentBuilder;
import it.university.department.exception.BindingException;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "university/api/departments/address/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "AddressController", description = "Controller per le operazioni di ricerca basate sull'indirizzo")
public final class AddressController {
	
	@Autowired private DepartmentService departmentService;
	@Autowired private DepartmentBuilder departmentBuilder;
	@Autowired private ResourceBundleMessageSource errorMessage;
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti che sono nella via indicata",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nella via",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "street/{street}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByStreet(
			@Parameter(description = "Via in cui deve risiedere il Dipartimento") @PathVariable("street") String street) {
		log.info("[GET] - api/departments/address/street/ ".concat(street));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressStreet(street);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}

	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti che sono presenti nella città indicata",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nella città indicata",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "city/{city}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByCity(
			@Parameter(description = "Città in cui deve risiedere il Dipartimento") @PathVariable("city") String city) {
		log.info("[GET] - api/departments/address/city/ ".concat(city));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressCity(city);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}

	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti che sono presenti nella provincia indicata",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nella provincia indicata",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "province/{province}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByProvince(
			@Parameter(description = "Provincia in cui deve risiedere il Dipartimento") @PathVariable("province") String province) {
		log.info("[GET] - api/departments/address/province/ ".concat(province));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressProvince(province);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}

	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti che sono presenti nella regione indicata",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nella regione indicata",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "region/{region}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByRegion(
			@Parameter(description = "Regione in cui deve risiedere il Dipartimento") @PathVariable("region") String region) {
		log.info("[GET] - api/departments/address/region/ ".concat(region));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressRegion(region);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti che sono presenti nella nazione indicata",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nella provincia indicata",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "nation/{nation}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByNation(
			@Parameter(description = "Nazione nella quale deve risiedere il Dipartimento") @PathVariable("nation") String nation) {
		log.info("[GET] - api/departments/address/nation/ ".concat(nation));
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddressNation(nation);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti cha sono domiciliati nell'indirizzo indicato",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente nell'indirizzo indicato",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "id/{id}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByAddress(
			@Parameter(description = "Identificativo dell'indirizzo nel quale deve risiedere il Dipartimento") @PathVariable("id") int id) {
		log.info("[GET] - api/departments/address/id/ " + id);
		
		final List<DepartmentDTO> departments = this.departmentService.findAllByAddress(id);
		
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo PUT che modifica un Dipartimento restituendo un messaggio di informazione",
			description = "Restituisce un messaggio in base al successo o meno della modifica del Dipartimento",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Richiesta andata a buon fine, restituendo un messaggio di conferma",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "400", description = "La richiesta ha prodotto un errore. Il Dipartimento possiede delle violazioni nei dai",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Il Dipartimento non è presente nella base di dati",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@PutMapping(path = "department/update/{department}") @SneakyThrows
	public final ResponseEntity<Message> putDepartmentAddress(
			@Parameter(description = "Nome del Dipartimento dal modificare") @PathVariable("department") String department, 
			@Parameter(description = "Indirizzo modificato del Dipartimento") @Valid @RequestBody Address address,
			BindingResult bindingResult) {
		
		log.info("[PUT] - api/departments/address/department/ ".concat(department));
		log.info(address.toString());
		
		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		
		final DepartmentDTO foundDepartment = this.departmentService.findById(department);
		
		if(foundDepartment == null) {
			throw new NotFoundException();
		}
		
		this.departmentService.save(this.departmentBuilder
				.setName(department)
				.setAddress(address)
				.setFaculty(new Faculty(foundDepartment.getFaculty().getName()))
				.build());
		
		return new ResponseEntity<Message>(new Message(LocalDate.now(), "Dipartimento modificato con successo!", HttpStatus.CREATED.value()), HttpStatus.CREATED);		
	}
}
