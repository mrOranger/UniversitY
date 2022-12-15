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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.university.department.dao.impl.DepartmentService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.entity.Department;
import it.university.department.exception.BindingException;
import it.university.department.exception.DuplicateException;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController
@Log @RequestMapping(path = "university/api/departments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "DepartmentController", description = "Controller per le operazioni di ricerca dei Dipartimenti")
public final class DepartmentController {
	
	@Autowired private DepartmentService departmentService;
	@Autowired private ResourceBundleMessageSource errorMessage;
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti presenti nella base di dati",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei dipartimenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,array = @ArraySchema(schema = @Schema(implementation = DepartmentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartments() {
		log.info("[GET] - api/departments ");
		final List<DepartmentDTO> departments = this.departmentService.findAll();
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo GET che restituisce un Dipartimento",
			description = "Restituisce il Dipartimento con quel nome presente nella base di dati",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo il Dipartimento richiesto",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun dipartimento presente con il nome indicato",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "/{name}") @SneakyThrows
	public final ResponseEntity<DepartmentDTO> getDepartment(
			@Parameter(description = "Nome del Dipartimento da cercare") @PathVariable("name") String name) {
		log.info("[GET] - api/departments/".concat(name));
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<DepartmentDTO>(department, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo POST che inserisce un Dipartimento",
			description = "Inserisce un Dipartimento nella base di dati, restituendo un messaggio di conferma o errore",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo un messaggio di conferma",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "400", description = "La richiesta ha prodotto un errore. Il dato viola alcuni vincoli di integrità",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "406", description = "La richiesta ha prodotto un errore. Il Dipartimento è già presente nella base di dati",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@PostMapping(path = "/insert") @SneakyThrows
	public final ResponseEntity<Message> postDepartment(
			@Parameter(description = "Dipartimento da inserire nella base di dati") @Valid @RequestBody Department department,
			BindingResult bindingResult) {
		
		log.info("[POST] - api/departments/");

		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		
		if(this.departmentService.findById(department.getName()) != null) {
			throw new DuplicateException();
		}
		
		this.departmentService.save(department);
		
		return new ResponseEntity<Message>(new Message(LocalDate.now(), "Dipartimento inserito con successo!", HttpStatus.OK.value()), HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo PUT che modifica un Dipartimento",
			description = "Modifica un Dipartimento nella base di dati, restituendo un messaggio di conferma o errore",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo un messaggio di conferma",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "400", description = "La richiesta ha prodotto un errore. Il dato viola alcuni vincoli di integrità",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Il Dipartimento non è presente nella base di dati",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@PutMapping(path = "/update/{department}") @SneakyThrows
	public final ResponseEntity<Message> putDepartment(
			@Parameter(description = "Nome del Dipartimento da modificare") @PathVariable("department") String name,
			@Parameter(description = "Valori del Dipartimento da modificare") @Valid @RequestBody Department department,
			BindingResult bindingResult
			){
		
		log.info("[PUT] - api/departments/".concat(name));
		
		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		
		if(this.departmentService.findById(name) == null) {
			throw new NotFoundException();
		}
		
		this.departmentService.save(department);
		
		return new ResponseEntity<Message>(new Message(LocalDate.now(), "Dipartimento modificato con successo!", HttpStatus.OK.value()), HttpStatus.OK);
	}

	@Operation(
			summary = "Metodo DELETE che elimina un Dipartimento",
			description = "Elimina un Dipartimento nella base di dati, restituendo un messaggio di conferma o errore",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo un messaggio di conferma",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Il Dipartimento non è presente nella base di dati",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@DeleteMapping(path = "/delete/{department}") @SneakyThrows
	public final ResponseEntity<Message> deleteDepartment(
			@Parameter(description = "Nome del Dipartimento da eliminare") @PathVariable("department") String department) {
		log.info("[DELETE] - api/departments/".concat(department));
		if(this.departmentService.findById(department) == null) {
			throw new NotFoundException();
		}
		this.departmentService.delete(department);
		return new ResponseEntity<Message>(new Message(LocalDate.now(), "Dipartimento eliminato con successo!", HttpStatus.OK.value()), HttpStatus.OK);
	}

}
