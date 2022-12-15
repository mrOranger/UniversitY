package it.university.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.university.department.dao.impl.DepartmentService;
import it.university.department.dao.impl.ProfessorService;
import it.university.department.dto.DepartmentDTO;
import it.university.department.dto.ProfessorDTO;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "university/api/departments/professors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ProfessorController", description = "Controller per le operazioni di ricerca dei Professori afferenti ad un Dipartimento")
public final class ProfessorController {
	
	@Autowired private ProfessorService professorService;
	@Autowired private DepartmentService departmentService;
	 
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Professori",
			description = "Restituisce tutti i Professori afferenti al Dipartimento indicato",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei Professori",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun Professori presente nel Dipartimento indicato",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "/{name}") @SneakyThrows
	public final ResponseEntity<List<ProfessorDTO>> getProfessors(@PathVariable("name") String name) {
		log.info("[GET] - api/departments/professors/".concat(name));
		
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException("Non è stato trovato alcun Dipartimento!");
		}
		
		final List<ProfessorDTO> professors = this.professorService.findAllByDepartment(name);
		if(professors.isEmpty()) {
			throw new NotFoundException("Nessun professore è iscritto a questo dipartimento!");
		}
		
		return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
	}
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Professori",
			description = "Restituisce tutti i Professori che sono anche Direttori del Dipartimento indicato",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista dei Professori",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessun Professore presente nel Dipartimento indicato",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "/directors/{name}") @SneakyThrows
	public final ResponseEntity<List<ProfessorDTO>> getDirectors(@PathVariable("name") String name) {
		log.info("[GET] - api/departments/professors/directors/".concat(name));
		
		final DepartmentDTO department = this.departmentService.findById(name);
		if(department == null) {
			throw new NotFoundException("Non è stato trovato alcun Dipartimento!");
		}
		
		final List<ProfessorDTO> professors = this.professorService.findDirectorsByDepartment(name);
		if(professors.isEmpty()) {
			throw new NotFoundException("Il dipartimento non ha un direttore!");
		}
		
		return new ResponseEntity<List<ProfessorDTO>>(professors, HttpStatus.OK);
	}
}
