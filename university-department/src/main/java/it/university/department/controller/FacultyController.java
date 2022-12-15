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
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "university/api/departments/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "FacultyController", description = "Controller per le operazioni di ricerca basate sulle Facoltà dei Dipartimenti")
public final class FacultyController {
	
	@Autowired private DepartmentService departmentService;
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Dipartimenti",
			description = "Restituisce tutti i dipartimenti afferenti alla Facoltà indicata",
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
	@GetMapping(path = "/{faculty}") @SneakyThrows
	public final ResponseEntity<List<DepartmentDTO>> getDepartmentsByFaculty(
			@Parameter(description =  "Nome della Facoltà a cui afferisce un Dipartimento") @PathVariable("faculty") String faculty) {
		log.info("[GET] - /api/departments/faculty/".concat(faculty));
		final List<DepartmentDTO> departments = this.departmentService.findAllByFaculty(faculty);
		if(departments.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<DepartmentDTO>>(departments, HttpStatus.OK);
	}

}
