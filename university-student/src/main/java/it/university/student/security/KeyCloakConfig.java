package it.university.student.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jdk.jfr.Description;

@Configuration
public class KeyCloakConfig {
	
	@Bean @Description("Configurazione di Keycloak per gestire il ruolo degli utenti")
	public KeycloakSpringBootConfigResolver keyCloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
}
