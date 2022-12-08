package it.university.student.controller;

import java.sql.Date;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.university.student.dao.impl.StudentService;
import it.university.student.dto.StudentDTO;
import it.university.student.entity.Student;
import it.university.student.exception.BindingException;
import it.university.student.exception.DuplicateException;
import it.university.student.exception.EmptyCollectionException;
import it.university.student.exception.NotFoundException;
import it.university.student.info.InfoMessage;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RestController @RequestMapping(path = "api/students/", produces = MediaType.APPLICATION_JSON_VALUE)
@Log
@Tag(name = "StudentController", description = "Controller per la gestione degli Studenti all'interno dell'Università")
public final class StudentController {
	
	@Autowired private StudentService service;
	@Autowired private ResourceBundleMessageSource errorMessage;
	
	@Operation(summary = "Ricerca di tutti gli Studenti",
			description = "Restituisce tutti gli Studenti presenti nel Database",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Gli Studenti sono stati trovati con successo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudents() {
		final List<StudentDTO> students = this.service.findAll();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente",
			description = "Restituisce lo Studente nel Database che corrisponde a quell'id, se presente",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'utente avente quell'id è stato trovato",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database con quell'Id",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundException.class)))
	})
	@GetMapping(path = "find/{id}") @SneakyThrows
	public final ResponseEntity<StudentDTO> getStudent(@Parameter(description = "Id dello Studente da ricercare", required = true) 
	@PathVariable("id") String id) {
		log.info("Richiesto studente con id".concat(id));
		final StudentDTO student = this.service.findById(id);
		if(student == null) {
			throw new NotFoundException();
		}
		return new ResponseEntity<StudentDTO>(student, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente con data di nascita compresa tra i due valori di input",
			description = "Restituisce gli Studenti all'interno del Database, con la data di nascita compresa tra i due valori",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database con data di nascita compresa nell'intervallo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/date/{start}/{end}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDateOfBirth(
			@Parameter(description = "Data di inizio", required = true) @PathVariable("start") Date start, 
			@Parameter(description = "Data di fine", required = true) @PathVariable("end") Date end) {
		final List<StudentDTO> students = this.service.findAllByDate(start, end);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che possiede una laurea triennale",
			description = "Restituisce gli Studenti all'interno del Database, che possiedono una laurea triennale",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che abbia conseguito la laurea triennale",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/bachelor/has") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsHavingBachelorDegreee() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegree();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che non possiede una laurea triennale",
			description = "Restituisce gli Studenti all'interno del Database, che non possiedono una laurea triennale",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che non abbia conseguito la laurea triennale",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/bachelor/has/not") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsNotHavingBachelorDegreee() {
		final List<StudentDTO> students = this.service.findAllByBachelorDegreeNot();
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia conseguito un diploma con quel voto",
			description = "Restituisce gli Studenti all'interno del Database, abbiano ottenuto un diploma con un voto pari a quello indicato",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che abbia conseguito il diploma con il voto indicato",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/diploma/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByDiplomaGrade(
			@Parameter(description = "Voto di diploma che lo Studente deve aver ottenuto", required = true)
			@PathVariable("grade") byte grade) {
		final List<StudentDTO> students = this.service.findAllByDiplomaGrade(grade);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia conseguito una laurea triennale con il voto indicato",
			description = "Restituisce gli Studenti all'interno del Database, abbiano ottenuto una laurea triennale con un voto pari a quello indicato",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che abbia conseguito la laurea triennale con il voto indicato",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/bachelor/grade/{grade}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByBachelorGrade(
			@Parameter(description = "Voto di laurea che lo Studente deve aver ottenuto", required = true)
			@PathVariable("grade") byte grade) {
		final List<StudentDTO> students = this.service.findAllByBachelorGrade(grade);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia la residenza nella città indicata",
			description = "Restituisce gli Studenti all'interno del Database, che siano attualmente residenti nella città indicata",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che sia residente nella città indicata",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/address/city/{city}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByCity(
			@Parameter(description = "Nome della città per cui ricercare gli Studenti", required = true)
			@PathVariable("city") String city) {
		final List<StudentDTO> students = this.service.findAllByCity(city);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia la residenza nella provincia indicata",
			description = "Restituisce gli Studenti all'interno del Database, che siano attualmente residenti nella provincia indicata",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che sia residente nella provincia indicata",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/address/province/{province}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByProvince(
			@Parameter(description = "Provincia nella quale lo Studente deve essere residente", required = true)
			@PathVariable("province") String province) {
		final List<StudentDTO> students = this.service.findAllByProvince(province);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia la residenza nella regione indicata",
			description = "Restituisce gli Studenti all'interno del Database, che siano attualmente residenti nella regione indicata",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che sia residente nella regione indicata",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/address/region/{region}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByRegion(
			@Parameter(description = "Regione nella quale lo Studente deve essere residente", required = true)
			@PathVariable("region") String region) {
		final List<StudentDTO> students = this.service.findAllByRegion(region);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia la residenza nella nazione indicata",
			description = "Restituisce gli Studenti all'interno del Database, che siano attualmente residenti nella nazione indicata",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che sia residente nella nazione indicata",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/address/nation/{nation}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByNation(
			@Parameter(description = "Nazione nella quale lo Studente deve essere residente", required = true)
			@PathVariable("nation") String nation) {
		final List<StudentDTO> students = this.service.findAllByNation(nation);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Ricerca di uno Studente che abbia la residenza nell'indirizzo indicato",
			description = "Restituisce gli Studenti all'interno del Database, che siano attualmente residenti in quell'indirizzo",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "E' stato trovato un insieme di Studenti che corrispondono al criterio di ricerca",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Non è stato trovato alcuno Studente nel Database che sia residente nell'indirizzo indicato",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmptyCollectionException.class)))
	})
	@GetMapping(path = "find/address/id/{address}") @SneakyThrows
	public final ResponseEntity<List<StudentDTO>> getStudentsByAddress(
			@Parameter(description = "Indirizzo nel quale lo Studente deve essere residente", required = true)
			@PathVariable("address") int address) {
		final List<StudentDTO> students = this.service.findAllByAddress(address);
		if(students.isEmpty()) {
			throw new EmptyCollectionException();
		}
		return new ResponseEntity<List<StudentDTO>>(students, HttpStatus.OK);
	}
	
	@Operation(summary = "Inserimento di uno studente nella collezione",
			description = "Inserisce uno Studente all'interno del Database, se questo non è presente già",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Lo Studente è stato inserito nel Database con successo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "400", description = "I dati dello Studente che sono stati passati al servizio non sono congruenti con quelli necessari",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BindingException.class))),
			@ApiResponse(responseCode = "406", description = "Lo Studente è già presente all'interno del Database",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DuplicateException.class)))
	})
	@PostMapping(path = "post") @SneakyThrows
	public final ResponseEntity<InfoMessage> postStudent(
			@Parameter(description = "Studente da inserire nel Database", required = true)
			@Valid @RequestBody Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		final StudentDTO studentFound = this.service.findById(student.getId());
		if(studentFound != null) {
			throw new DuplicateException("Studente già presente nel database!");
		}
		
		this.service.save(student);
		
		return new ResponseEntity<InfoMessage>(new InfoMessage(HttpStatus.CREATED.value(), LocalDate.now(), "Studente inserito con successo!"), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Modifica di uno studente nella collezione",
			description = "Modifica uno Studente all'interno del Database, se questo è già presente ",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Lo Studente è stato modificato con successo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "400", description = "I dati dello Studente che sono stati passati al servizio non sono congruenti con quelli necessari",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BindingException.class))),
			@ApiResponse(responseCode = "404", description = "Lo Studente non è presente all'interno del Database",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundException.class)))
	})
	@PutMapping(path = "put") @SneakyThrows
	public final ResponseEntity<InfoMessage> putStudent(
			@Parameter(description = "Studente da modificare", required = true)
			@Valid @RequestBody Student student, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new BindingException(this.errorMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()));
		}
		final StudentDTO studentFound = this.service.findById(student.getId());
		if(studentFound == null) {
			throw new NotFoundException();
		}
		
		this.service.save(student);
		
		return new ResponseEntity<InfoMessage>(new InfoMessage(HttpStatus.CREATED.value(), LocalDate.now(), "Studente modificato con successo!"), HttpStatus.CREATED);		
	}

	@Operation(summary = "Elimina di uno studente nella collezione",
			description = "Elimina uno Studente all'interno del Database, se questo è già presente ",
			tags = {"SudentDTO"})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Lo Studente è stato eliminato con successo",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StudentDTO.class))),
			@ApiResponse(responseCode = "404", description = "Lo Studente non è presente all'interno del Database",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NotFoundException.class)))
	})
	@DeleteMapping(path = "delete/{id}") @SneakyThrows
	public final ResponseEntity<InfoMessage> deleteStudent(@PathVariable("id") String id) {
		final StudentDTO student = this.service.findById(id);
		if(student == null) {
			throw new NotFoundException();
		}
		this.service.deleteStudent(id);
		return new ResponseEntity<InfoMessage>(new InfoMessage(HttpStatus.ACCEPTED.value(), LocalDate.now(), "Studente eliminato con successo!"), HttpStatus.ACCEPTED);		
	}
}
