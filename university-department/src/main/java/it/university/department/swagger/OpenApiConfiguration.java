package it.university.department.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
		name = "bearerAuth",
		type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
		)
public class OpenApiConfiguration {

	// http://localhost:8001/swagger-ui/index.html#/
	
	@Bean
	public OpenAPI getCustomOpenApi() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Progetto UniversitY - Dipartimenti Web Service")
						.description("REST API in Spring Boot per la gestione dei Dipartimenti, del progetto UniversitY")
						.contact(new Contact().email("edo.oranger@email.com").name("Edoardo Oranger").url("https://github.com/mrOranger/"))
						.license(new License().name("GNU General Public License v3.0").url("https://www.gnu.org/licenses/gpl-3.0.en.html"))
						.version("1.0"));
	}
	
}