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
import it.university.department.dao.impl.StudentService;
import it.university.department.dto.StudentDTO;
import it.university.department.exception.NotFoundException;
import it.university.department.message.Message;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @Log
@RequestMapping(path = "university/api/departments/students")
@Tag(name = "StudentController", description = "Controller per le operazioni di ricerca degli Studenti afferenti ad un Dipartimento")
public final class StudentController {
	
	@Autowired private StudentService studentService;
	
	@Operation(
			summary = "Metodo GET che restituisce un insieme di Studenti",
			description = "Restituisce tutti gli Studenti che sono iscritti al Dipartimento indicato",
			security = @SecurityRequirement(name = "bearerAuth"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Richiesta andata a buon fine, restituendo la lista degli Studenti",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = StudentDTO.class)))),
			@ApiResponse(responseCode = "404", description = "La richiesta ha prodotto un errore. Nessuno Studente registrato al Dipartimento indicato",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "401", description = "Richiesta non valida, utente non autenticato.",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class))),
			@ApiResponse(responseCode = "403", description = "Richiesta non valida, utente non autorizzato.",
			content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Message.class)))
	})
	@GetMapping(path = "/{department}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudents(
			@Parameter(description = "Nome del Dipartimento") @PathVariable("department") String department) {
		log.info("[GET] -> api/departments/students/".concat(department));
		final List<StudentDTO> students = this.studentService.findAllByDepartment(department);
		if(students.isEmpty()) {
			throw new NotFoundException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
}
